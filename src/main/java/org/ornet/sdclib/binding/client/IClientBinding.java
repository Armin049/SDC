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
package org.ornet.sdclib.binding.client;

public interface IClientBinding {

    /**
     * Initialize binding.
     *
     * @return True, if initialized.
     */
    boolean init();

    /**
     * Initialize eventing.
     */
    void initEventing();

    /**
     * Deinitialize eventing.
     */
    void deInitEventing();

    /**
     * Close connection to device endpoint.
     */
    void close();

    /**
     * Return if currently connected to device endpoint.
     *
     * @return True, if connected
     */
    boolean isConnected();

    /**
     * Get the device's EPR
     *
     * @return The EPR
     */
    String getEnpointReference();
    
    /**
     * Get the device's XAdress
     *
     * @return The XAdress
     */
    String getXAdress();

    /**
     * The GET operations. Must invoke transport-specific implementation.
     *
     * @return The operations
     */
    IConsumerGetOperationBinding getGetOperationBinding();

    /**
     * The SET operations. Must invoke transport-specific implementation.
     *
     * @return The operations
     */
    IConsumerSetOperationBinding getSetOperationBinding();

    /**
     * The EVENT sink. Must be invoked by transport-specific implementation.
     *
     * @return The operations
     */
    IConsumerEventSinkBinding getEventSinkBinding();

}
