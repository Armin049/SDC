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
package org.ornet.sdclib;

import org.ornet.sdclib.binding.device.IDeviceBinding;
import org.ornet.sdclib.consumer.SDCServiceManager;
import org.ornet.sdclib.provider.SDCProvider;

public interface ISDCTransportLayerConfiguration<T> {

    /**
     * Start transport layer.
     */
    void startup();

    /**
     * Stop transport layer
     */
    void shutdown();

    /**
     * Get configuration detail
     *
     * @return
     */
    T getConfigurationDetail();

    /**
     * Get the service manager. Must always be the same instance.
     *
     * @return The service manager
     */
    SDCServiceManager getServiceManager();

    /**
     * The device binding used for SDC providers.
     *
     * @param provider The provider
     * @return The binding
     */
    IDeviceBinding getDeviceBinding(SDCProvider provider);

}
