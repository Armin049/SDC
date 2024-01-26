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

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.ws.NIFManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.UDPCallback;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPOverUDPManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ByeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMetadataMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMetadataResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.HelloMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMatchesMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ResolveMatchesMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ResolveMessage;

public class DeviceDiscoManager {

    private static final String DISCOVERY_ADDR = "239.255.255.250";
    private static final int DISCOVERY_PORT = 3702;

    private final SOAPOverUDPManager udpManager;
    private final String bindInterface, eprAddr, xAddrs;
    private final MDPWSDevice.AppSequencer appSeq;
    private final AtomicInteger metadataVersion = new AtomicInteger();
    private final Timer timer = new Timer();

    private String manufacturer = "OR.NET", friendlyName = "OR.NET Demo device", modelName = "Demo model", serialNumber = "1.0", modelNumber = "1.0";

    public DeviceDiscoManager(SOAPOverUDPManager udpManager, String bindInterface, String eprAddr, String xAddrs, MDPWSDevice.AppSequencer appSeq) {
        this.udpManager = udpManager;
        this.bindInterface = bindInterface;
        this.eprAddr = eprAddr;
        this.xAddrs = xAddrs;
        this.appSeq = appSeq;
    }

    public void init() {
        UDPCallback discoveryCallback = new UDPCallback((host, port, asm) -> {
            try {
                String epr = getEprAddr();
                if (asm.getAction().equals(WSConstants.PROBE_ACTION)) {
                    ProbeMessage probe = new ProbeMessage(asm.getContent());
                    SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming UDP multicast message for device {0}, Action: {1}", new Object[]{epr, asm.getAction()});
                    handleProbe(host, port, probe);
                } else if (asm.getAction().equals(WSConstants.RESOLVE_ACTION)) {
                    ResolveMessage resolve = new ResolveMessage(asm.getContent());
                    SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming UDP multicast message for device {0}, Action: {1}", new Object[]{epr, asm.getAction()});
                    handleResolve(host, port, resolve);
                }
            } catch (Exception e) {
                SDCLib.getInstance().getLogger().log(Level.FINE, "Unable to process discovery message: {0}", e.toString());
            }
        });
        SDCLib.getInstance().getLogger().log(Level.FINE, "Init device discovery multicast server.");
        new NIFManager(bindInterface, true, true).applyVisitor((NetworkInterface ni, InetAddress addr, String addrStr) -> {
            udpManager.startRecvMulticast(addrStr, DISCOVERY_ADDR, DISCOVERY_PORT, discoveryCallback);
        });
        metadataVersion.incrementAndGet();
    }

    private void handleProbe(String host, int port, ProbeMessage probe) {
        ProbeMatchesMessage probeMatches = handleProbe(probe);
        if (probeMatches == null) {
            return;
        }
        SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing probe match to {0}:{1}", new Object[]{host, port});
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                udpManager.send(bindInterface, host, port, probeMatches, null);
            }
        }, ThreadLocalRandom.current().nextInt(10, 100));
    }

    private void handleResolve(String host, int port, ResolveMessage resolve) {
        ResolveMatchesMessage resolveMatches = handleResolve(resolve);
        if (resolveMatches == null) {
            return;
        }
        SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing resolve match to {0}:{1}", new Object[]{host, port});
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                udpManager.send(bindInterface, host, port, resolveMatches, null);
            }
        }, ThreadLocalRandom.current().nextInt(10, 100));
    }

    public ResolveMatchesMessage handleResolve(ResolveMessage resolve) {
        if (resolve.getAddress().equals(eprAddr)) {
            return new ResolveMatchesMessage(resolve, eprAddr, xAddrs, appSeq, metadataVersion.get());
        }
        return null;
    }

    public ProbeMatchesMessage handleProbe(ProbeMessage probe) {
        String types = probe.getTypes();
        if (types == null || (types.contains("Device"))) {
            return new ProbeMatchesMessage(probe, eprAddr, xAddrs, appSeq, metadataVersion.get());
        }
        return null;
    }

    public GetResponseMessage handleGet(GetMessage msg) {
        GetResponseMessage response = new GetResponseMessage(msg, eprAddr, getManufacturer(), getModelName(), getFriendlyName(), getModelNumber(), getSerialNumber());
        return response;
    }

    public GetMetadataResponseMessage handleGetMetadata(GetMetadataMessage msg) {
        GetMetadataResponseMessage response = new GetMetadataResponseMessage(msg, eprAddr);
        return response;
    }

    public void sendHello() {
        SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing announcement device up (hello): {0}", eprAddr);
        udpManager.send(bindInterface, DISCOVERY_ADDR, DISCOVERY_PORT, new HelloMessage(eprAddr, xAddrs, appSeq, metadataVersion.get()), null);
    }

    public void sendBye() {
        SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing announcement device down (bye): {0}", eprAddr);
        udpManager.send(bindInterface, DISCOVERY_ADDR, DISCOVERY_PORT, new ByeMessage(eprAddr, appSeq, metadataVersion.get()), null);
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the friendlyName
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * @param friendlyName the friendlyName to set
     */
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * @return the modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName the modelName to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the modelNumber
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * @param modelNumber the modelNumber to set
     */
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getEprAddr() {
        return eprAddr;
    }

}
