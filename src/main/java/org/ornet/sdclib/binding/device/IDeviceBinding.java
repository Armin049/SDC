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
package org.ornet.sdclib.binding.device;

import org.ornet.cdm.AbstractState;

/**
 * Implementations of this interface represent a bridge between the
 * transport-specific and transport-agnostic layer.
 *
 * @author besting
 */
public interface IDeviceBinding {

    /**
     * Initializes the devide.
     *
     * @return True, if initialized
     */
    boolean init();

    /**
     * State-specific initialization
     *
     * @param state The state
     */
    void initState(AbstractState state);

    /**
     * Called when a state has been updated
     *
     * @param state The state
     */
    void onStateChanged(AbstractState state);

    /**
     * Device model namespace
     *
     * @return the namespace
     */
    String getNamespace();

    /**
     * Start the transport-specific device endpoint.
     */
    void start();

    /**
     * Stop the transport-specific device endpoint.
     */
    void stop();

    /**
     * Check if the device is running.
     *
     * @return True, if running
     */
    boolean isRunning();

    /**
     * Set the device's EPR
     *
     * @param epr The EPR
     */
    void setEndpointReference(String epr);

    /**
     * Get the device's EPR
     *
     * @return The EPR
     */
    String getEnpointReference();

    /**
     * The GET operations. Must be invoked by transport-specific implementation.
     *
     * @return The operations
     */
    IProviderGetOperationBinding getGetOperationBinding();

    /**
     * The SET operations. Must be invoked by transport-specific implementation.
     *
     * @return The operations
     */
    IProviderSetOperationBinding getSetOperationBinding();

    /**
     * The EVENT operations. Are invoked by the framework to call into
     * transport-specific implementation.
     *
     * @return The operations
     */
    IProviderEventSourceBinding getEventSourceBinding();

}
