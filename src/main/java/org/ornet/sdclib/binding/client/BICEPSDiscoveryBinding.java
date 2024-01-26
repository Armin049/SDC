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

import static com.bestingit.async.Task.blocking;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.consumer.SDCLifecycleHandler;
import org.ornet.sdclib.consumer.SDCConsumer;

public abstract class BICEPSDiscoveryBinding implements IDiscoveryBinding {

    private static final long TIMEOUT_REMOVE_SECONDS = 60;
    private final ObservableMap<String, SDCConsumer> sdcConsumers = FXCollections.observableMap(new ConcurrentHashMap<>());
    private final ConcurrentHashMap<String, SDCConsumer> discoveredConsumers = new ConcurrentHashMap<>();
    private final Set<IConsumerJoinedHandler> joinedHandlers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private Timer defaultTimer = new Timer();
    private TimerTask timerTaskDefaultSearch;
    private SDCLifecycleHandler lifecycleHandler;

    @Override
    public void startContinuousDiscovery() {
        init();
        sdcConsumers.clear();
        defaultTimer.cancel();
        defaultTimer = new Timer();
        if (timerTaskDefaultSearch == null) {
            timerTaskDefaultSearch = getSearchTask();
        }
        defaultTimer.schedule(timerTaskDefaultSearch, 1000, getScanIntervalSeconds() * 1000);
    }

    @Override
    public void stopContinuousDiscovery() {
        close();
        defaultTimer.cancel();
        sdcConsumers.clear();
        discoveredConsumers.clear();
    }

    @Override
    public void forceDiscovery() {
        if (timerTaskDefaultSearch == null) {
            timerTaskDefaultSearch = getSearchTask();
        }
        blocking(() -> {
            getSearchTask().run();
        });
    }

    @Override
    public abstract int getScanIntervalSeconds();

    @Override
    public void onConsumerJoined(SDCConsumer consumer) {
        SDCLib.getInstance().getLogger().log(Level.INFO, "SDC member found: {0}", consumer.getEndpointReference());
        if (getDiscoveredConsumers().containsKey(consumer.getEndpointReference())) {
            if (consumer != getDiscoveredConsumers().get(consumer.getEndpointReference())) {
                throw new IllegalStateException("New instances with the same EPR not allowed! Get available object and initialize again!");
            }
            // This will trigger the observable map's added event (recurrent update)
            sdcConsumers.put(consumer.getEndpointReference(), consumer);
            return;
        }
        // Initial observable map insert
        sdcConsumers.put(consumer.getEndpointReference(), consumer);
        // Note: joined handler is called only once
        joinedHandlers.forEach((handler) -> {
            handler.onConsumerJoined(consumer);
        });
        var lifecycleHandler = new SDCLifecycleHandler() {

                private Timer timer;

                @Override
                public void onConnectionLost(SDCConsumer consumer) {
                    SDCLib.getInstance().getLogger().log(Level.INFO, "SDC member connection lost: {0}", consumer.getEndpointReference());
                    timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            if (!consumer.isConnected()) {
                                onConsumerLeft(consumer.getEndpointReference());
                            }
                        }

                    }, TIMEOUT_REMOVE_SECONDS * 1000);
                    // This will trigger the observable map's deleted event (see also onConsumerLeft)
                    sdcConsumers.remove(consumer.getEndpointReference());
                }

                @Override
                public void onConnectionReestablished(SDCConsumer consumer) {
                    SDCLib.getInstance().getLogger().log(Level.INFO, "SDC member connection reestablished: {0}", consumer.getEndpointReference());
                    if (timer != null) {
                        timer.cancel();
                    }
                }

                @Override
                public void onClosed(SDCConsumer consumer) {
                    SDCLib.getInstance().getLogger().log(Level.INFO, "SDC member connection closed: {0}", consumer.getEndpointReference());
                }

                @Override
                public void onOpened(SDCConsumer consumer) {
                    SDCLib.getInstance().getLogger().log(Level.INFO, "SDC member connection opened: {0}", consumer.getEndpointReference());
                }

            };
        consumer.addLifecycleHandler(lifecycleHandler);
    }

    @Override
    public void onConsumerLeft(String epr) {
        final SDCConsumer consumer = sdcConsumers.get(epr);
        if (consumer != null) {
            SDCLib.getInstance().getLogger().log(Level.INFO, "SDC member left: {0}", consumer.getEndpointReference());
            closeConsumer(consumer);
            // This will trigger the observable map's deleted event
            sdcConsumers.remove(consumer.getEndpointReference());
        }
    }

    protected void openConsumer(SDCConsumer consumer) {
        invokeMethod(consumer, "open");
    }

    protected void closeConsumer(SDCConsumer consumer) {
        invokeMethod(consumer, "close");
    }

    private void invokeMethod(SDCConsumer consumer, String methodName) {
        Method method;
        try {
            method = consumer.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(consumer);
        } catch (Exception ex) {
            SDCLib.getInstance().getLogger().log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public SDCConsumer getConsumerByEPR(String epr) {
        return sdcConsumers.get(epr);
    }

    @Override
    public Collection<SDCConsumer> getSDCConsumers() {
        return sdcConsumers.values();
    }

    @Override
    public abstract TimerTask getSearchTask();

    @Override
    public String getNamespace() {
        return WSConstants.BINDING_NAMESPACE;
    }

    @Override
    public void addConsumerJoinedFirstHandler(IConsumerJoinedHandler handler) {
        joinedHandlers.add(handler);
    }

    protected abstract void init();

    protected abstract void close();

    @Override
    public ObservableMap<String, SDCConsumer> getObservableMap() {
        return sdcConsumers;
    }

    /**
     * Gets a map of discovered consumers. Consumers which have been once discovered are stored
     * in this map even if currently no connection is available.
     * @return The map
     */
    protected ConcurrentHashMap<String, SDCConsumer> getDiscoveredConsumers() {
        return discoveredConsumers;
    }

}
