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
package org.ornet.sdclib.binding.mdpws.impl.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceStreamingManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMetadataMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMetadataResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetResponseMessage;
import org.ornet.sdclib.common.SyncEvent;

public class MDPWSClient {

    public static final int TIMEOUT = 10000;

    private final LinkedList<String> xAddrs = new LinkedList<>();
    private final Vertx vertx;
    private final MDPWSTransportLayerConfiguration tlc;
    private final String epr;
    private String baseAddress;
    private int basePort;
    private boolean closed;
    private final WebClient client;
    private final HttpClientOptions clientOptions;
    // Service ID -> address
    private Map<String, String> hosted;
    private final Object hostedLock = new Object();

    public MDPWSClient(MDPWSTransportLayerConfiguration tlc, String xAddrs, String epr, Vertx vertx) {
        this.xAddrs.addAll(Arrays.asList(xAddrs.split(" ")));
        this.epr = epr;
        this.vertx = vertx;
        this.tlc = tlc;
        this.clientOptions = tlc.getConfigurationDetail().getClientOptions();
        this.client = WebClient.wrap(vertx.createHttpClient(clientOptions));
    }

    public Vertx getVertx() {
        return vertx;
    }

    public void close() {
        if (!closed) {
            client.close();
            closed = true;
        }
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public int getBasePort() {
        return basePort;
    }

    public HttpClientOptions getOptions() {
        return clientOptions;
    }

    /*
     * Overriding default InetAddress.isReachable() method to add 2 more arguments port and timeout value
     */
    private boolean crunchifyAddressReachable(String address, int port, int timeout) {
        try {
            try (Socket crunchifySocket = new Socket()) {
                // Connects this socket to the server with a specified timeout value.
                crunchifySocket.connect(new InetSocketAddress(address, port), timeout);
            }

            // Return true if connection successful
            return true;
        } catch (IOException exception) {
            Logger.getLogger(MDPWSClient.class.getName()).log(Level.FINEST, "Socket not able to connect to: {0}", address + ":" + port);
            return false;
        }
    }

    public boolean reorderToReachable(List<String> addrs, boolean initBaseAddress) {
        String reachableAddr = null;
        for (String next : addrs) {
            try {
                URL url = new URL(next);
                if (crunchifyAddressReachable(url.getHost(), url.getPort(), getTlc().getConfigurationDetail().getCommTimeout())) {
                    SDCLib.getInstance().getLogger().log(Level.FINE, "Found reachable address: {0}", next);
                    reachableAddr = next;
                    final Socket socket = new Socket(url.getHost(), url.getPort());
                    if (initBaseAddress) {
                        String bindInterface = getTlc().getConfigurationDetail().getBindInterface();
                        baseAddress = socket.getLocalAddress().getHostAddress();
                        // Check if local address complies with bind interface
                        if (bindInterface.equals("0.0.0.0") || bindInterface.equals(baseAddress)) {
                            socket.close();
                            break;
                        }
                    } else {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(MDPWSClient.class.getName()).log(Level.FINE, "Not reachable: {0}", next);
            }
        }
        if (reachableAddr != null) {
            addrs.remove(reachableAddr);
            addrs.add(0, reachableAddr);
            return true;
        }
        return false;
    }

    public boolean buildUpBlocking(ClientMsgManager msgManager) {
        if (!reorderToReachable(xAddrs, true)) {
            SDCLib.getInstance().getLogger().log(Level.WARNING, "No reachable device found for {0}", epr);
            return false;
        }
        basePort = getTlc().getConfigurationDetail().extractNextPort();
        SDCLib.getInstance().getLogger().log(Level.FINE, "Remote endpoint build up for: {0}", xAddrs.get(0));
        // Get device & services info
        SyncEvent event = new SyncEvent();
        String trace = Arrays.toString(Thread.currentThread().getStackTrace());
        // xAddrs have been reordered to reachable, the first one is the one we use as EPR to
        String toEpr = xAddrs.get(0);
        postDevice(new GetMessage(toEpr), (res) -> {
            if (res.succeeded()) {
                var buf = res.result().body();
                GetResponseMessage response = new GetResponseMessage(buf);
                if (!response.getAction().equals(WSConstants.GET_RESPONSE_ACTION)) {
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "Suspicious response action received: {0}", response.getAction());
                }
                SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming message for client, Action: {0}", response.getAction());
                synchronized (hostedLock) {
                    hosted = new HashMap<>();
                    Map<String[], List<String>> serviceHosted = response.getServiceHosted();
                    serviceHosted.entrySet().forEach((next) -> {
                        if (!reorderToReachable(next.getValue(), false)) {
                            SDCLib.getInstance().getLogger().log(Level.WARNING, "No reachable service: {0}", next.getKey()[0]);
                        } else {
                            final String[] types = next.getKey()[1].split("\\s");
                            final String eprAdr = next.getValue().get(0);
                            for (String type : types) {
                                String typeTrimmed = type.trim();
                                SDCLib.getInstance().getLogger().log(Level.FINER, "Available port type: {0}", typeTrimmed);
                                hosted.put(typeTrimmed, eprAdr);
                                if (type.equals(WSConstants.PORT_TYPE_WAV)) {
                                    getStreamingMetadata(typeTrimmed, eprAdr, msgManager);
                                }
                            }
                        }
                    });
                }
                event.set();
            }
        });
        if (!event.wait(TIMEOUT)) {
            SDCLib.getInstance().getLogger().log(Level.WARNING, "Metadata request failed for: {0}", epr);
            return false;
        }
        return true;
    }

    private void getStreamingMetadata(String type, String eprAdr, ClientMsgManager msgManager) {
        String trace = Arrays.toString(Thread.currentThread().getStackTrace());
        postService(type, new GetMetadataMessage(eprAdr), (resMeta) -> {
            if (resMeta.succeeded()) {
                var buf = resMeta.result().body();
                GetMetadataResponseMessage metaResponse = new GetMetadataResponseMessage(buf);
                if (!metaResponse.getAction().equals(WSConstants.GET_META_RESPONSE_ACTION)) {
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "Suspicious response action received: {0}", metaResponse.getAction());
                }
                SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming message for client, Action: {0}", metaResponse.getAction());
                String streamAdr = metaResponse.getStreamAddress();
                if (streamAdr == null) {
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "No stream address provided: {0} - using default", eprAdr);
                    streamAdr = "soap.udp://" + DeviceStreamingManager.MCAST_IP + ":" + DeviceStreamingManager.MCAST_PORT;
                }
                SDCLib.getInstance().getLogger().log(Level.FINE, "Using streaming endpoint: {0}", streamAdr);
                msgManager.getStreamingManager().setStreamAdr(streamAdr);
                msgManager.getStreamingManager().initStreaming();
            } else {
                var exc = resMeta.cause();
                SDCLib.getInstance().getLogger().log(Level.WARNING, "Target unreachable: {0} ({1})", new Object[]{eprAdr, exc.getMessage()});
                SDCLib.getInstance().logException(exc, trace);
            }
        });
    }

