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
package org.ornet.sdclib.binding.mdpws.impl.device;

import io.vertx.core.Vertx;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.binding.mdpws.impl.ws.NIFManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPOverUDPManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.routes.DeviceRoute;

public class MDPWSDevice {

    public static class AppSequencer {

        private final AtomicInteger instanceId = new AtomicInteger();
        private final AtomicLong messageNumber = new AtomicLong();

        public long getNextMsgNo() {
            messageNumber.set(messageNumber.get() + 1);
            return messageNumber.get();
        }

        public int getInstanceId() {
            return instanceId.get();
        }

    }

    private final MDPWSTransportLayerConfiguration tlc;
    private final SOAPOverUDPManager udpManager;
    private final AtomicBoolean running = new AtomicBoolean();
    private final AppSequencer appSeq = new AppSequencer();

    private final DeviceMsgManager msgManager;

    private DeviceStreamingManager strManager;
    private DeviceDiscoManager discoManager;
    private DeviceSubscriptionManager subscrManager;
    private String epr, xaddrs;
    private int basePort;
    private final Vertx vertx;
    private final String namespace;

    public MDPWSDevice(MDPWSTransportLayerConfiguration tlc, DeviceMsgManager msgManager, Vertx vertx, String namespace) {
        this.tlc = tlc;
        this.msgManager = msgManager;
        this.vertx = vertx;
        this.namespace = namespace;
        udpManager = new SOAPOverUDPManager(tlc);
        appSeq.instanceId.set(Math.abs(ThreadLocalRandom.current().nextInt()));
        appSeq.messageNumber.set(0);
    }

    public AppSequencer getAppSeq() {
        return appSeq;
    }

    public void setEpr(String epr) {
        this.epr = epr;
    }

    public String getEpr() {
        return epr;
    }

    public int getBasePort() {
        return basePort;
    }

    
    public void start() throws IllegalStateException {
        if (epr == null) {
            throw new IllegalStateException("EPR not set!");
        }
        basePort = tlc.getConfigurationDetail().extractNextPort();
        StringBuilder sb = new StringBuilder();
        final String bindInterface = tlc.getConfigurationDetail().getBindInterface();
        boolean ssl = tlc.getConfigurationDetail().getServerOptions().isSsl();
        final String http = ssl ? "https://" : "http://";
        new NIFManager(bindInterface, true, true).applyVisitor((NetworkInterface ni, InetAddress addr, String addrStr) -> {
            sb.append(http).append(addrStr).append(":").append(basePort).append("/" + DeviceRoute.REALM + " ");
        });
        xaddrs = sb.toString().trim();
        if (xaddrs.length() == 0) {
            throw new IllegalStateException("Fatal error, no available network interfaces found for binding: " + bindInterface);
        }
        SDCLib.getInstance().getLogger().log(Level.FINE, "Device xaddrs are: {0}", xaddrs);
        initSubscriptions();
        running.set(true);
    }

    public AtomicBoolean getRunning() {
        return running;
    }

    public void startDiscovery() {
        discoManager = new DeviceDiscoManager(udpManager, tlc.getConfigurationDetail().getBindInterface(), epr, xaddrs, appSeq);
        msgManager.setDiscoManager(discoManager);
        discoManager.init();
    }

    private void initSubscriptions() {
        subscrManager = new DeviceSubscriptionManager(xaddrs, vertx, tlc);
        msgManager.setSubscrManager(subscrManager);
        subscrManager.init();
    }

    public void stop() {
        udpManager.close();
        subscrManager.close();
        strManager = null;
        subscrManager = null;
        discoManager = null;
        running.set(false);
    }

    public void initStreaming() {
        if (strManager == null) {
            SDCLib.getInstance().getLogger().log(Level.FINE, "Initializing device streaming endpoint.");
            String bindInterface = tlc.getConfigurationDetail().getBindInterface();
            strManager = new DeviceStreamingManager(udpManager, bindInterface, appSeq);
            msgManager.setStreamingManager(strManager);
        }
    }

}
