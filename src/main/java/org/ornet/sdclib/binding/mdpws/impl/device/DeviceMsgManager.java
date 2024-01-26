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
import org.ornet.sdclib.binding.device.IProviderGetOperationBinding;
import org.ornet.sdclib.binding.device.IProviderSetOperationBinding;

public class DeviceMsgManager {

    private DeviceStreamingManager streamingManager;
    private DeviceDiscoManager discoManager;
    private DeviceSubscriptionManager subscrManager;
    private IProviderGetOperationBinding getOperationBinding;
    private IProviderSetOperationBinding setOperationBinding;
    private final Vertx vertx;

    public DeviceMsgManager(Vertx vertx) {
        this.vertx = vertx;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public void setStreamingManager(DeviceStreamingManager streamingManager) {
        this.streamingManager = streamingManager;
    }

    public DeviceStreamingManager getStreamingManager() {
        return streamingManager;
    }

    public void setSubscrManager(DeviceSubscriptionManager subscrManager) {
        this.subscrManager = subscrManager;
    }

    public DeviceSubscriptionManager getSubscrManager() {
        return subscrManager;
    }

    public void setDiscoManager(DeviceDiscoManager discoManager) {
        this.discoManager = discoManager;
    }

    public DeviceDiscoManager getDiscoManager() {
        return discoManager;
    }

    public void setGetOperationBinding(IProviderGetOperationBinding getOperationBinding) {
        this.getOperationBinding = getOperationBinding;
    }

    public void setSetOperationBinding(IProviderSetOperationBinding setOperationBinding) {
        this.setOperationBinding = setOperationBinding;
    }

    public IProviderGetOperationBinding getGetOperationBinding() {
        return getOperationBinding;
    }

    public IProviderSetOperationBinding getSetOperationBinding() {
        return setOperationBinding;
    }

}
