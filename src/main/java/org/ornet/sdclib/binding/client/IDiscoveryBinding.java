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

import java.util.Collection;
import java.util.TimerTask;
import javafx.collections.ObservableMap;
import org.ornet.sdclib.consumer.SDCConsumer;

public interface IDiscoveryBinding {

    public interface IConsumerJoinedHandler {

        void onConsumerJoined(SDCConsumer consumer);

    }

    /**
     * Start the continuous discovery process
     */
    void startContinuousDiscovery();

    /**
     * Stop the continuous discovery process
     */
    void stopContinuousDiscovery();

    /**
     * Regardless of the continuous scan interval, calling this function will
     * trigger an active scan process immediately
     */
    void forceDiscovery();

    /**
     * The scan interval in seconds
     *
     * @return The seconds
     */
    int getScanIntervalSeconds();

    /**
     * Implementation of the search timer taks
     *
     * @return The timer task
     */
    TimerTask getSearchTask();

    /**
     * The namespace used for discovery
     *
     * @return The namespace
     */
    String getNamespace();

    /**
     * Must be called whenever a new enpoint has been discovered
     *
     * @param consumer The consumer created for the endpoint
     */
    void onConsumerJoined(SDCConsumer consumer);

    /**
     * Can be called whenever an endpoint left
     *
     * @param epr The EPR of the consumer which left
     */
    void onConsumerLeft(String epr);

    /**
     * Return the consumer from the current list by EPR
     *
     * @param epr The EPR
     * @return The consumer, or null
     */
    SDCConsumer getConsumerByEPR(String epr);

    /**
     * Get the list of currently available consumers
     *
     * @return The list
     */
    Collection<SDCConsumer> getSDCConsumers();

    /**
     * Get an observable map of EPR and consumers
     *
     * @return The map
     */
    ObservableMap<String, SDCConsumer> getObservableMap();

    /**
     * Add a handler which will be invoked on first discovery
     *
     * @param handler The handler
     */
    void addConsumerJoinedFirstHandler(IConsumerJoinedHandler handler);

    /**
     * Add a handler which will be invoked on first discovery
     *
     * @param handler The handler
     * @deprecated This method will be removed, use {@link #addConsumerJoinedFirstHandler(IConsumerJoinedHandler) addConsumerJoinedFirstHandler} instead.
     */
    @Deprecated(forRemoval=true) default
    void addConsumerJoinedHandler(IConsumerJoinedHandler handler) {
        addConsumerJoinedHandler(handler);
    }

}
