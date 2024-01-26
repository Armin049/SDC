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
package org.ornet.sdclib.binding.mdpws;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.client.BICEPSDiscoveryBinding;
import org.ornet.sdclib.binding.mdpws.impl.ws.NIFManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.UDPCallback;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPOverUDPManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ByeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.HelloMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMatchesMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ResolveMatchesMessage;
import org.ornet.sdclib.common.MaxHashMap;
import org.ornet.sdclib.consumer.SDCConsumer;
import static com.bestingit.async.Task.*;

public class MDPWSDiscoveryBinding extends BICEPSDiscoveryBinding {

    private static final String DISCOVERY_ADDR = "239.255.255.250";
    private static final int DISCOVERY_PORT = 3702;

    private final int SCAN_INTERVAL_SECONDS = 20;
    private SOAPOverUDPManager udpManager;
    private MDPWSTransportLayerConfiguration tlc;
    private UDPCallback unicastCallback;
    private final Map<Long, Integer> instanceMeta = Collections.synchronizedMap(new MaxHashMap<>(1024));
    private final Set<String> activeDiscoveries = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Semaphore searchTaskSemaphore = new Semaphore(1);

    @Override
    protected void init() {
        tlc = SDCLib.getInstance().getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class);
        udpManager = new SOAPOverUDPManager(tlc);
        initUDP();
    }

    @Override
    protected void close() {
        if (udpManager != null) {
            udpManager.close();
        }
    }

    @Override
    public int getScanIntervalSeconds() {
        return SCAN_INTERVAL_SECONDS;
    }

    @Override
    public TimerTask getSearchTask() {
        return new TimerTask() {
            @Override
            public void run() {
                async(() -> {
                    if (searchTaskSemaphore.tryAcquire()) {
                        try {
                            ProbeMessage msg = new ProbeMessage();
                            SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing client discovery attempt, Action: {0}", msg.getAction());
                            new NIFManager(tlc.getConfigurationDetail().getBindInterface(), true, true).applyVisitor((NetworkInterface ni, InetAddress addr, String addrStr) -> {
                                udpManager.send(addrStr, DISCOVERY_ADDR, DISCOVERY_PORT, msg, unicastCallback);
                            });
                        } finally {
                            searchTaskSemaphore.release();
                        }
                    }
                });
            }
        };
    }

    private void initUDP() {
        // Init multicast callback (hello, bye) and start to listen
        UDPCallback discoveryCallback = new UDPCallback((host, port, asm) -> {
            switch (asm.getAction()) {
                case WSConstants.HELLO_ACTION: {
                    HelloMessage msg = new HelloMessage(asm.getContent());
                    SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming UDP multicast message for client, Action: {0}", asm.getAction());
                    handleHello(msg);
                    break;
                }
                case WSConstants.BYE_ACTION: {
                    ByeMessage msg = new ByeMessage(asm.getContent());
                    SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming UDP multicast message for client, Action: {0}", asm.getAction());
                    handleBye(msg);
                    break;
                }
                default:
                    break;
            }
        });
        SDCLib.getInstance().getLogger().log(Level.FINE, "Init client discovery multicast server.");
        new NIFManager(tlc.getConfigurationDetail().getBindInterface(), true, true).applyVisitor((NetworkInterface ni, InetAddress addr, String addrStr) -> {
            udpManager.startRecvMulticast(addrStr, DISCOVERY_ADDR, DISCOVERY_PORT, discoveryCallback);
        });
        // Init unicast callback (probe, probe-matches)
        unicastCallback = new UDPCallback((host, port, asm) -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming UDP response for client, Action: {0}", asm.getAction());
            switch (asm.getAction()) {
                case WSConstants.PROBE_MATCHES_ACTION: {
                    ProbeMatchesMessage res = new ProbeMatchesMessage(asm.getContent());
                    handleProbeMatches(res);
                    break;
                }
                case WSConstants.RESOLVE_MATCHES_ACTION: {
                    ResolveMatchesMessage res = new ResolveMatchesMessage(asm.getContent());
                    handlResolveMatches(res);
                    break;
                }
                default:
                    break;
            }
        });
    }

    private void handleHello(HelloMessage msg) {
        String sdcTypes = msg.getTypes();
        discoverSDC(sdcTypes, msg.getEPR(), msg.getXaddrs(), msg.getInstanceId(), msg.getMetadataVersion());
    }

    private void handleProbeMatches(ProbeMatchesMessage msg) {
        String sdcTypes = msg.getTypes();
        // Check for missing AppSequence and Instance Id attribute
        long instanceId = 0;
        try {
            instanceId = msg.getInstanceId();
        }
        catch(Exception e) {
            SDCLib.getInstance().getLogger().log(Level.FINE, "Missing instance Id in ProbeMatches!");
        }
        discoverSDC(sdcTypes, msg.getEPR(), msg.getXaddrs(), instanceId, msg.getMetadataVersion());
    }

    private void handlResolveMatches(ResolveMatchesMessage msg) {
        String sdcTypes = msg.getTypes();
        discoverSDC(sdcTypes, msg.getEPR(), msg.getXaddrs(), msg.getInstanceId(), msg.getMetadataVersion());
    }

    private void discoverSDC(String sdcTypes, String epr, String xAddrs, long instanceId, int metadataVersion) {
        if (activeDiscoveries.contains(epr)) {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Discovery active, skipping: {0}", epr);
            return;
        }
        if (sdcTypes != null && sdcTypes.contains("MedicalDevice")) {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Discovery process started for: {0}", epr);
            // Use internal map instead of observable map to store instances permanently once discovered
            final SDCConsumer consumer = getDiscoveredConsumers().get(epr);
            if (consumer != null && !consumer.isClosed()) {
                Integer meta = instanceMeta.get(instanceId);
                if (meta != null && meta == metadataVersion) {
                    SDCLib.getInstance().getLogger().log(Level.FINE, "Endpoint known and up to date: {0}", epr);
                    return;
                }
            }
            activeDiscoveries.add(epr);
            tlc.getVertx().executeBlocking(future -> {
                try {
                    // Check for existing instance in list, otherwise create new
                    SDCConsumer newConsumer = getDiscoveredConsumers().get(epr);
                    boolean seen = false;
                    if (newConsumer == null) {
                        newConsumer = new SDCConsumer();
                    } else {
                        try {
                            // We've already discovered the consumer and don't want to create a new instance
                            // since this could mess up application domain logic.
                            seen = true;
                            closeConsumer(consumer);
                        } catch (Exception ex) {
                            SDCLib.getInstance().getLogger().log(Level.SEVERE, null, ex);
                        }
                    }
                    MDPWSClientBinding binding = new MDPWSClientBinding(newConsumer, epr, xAddrs);
                    newConsumer.setClientBinding(binding);
                    if (binding.initConnectionBlocking()) {
                        try {
                            instanceMeta.put(instanceId, metadataVersion);
                            openConsumer(newConsumer);
                            if (seen) {
                                // In a new instance, eventing initialization will be triggered when handlers are registered.
                                // We need to do this again, in case a former instance will be used again.
                                newConsumer.internalCheckEventingInit();
                            }
                            onConsumerJoined(newConsumer);
                            // Use internal map instead of observable map to store instances permanently once discovered
                            getDiscoveredConsumers().put(epr, newConsumer);
                        } catch (Exception e) {
                            future.complete("Binding connection init failed due to internal exception: " + e.getMessage());
                        }
                        future.complete("Binding connection init succeeded.");
                    } else {
                        future.complete("Binding connection init failed.");
                    }
                }catch (Exception e) {
                    SDCLib.getInstance().getLogger().log(Level.SEVERE, "Binding connection init exception", e);
                    future.complete("Binding connection init due to internal exception: " + e.getMessage());
                }
            }, res -> {
                activeDiscoveries.remove(epr);
                if (res.result() == null) {
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "Incomplete discovery.");
                    return;
                }
                SDCLib.getInstance().getLogger().log(Level.FINE, res.result().toString());
            });
        }
    }

    private void handleBye(ByeMessage bye) {
        onConsumerLeft(bye.getEPR());
    }

}