    public WebClient getClient() {
        return client;
    }

    public String getCurrentDeviceXAddr() {
        return xAddrs.getFirst();
    }

    public void postService(String servicePortType, AbstractSoapMessage msg, Handler<AsyncResult<HttpResponse<Buffer>>> hndlr) {
        String resolvedAddress;
        synchronized (hostedLock) {
            resolvedAddress = hosted.get(servicePortType);
        }
        if (resolvedAddress == null) {
        	SDCLib.getInstance().getLogger().log(Level.SEVERE, "xAddrs " + this.getCurrentDeviceXAddr());
        	hosted.forEach((key,value) -> SDCLib.getInstance().getLogger().log(Level.SEVERE, "Hosted " + key.toString() + " " + value.toString()));
       	
            SDCLib.getInstance().getLogger().log(Level.SEVERE, "Unknown remote port type: " + servicePortType);
            return;
        }
        
        SDCLib.getInstance().getLogger().log(Level.INFO, "xAddrs " + this.getCurrentDeviceXAddr());
        SDCLib.getInstance().getLogger().log(Level.INFO, "Remote port type: " + resolvedAddress);
        
        if (msg.getTo().length() == 0)
            msg.setTo(resolvedAddress);
        post(resolvedAddress, msg, hndlr);
    }

    public void postServiceRaw(String servicePortType, String string, Handler<AsyncResult<HttpResponse<Buffer>>> hndlr) {
        String resolvedAddress;
        synchronized (hostedLock) {
            resolvedAddress = hosted.get(servicePortType);
        }
        postRaw(resolvedAddress, Buffer.buffer(string), hndlr);
    }

    public void postDevice(AbstractSoapMessage msg, Handler<AsyncResult<HttpResponse<Buffer>>> hndlr) {
        String devAdr = getCurrentDeviceXAddr();
        post(devAdr, msg, hndlr);
    }

    public void post(String absAddr, AbstractSoapMessage msg, Handler<AsyncResult<HttpResponse<Buffer>>> hndlr) {
        final Buffer buffer = msg.createBuffer();
        postRaw(absAddr, buffer, hndlr);
    }

    private void postRaw(String absAddr, Buffer buffer, Handler<AsyncResult<HttpResponse<Buffer>>> hndlr) {
        SDCLib.getInstance().getLogger().log(Level.FINEST, "HTTP client side post to address: {0}", absAddr);
        client.postAbs(absAddr)
            .timeout(tlc.getConfigurationDetail().getCommTimeout())
            .putHeader("Content-Type", "application/soap+xml")
            .sendBuffer(buffer, h -> { hndlr.handle(h); });
    }

    public MDPWSTransportLayerConfiguration getTlc() {
        return tlc;
    }

}