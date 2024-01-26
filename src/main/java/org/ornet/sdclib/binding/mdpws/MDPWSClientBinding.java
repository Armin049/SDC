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

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.client.BICEPSClientBinding;
import org.ornet.sdclib.binding.client.BICEPSConsumerEventSinkBinding;
import org.ornet.sdclib.binding.client.IConsumerEventSinkBinding;
import org.ornet.sdclib.binding.client.IConsumerGetOperationBinding;
import org.ornet.sdclib.binding.client.IConsumerSetOperationBinding;
import org.ornet.sdclib.binding.mdpws.impl.client.ClientMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.client.ClientStreamingManager;
import org.ornet.sdclib.binding.mdpws.impl.client.ClientSubscriptionManager;
import org.ornet.sdclib.binding.mdpws.impl.client.MDPWSClient;
import org.ornet.sdclib.binding.mdpws.impl.client.MDPWSConsumerGetOperationBinding;
import org.ornet.sdclib.binding.mdpws.impl.client.MDPWSConsumerSetOperationBinding;
import org.ornet.sdclib.binding.mdpws.impl.ws.SDCClientVerticle;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPOverUDPManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMessage;
import org.ornet.sdclib.common.SyncEvent;
import org.ornet.sdclib.consumer.SDCConsumer;
import static com.bestingit.async.Task.*;

public class MDPWSClientBinding extends BICEPSClientBinding {

    private final String epr, xAddrs;
    private MDPWSClient client;
    private final MDPWSTransportLayerConfiguration tlc;
    private final Vertx vertx;
    private long timerID;
    private final AtomicBoolean connected = new AtomicBoolean(true);
    private final ClientMsgManager msgManager = new ClientMsgManager();
    private final SOAPOverUDPManager udpManager;
    private final BICEPSConsumerEventSinkBinding eventSinkBinding;
    private final SDCClientVerticle verticle;
    private String deploymentId;

    private static final int DIRECTED_PROBE_CYCLE = 5000;

    public MDPWSClientBinding(SDCConsumer consumer, String epr, String xAddrs) {
        super(consumer);
        tlc = SDCLib.getInstance().getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class);
        eventSinkBinding = new BICEPSConsumerEventSinkBinding(consumer);
        verticle = new SDCClientVerticle(msgManager, tlc.getConfigurationDetail().getServerOptions());
        this.epr = epr;
        this.vertx = tlc.getVertx();
        udpManager = new SOAPOverUDPManager(tlc);
        this.xAddrs = xAddrs;
    }

    public boolean initConnectionBlocking() {
        if(xAddrs == null || xAddrs.isEmpty() || xAddrs.isBlank())
            return false;

        client = new MDPWSClient(tlc, xAddrs, getEnpointReference(), vertx);
        boolean result = client.buildUpBlocking(msgManager);
        if (result) {
            msgManager.setStreamingManager(new ClientStreamingManager(eventSinkBinding, udpManager, tlc.getConfigurationDetail().getBindInterface()));
            msgManager.setGetOperationBinding(new MDPWSConsumerGetOperationBinding(client));
            msgManager.setSetOperationBinding(new MDPWSConsumerSetOperationBinding(client));
        } else {
            client.close();
        }
        return result;
    }

    @Override
    public boolean init() {
        connected.set(true);
        String trace = Arrays.toString(Thread.currentThread().getStackTrace());
        timerID = scheduleAsync(() -> {
            ProbeMessage msg = new ProbeMessage();

            client.postDevice(msg, res -> {
                connected.set(true);
                /*
                if (res.succeeded()) {
                    var result = res.result();
                    if (result.statusCode() != 200) {
                        SDCLib.getInstance().getLogger().log(Level.WARNING, "Target returned no success: {0} (code {1})", new Object[]{client.getCurrentDeviceXAddr(), result.statusCode()});
                        connected.set(false);
                        return;
                    }
                    connected.set(true);
                    SDCLib.getInstance().getLogger().log(Level.FINER, "Directed probe confirmed device online: {0}", client.getCurrentDeviceXAddr());

                } else {
                    var exc = res.cause();
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "Target unreachable: {0} ({1})", new Object[]{client.getCurrentDeviceXAddr(), exc.getMessage()});
                    SDCLib.getInstance().logException(exc, trace);
                    connected.set(false);
                }

                */
            });

        }, DIRECTED_PROBE_CYCLE, true);
        final JsonObject config = new JsonObject();
        config.put("http.host", client.getBaseAddress());
        config.put("http.port", client.getBasePort());
        boolean isSecure = tlc.getConfigurationDetail().getServerOptions().isSsl();
        final ClientSubscriptionManager clientSubscriptionManager = new ClientSubscriptionManager(eventSinkBinding, client, vertx, connected, isSecure);
        msgManager.setSubscriptionManager(clientSubscriptionManager);
        SyncEvent deployEvent = new SyncEvent();
        vertx.deployVerticle(verticle, new DeploymentOptions().setWorker(true).setConfig(config),
                res -> {
                    deploymentId = res.result();
                    SDCLib.getInstance().getLogger().log(Level.INFO, "Client verticle deployed: {0}", res.succeeded());
                    if (!res.succeeded()) {
                        SDCLib.getInstance().getLogger().log(Level.SEVERE, "Client verticle error: {0}", res.cause().toString());
                    } else {
                        connected.set(true);
                        deployEvent.set(res.succeeded());
                    }
                });
        if (!deployEvent.wait(10000)) {
            throw new IllegalStateException("Client verticle not deployed in time!");
        }
        SDCLib.getInstance().getLogger().log(Level.FINE, "Subscribing OIR reports.");
        clientSubscriptionManager.initOperationInvoked();
        return true;
    }

    @Override
    public void close() {
        cancel(timerID);
        msgManager.getSubscriptionManager().close();
        udpManager.close();
        if (deploymentId != null) {
            vertx.undeploy(deploymentId);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MDPWSClientBinding.class.getName()).log(Level.SEVERE, null, ex);
        }
        client.close();
    }

    @Override
    public boolean isConnected() {
        return connected.get();
    }

    @Override
    public String getEnpointReference() {
        return epr;
    }

    @Override
    public IConsumerGetOperationBinding getGetOperationBinding() {
        return msgManager.getGetOperationBinding();
    }

    @Override
    public IConsumerSetOperationBinding getSetOperationBinding() {
        return msgManager.getSetOperationBinding();
    }

    @Override
    public IConsumerEventSinkBinding getEventSinkBinding() {
        return eventSinkBinding;
    }

    @Override
    public void initEventing() {
        msgManager.getSubscriptionManager().init();
    }

    @Override
    public void deInitEventing() {
        msgManager.getSubscriptionManager().deInit();
    }

	@Override
	public String getXAdress() {
		return this.xAddrs;
	}

}
