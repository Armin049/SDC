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

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ornet.cdm.WaveformStream;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.client.IConsumerEventSinkBinding;
import org.ornet.sdclib.binding.mdpws.impl.ws.NIFManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.UDPCallback;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPOverUDPManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeMessage;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;

public class ClientStreamingManager {

    private String streamAdr;
    private final SOAPOverUDPManager udpManager;
    private final String bindInterface;
    private final IConsumerEventSinkBinding eventSinkBinding;

    public ClientStreamingManager(IConsumerEventSinkBinding eventSinkBinding, SOAPOverUDPManager udpManager, String bindInterface) {
        this.udpManager = udpManager;
        this.bindInterface = bindInterface;
        this.eventSinkBinding = eventSinkBinding;
    }

    public void setStreamAdr(String streamAdr) {
        this.streamAdr = streamAdr;
    }

    public String getStreamAdr() {
        return streamAdr;
    }

    public void initStreaming() {
        try {
            URL soapUrl = new URL(streamAdr.replace("soap.udp", "http"));
            String mcastAdr = soapUrl.getHost();
            int mcastPort = soapUrl.getPort();
            UDPCallback streamingCallback = new UDPCallback((host, port, asm) -> {
                SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming stream packet, Action: {0}", asm.getAction());
                handleStream(new InvokeMessage(asm.getContent()));
            });
            SDCLib.getInstance().getLogger().log(Level.FINE, "Init client streaming multicast server.");
            new NIFManager(bindInterface, true, true).applyVisitor((NetworkInterface ni, InetAddress addr, String addrStr) -> {
                udpManager.startRecvMulticast(addrStr, mcastAdr, mcastPort, streamingCallback);
            });
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientStreamingManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleStream(InvokeMessage msg) {
        try {
            eventSinkBinding.onStreamReceived(msg.getMessageModelContent(WaveformStream.class));
        } catch (SOAPException | JAXBException e) {
            Logger.getLogger(ClientStreamingManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
