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

import io.vertx.core.Vertx;
import org.ornet.sdclib.ISDCTransportLayerConfiguration;
import org.ornet.sdclib.binding.device.IDeviceBinding;
import org.ornet.sdclib.common.SyncEvent;
import org.ornet.sdclib.consumer.SDCServiceManager;
import org.ornet.sdclib.provider.SDCProvider;

public class MDPWSTransportLayerConfiguration implements ISDCTransportLayerConfiguration<MDPWSTransportLayerDetail> {

    private final MDPWSTransportLayerDetail detail = new MDPWSTransportLayerDetail();
    private final SDCServiceManager serviceManager = new SDCServiceManager(new MDPWSDiscoveryBinding());
    private final Vertx vertx = Vertx.vertx();

    @Override
    public void startup() {
    }

    @Override
    public void shutdown() {
        SyncEvent event = new SyncEvent();
        try {
            vertx.close((hndlr) -> {
                event.set(hndlr.succeeded());
            });
        } catch (IllegalStateException e) {
            // Ignore if already undeployed
        }
        event.wait(detail.getCommTimeout());
    }

    public Vertx getVertx() {
        return vertx;
    }

    @Override
    public MDPWSTransportLayerDetail getConfigurationDetail() {
        return detail;
    }

    @Override
    public SDCServiceManager getServiceManager() {
        return serviceManager;
    }

    @Override
    public IDeviceBinding getDeviceBinding(SDCProvider provider) {
        return new MDPWSDeviceBinding(provider);
    }

}
