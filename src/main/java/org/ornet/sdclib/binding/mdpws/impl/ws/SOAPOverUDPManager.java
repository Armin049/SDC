/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting
 * @Copyright (C) SurgiTAIX AG
 */
package org.ornet.sdclib.binding.mdpws.impl.ws;

import static com.bestingit.async.Task.blocking;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;
import org.ornet.sdclib.common.MaxHashMap;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;

public class SOAPOverUDPManager {

    private final Map<String, DatagramSocket> senders = Collections.synchronizedMap(new MaxHashMap<>(MAX_ENTRIES));
    private final Set<DatagramSocket> recvers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Set<String> msgIds = Collections.newSetFromMap(Collections.synchronizedMap(new MaxHashMap<>(MAX_ENTRIES)));
    private final MDPWSTransportLayerConfiguration tlc;

    private static final int MAX_ENTRIES = 64;
    private static final int SOCKET_BUFFER_SIZE = 131072;

    public SOAPOverUDPManager(MDPWSTransportLayerConfiguration tlc) {
        this.tlc = tlc;
    }

    /**
     * Start receiving multicast messages.
     *
     * @param localAddress
     * @param mcastAddress
     * @param mcastPort
     * @param responseCallback
     */
    public void startRecvMulticast(String localAddress, String mcastAddress, int mcastPort, UDPCallback responseCallback) {
        if (localAddress.equals("0.0.0.0")) {
            throw new IllegalStateException("Binding to address 0.0.0.0 not possible - provide valid local IPs!");
        }
        SDCLib.getInstance().getLogger().log(Level.FINE, "Starting multicast receiver: {0} listening on {1}:{2}", new Object[]{localAddress, mcastAddress, mcastPort});
        if (responseCallback == null) {
            throw new IllegalStateException("Reponse callback must not be null!");
        }
        try {
            final MulticastSocket socket = new MulticastSocket(mcastPort);
            socket.setReuseAddress(true);
            socket.setInterface(InetAddress.getByName(localAddress));
            socket.joinGroup(InetAddress.getByName(mcastAddress));
            socket.setTimeToLive(tlc.getConfigurationDetail().getTimeToLive());
            // No timeout for multicast
            socket.setSoTimeout(0);
            recvers.add(socket);
            startReceiving(socket, responseCallback);
        } catch (Exception e) {
            SDCLib.getInstance().getLogger().log(Level.SEVERE, "Multicast socket listen failed.", e);
        }
    }

    private void startReceiving(final DatagramSocket socket, UDPCallback responseCallback) {
        blocking(() -> {
            byte[] buffer = new byte[65536];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    if (socket.isClosed()) {
                        break;
                    }
                    socket.receive(packet);
                    byte[] data = Arrays.copyOf(packet.getData(), packet.getLength());
                    try {
                        AbstractSoapMessage asm = new AbstractSoapMessage(data);
                        final String msgId = asm.getMessageId();
                        if (msgId != null) {
                            if (msgIds.contains(msgId)) {
                                SDCLib.getInstance().getLogger().log(Level.FINER, "Message discarded due to known ID, Action: {0}", asm.getAction());
                                //return;
                            }
                            msgIds.add(msgId);
                        }
                        responseCallback.getSupplier().apply(packet.getAddress().getHostAddress(), packet.getPort(), asm);
                    } catch (Exception e) {
                        SDCLib.getInstance().getLogger().log(Level.SEVERE, "Error processing UDP message.", e);
                    }
                } catch (SocketTimeoutException e) {
                    SDCLib.getInstance().getLogger().log(Level.FINEST, "UDP receive timeout. Stopping reception: {0}", socket.getLocalAddress());
                    break;
                } catch (Exception e) {
                    if (socket.isClosed()) {
                        break;
                    } else {
                        SDCLib.getInstance().getLogger().log(Level.SEVERE, "Error receiving message.", e);
                    }
                }
            }
        });
    }

    /**
     * Send to remote unicast or multicast and optionally receive unicast
     * response.
     *
     * @param localAddress
     * @param targetAddress
     * @param targetPort
     * @param msg
     * @param responseCallback
     */
    public void send(String localAddress, String targetAddress, int targetPort, AbstractSoapMessage msg, UDPCallback responseCallback) {
        if (responseCallback != null && localAddress.equals("0.0.0.0")) {
            throw new IllegalStateException("Binding to address 0.0.0.0 for responses not possible - provide valid local IPs!");
        }
        String resKey = responseCallback != null ? "-" + responseCallback.getId() : new String();
        String key = localAddress + "-" + targetAddress + "-" + targetPort + resKey;
        final DatagramSocket socket = senders.get(key);
        // We already have a socket
        if (socket != null) {
            send(socket, msg, targetAddress, targetPort);
            return;
        }
        // Create new socket and send
        try {
            DatagramSocket newSocket = createSocket(localAddress);
            if (responseCallback != null) {
                startReceiving(newSocket, responseCallback);
            }
            senders.put(key, newSocket);
            send(newSocket, msg, targetAddress, targetPort);
        } catch (SocketException ex) {
            SDCLib.getInstance().getLogger().log(Level.SEVERE, null, ex);
        }
    }

    private DatagramSocket createSocket(String localAddress) throws SocketException {
        final DatagramSocket newSocket = new DatagramSocket(new InetSocketAddress(localAddress, 0));
        newSocket.setSoTimeout(tlc.getConfigurationDetail().getCommTimeout());
        newSocket.setSendBufferSize(SOCKET_BUFFER_SIZE);
        newSocket.setReceiveBufferSize(SOCKET_BUFFER_SIZE);
        return newSocket;
    }

    private void send(DatagramSocket socket, AbstractSoapMessage msg, String targetAddress, int targetPort) {
        SDCLib.getInstance().getLogger().log(Level.FINEST, "Send datagram from {0}:{1} to {2}:{3}", new Object[]{
            socket.getLocalAddress().getHostAddress(),
            socket.getLocalPort(),
            targetAddress,
            targetPort
        });
        byte[] bytes = msg.createBytes();
        try {
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(targetAddress), targetPort);
            socket.send(packet);
        } catch (UnknownHostException ex) {
            SDCLib.getInstance().getLogger().log(Level.SEVERE, "Unicast send failed", ex);
        } catch (IOException ex) {
            SDCLib.getInstance().getLogger().log(Level.SEVERE, "Unicast send failed", ex);
        }
    }

    public void close() {
        senders.values().stream().forEach((next) -> {
            next.close();
        });
        senders.clear();
        recvers.stream().forEach((next) -> {
            next.close();
        });
        recvers.clear();
    }

}
