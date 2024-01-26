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

import static com.bestingit.async.Task.sleep;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.util.List;
import java.util.logging.Level;
import org.ornet.cdm.AbstractState;
import org.ornet.cdm.LocalizedText;
import org.ornet.cdm.MdDescription;
import org.ornet.cdm.MdsDescriptor;
import org.ornet.cdm.RealTimeSampleArrayMetricState;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.device.BICEPSDeviceBinding;
import org.ornet.sdclib.binding.device.IProviderEventSourceBinding;
import org.ornet.sdclib.binding.mdpws.impl.device.MDPWSDevice;
import org.ornet.sdclib.binding.mdpws.impl.device.MDPWSProviderEventSourceBinding;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.SDCDeviceVerticle;
import org.ornet.sdclib.common.SyncEvent;
import org.ornet.sdclib.provider.SDCProvider;

public class MDPWSDeviceBinding extends BICEPSDeviceBinding {

    private final MDPWSDevice device;
    private final DeviceMsgManager msgManager;
    private final MDPWSProviderEventSourceBinding eventSourceBinding;
    private final SDCDeviceVerticle verticle;
    private final MDPWSTransportLayerConfiguration defaultTransportLayerConfig;
    private String deploymentId;
    private final SDCProvider provider;
    private final Vertx vertx;

    public MDPWSDeviceBinding(SDCProvider provider) {
        super(provider);
        this.provider = provider;
        defaultTransportLayerConfig = (MDPWSTransportLayerConfiguration) provider.getTransportLayerConfiguration();
        this.vertx = defaultTransportLayerConfig.getVertx();
        msgManager = new DeviceMsgManager(this.vertx);
        msgManager.setGetOperationBinding(getGetOperationBinding());
        msgManager.setSetOperationBinding(getSetOperationBinding());
        device = new MDPWSDevice(defaultTransportLayerConfig, msgManager, this.vertx, getNamespace());
        eventSourceBinding = new MDPWSProviderEventSourceBinding(provider, msgManager);
        verticle = new SDCDeviceVerticle(msgManager, defaultTransportLayerConfig.getConfigurationDetail().getServerOptions());
    }

    @Override
    public void startBinding() {
        device.start();
        final JsonObject config = new JsonObject();
        config.put("http.host", defaultTransportLayerConfig.getConfigurationDetail().getBindInterface());
        config.put("http.port", device.getBasePort());
        SyncEvent deployEvent = new SyncEvent();
        vertx.deployVerticle(verticle, new DeploymentOptions().setWorker(true).setConfig(config),
                res -> {
                    deploymentId = res.result();
                    // Announce device up, support probe / resolve
                    device.startDiscovery();
                    // Map model metadata to DPWS metadata
                    initTransportMetadataMapping();
                    SDCLib.getInstance().getLogger().log(Level.INFO, "Device verticle deployed: {0}", res.succeeded());
                    msgManager.getDiscoManager().sendHello();
                    if (!res.succeeded()) {
                        SDCLib.getInstance().getLogger().log(Level.SEVERE, "Device verticle error: {0}", res.cause().toString());
                    } else {
                        deployEvent.set();
                    }
                });
        if (!deployEvent.wait(10000)) {
            throw new IllegalStateException("Device verticle not deployed in time!");
        }
    }

    @Override
    public void stopBinding() {
        msgManager.getDiscoManager().sendBye();
        if (deploymentId != null) {
            vertx.undeploy(deploymentId);
        }
        sleep(3000);
        device.stop();
    }

    private void initTransportMetadataMapping() {
        MdDescription mdd = provider.getMDDescription();
        for (MdsDescriptor mds : mdd.getMds()) {
            MdsDescriptor.MetaData meta = mds.getMetaData();
            if (meta == null) {
                continue;
            }
            List<LocalizedText> text = meta.getManufacturer();
            if (text != null && text.size() > 0) {
                final String value = text.get(0).getValue();
                if (value != null) {
                    msgManager.getDiscoManager().setManufacturer(value);
                    msgManager.getDiscoManager().setFriendlyName(value);
                }
            }
            text = meta.getModelName();
            if (text != null && text.size() > 0) {
                final String value = text.get(0).getValue();
                if (value != null) {
                    msgManager.getDiscoManager().setModelName(value);
                    if (msgManager.getDiscoManager().getFriendlyName() != null && msgManager.getDiscoManager().getFriendlyName().length() > 0) {
                        msgManager.getDiscoManager().setFriendlyName(msgManager.getDiscoManager().getFriendlyName() + " - " + value);
                    } else {
                        msgManager.getDiscoManager().setFriendlyName(value);
                    }
                }
            }
            List<String> str = meta.getSerialNumber();
            if (str != null && str.size() > 0) {
                final String value = str.get(0);
                if (value != null) {
                    msgManager.getDiscoManager().setSerialNumber(value);
                }
            }
            String s = meta.getModelNumber();
            if (s != null) {
                msgManager.getDiscoManager().setModelNumber(s);
            }
        }
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public void initState(AbstractState state) {
        if (state instanceof RealTimeSampleArrayMetricState) {
            device.initStreaming();
        }
    }

    @Override
    public boolean isRunning() {
        return device.getRunning().get();
    }

    @Override
    public void setEndpointReference(String epr) {
        device.setEpr(epr);
    }

    @Override
    public String getEnpointReference() {
        return device.getEpr();
    }

    @Override
    public IProviderEventSourceBinding getEventSourceBinding() {
        return eventSourceBinding;
    }

}
