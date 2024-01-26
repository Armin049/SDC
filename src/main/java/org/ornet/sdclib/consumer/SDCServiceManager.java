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
package org.ornet.sdclib.consumer;

import com.bestingit.async.Completer;
import com.bestingit.async.EventCompleter;
import static com.bestingit.async.Task.event;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.client.IDiscoveryBinding;
import org.ornet.sdclib.binding.client.IDiscoveryBinding.IConsumerJoinedHandler;

public class SDCServiceManager {

    private final IDiscoveryBinding binding;
    private ObservableMap<String, SDCConsumer> observableMap;
    private final ConcurrentMap<String, Set<IConsumerJoinedHandler>> joinHandlers = new ConcurrentHashMap<>();
    private Timer activeTimer = new Timer();
    private TimerTask timerTaskActiveSearch;

    public SDCServiceManager(IDiscoveryBinding binding) {
        this.binding = binding;
    }

    public void init() {
        observableMap = this.binding.getObservableMap();
        observableMap.addListener((MapChangeListener.Change<? extends String, ? extends SDCConsumer> change) -> {
            synchronized (joinHandlers) {
                if (change.wasAdded()) {
                    Set<IConsumerJoinedHandler> handlers = joinHandlers.get(change.getKey());
                    if (handlers != null) {
                        handlers.forEach((handler) -> {
                            handler.onConsumerJoined(change.getValueAdded());
                        });
                        joinHandlers.remove(change.getKey());
                    }
                }
            }
        });
        this.binding.startContinuousDiscovery();
        activeTimer.cancel();
        activeTimer = new Timer();
        if (timerTaskActiveSearch == null) {
            timerTaskActiveSearch = getActiveSearchTask();
        }
        activeTimer.schedule(timerTaskActiveSearch, 1000, 2000);
    }

    public void close() {
        getAvailableSDCConsumers().forEach((consumer) -> {
            closeConsumer(consumer);
        });
        this.binding.stopContinuousDiscovery();
        joinHandlers.clear();
        observableMap = null;
    }

    private void closeConsumer(SDCConsumer consumer) {
        if (consumer.isClosed()) {
            return;
        }
        Method method;
        try {
            method = consumer.getClass().getDeclaredMethod("close");
            method.setAccessible(true);
            method.invoke(consumer);
        } catch (Exception ex) {
            SDCLib.getInstance().getLogger().log(Level.SEVERE, null, ex);
        }
    }

    public IDiscoveryBinding getBinding() {
        return binding;
    }

    /**
     * Get currently available consumers. The list will be permanently updated
     * by underlying discovery mechanisms.
     *
     * @return The consumers
     */
    public Collection<SDCConsumer> getAvailableSDCConsumers() {
        return binding.getSDCConsumers();
    }
    
    /**
     * Discover a consumer asynchronously. If the consumer is already known,
     * handler will be invoked immediately.
     *
     * @param epr The EPR
     * @return The completer
     */    
    public Completer<SDCConsumer> discoverEPRAsync(String epr) {
        EventCompleter<SDCConsumer> source = event(SDCConsumer.class);
        discoverEPRAsync(epr, (consumer) -> {
            source.setFinishedSuccess(consumer);
        });
        return source;
    }
        
    /**
     * Discover a consumer asynchronously. If the consumer is already known,
     * handler will be invoked immediately.
     *
     * @param epr The EPR
     * @param handler The handler to call on consumer join
     */
    public void discoverEPRAsync(String epr, IConsumerJoinedHandler handler) {
        SDCConsumer consumer = getAvailableSDCConsumerByEPR(epr);
        // Already discovered
        if (consumer != null && !consumer.isClosed()) {
            handler.onConsumerJoined(consumer);
            return;
        }
        // Active search
        synchronized (joinHandlers) {
            if (!joinHandlers.containsKey(epr)) {
                joinHandlers.put(epr, Collections.newSetFromMap(new ConcurrentHashMap<>()));
            }
            joinHandlers.get(epr).add(handler);
        }
    }

    /**
     * Get consumer by EPR from list of curerntly available consumers.
     *
     * @param epr the EPR
     *
     * @return The consumer, or null
     */
    public SDCConsumer getAvailableSDCConsumerByEPR(String epr) {
        return binding.getConsumerByEPR(epr);
    }

    /**
     * Get an observable map of EPR and consumers. Can be used for
     * effectively monitor joining and leaving consumers.
     *
     * @return The map
     */
    public ObservableMap<String, SDCConsumer> getObservableMap() {
        return binding.getObservableMap();
    }
    
    private TimerTask getActiveSearchTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (joinHandlers.size() > 0) {
                    SDCLib.getInstance().getLogger().log(Level.FINE, "Ongoing discovery, pending search list size: {0}", joinHandlers.size());
                    binding.forceDiscovery();
                }
            }
        };
    }

}
