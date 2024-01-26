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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ornet.cdm.*;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerDetail;
import org.ornet.sdclib.consumer.FutureInvocationState;
import org.ornet.sdclib.consumer.SDCConsumer;
import org.ornet.sdclib.consumer.SDCLifecycleHandler;
import org.ornet.sdclib.provider.SDCProvider;
import org.ornet.sdclib.provider.SDCFluentStateChangeRequestContext;
import static com.bestingit.async.Task.*;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import org.ornet.sdclib.common.SyncEvent;
import org.ornet.sdclib.restapi.RestApiRoute;
import org.ornet.sdclib.restapi.RestApiVerticle;

/**
 * Fluent API, a flat and easy-to-use SDC interface
 */
public class SDCFluent {

    private static final ConcurrentMap<String, SDCConsumer> sdcConsumers = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, SDCProvider> sdcProviders = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, BlockingDeque<AbstractState>> events = new ConcurrentHashMap<>();
    private static final int DEFAULT_PORT_START = 10000;
    private static RestApiVerticle restVerticle;
    private static Vertx vertx;

    private static boolean joined = false;
    private static SDCLib instance;
    private static long asyncHandlerId = -1;
    private static String restDeploymentId;

    /**
     * Optionally configure fluent API (schema validation enabled).
     *
     * @param transportConfig The transport configuration
     */
    public static void Configure(ISDCTransportLayerConfiguration transportConfig) {
        Configure(transportConfig, true);
    }

    /**
     * Optionally configure fluent API.
     *
     * @param transportConfig The transport configuration
     * @param schemaValidation Whether to use schema validation
     */
    public static void Configure(ISDCTransportLayerConfiguration transportConfig, boolean schemaValidation) {
        if (instance == null) {
            instance = SDCLib.getInstance();
        }
        instance.addTransportLayerConfig(instance.getDefaultTransportLayerKey(), transportConfig);
        instance.setSchemaValidationEnabled(schemaValidation);
    }

    /**
     * Join SDC network.
     *
     */
    public static void JoinSDCNetwork() {
        JoinSDCNetwork(true);
    }
    
    /**
     * Start SDC REST API.
     * 
     * @param port The port
     */    
    public static void StartSDCRestApi(int port) {
        StartSDCRestApi("0.0.0.0", port, new HttpServerOptions());
    }    
    
    /**
     * Start SDC REST API.
     * 
     * @param host The host IP
     * @param port The port
     */    
    public static void StartSDCRestApi(String host, int port) {
        StartSDCRestApi(host, port, new HttpServerOptions());
    }
    
    /**
     * Stop SDC REST API.
     * 
     */    
    public static void StopSDCRestApi() {
        if (vertx != null && restDeploymentId != null) {
            vertx.undeploy(restDeploymentId);
        }            
    }

    /**
     * Start SDC REST API.
     *
     * @param host The host IP
     * @param port The port
     * @param options The HTTP options
     */
    public static void StartSDCRestApi(String host, int port, HttpServerOptions options) {
        if (instance == null)
            throw new IllegalStateException("SDCLib instance not available, call JoinSDCNetwork() first.");
        if (!instance.getDefaultTransportLayerKey().equals(SDCLib.MDPWS_TRANSPORT_KEY))
            throw new IllegalStateException("REST API can only be used in conjunction with MDPWS.");        
        final JsonObject config = new JsonObject();
        config.put("http.host", host);
        config.put("http.port", port);
        SyncEvent deployEvent = new SyncEvent();
        if (vertx == null) {
            vertx = instance.getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class).getVertx();
        }
        restVerticle = new RestApiVerticle(options);
        vertx.deployVerticle(restVerticle, new DeploymentOptions().setWorker(true).setConfig(config),
                res -> {
                    restDeploymentId = res.result();
                    SDCLib.getInstance().getLogger().log(Level.INFO, "REST API verticle deployed: {0}", res.succeeded());
                    SDCLib.getInstance().getLogger().log(Level.INFO, "REST API uses current HTTP path: {0}", RestApiRoute.REALM);
                    if (!res.succeeded()) {
                        SDCLib.getInstance().getLogger().log(Level.SEVERE, "REST API verticle error: {0}", res.cause().toString());
                    } else {
                        deployEvent.set();
                    }
                });
        if (!deployEvent.wait(10000)) {
            throw new IllegalStateException("REST API verticle not deployed in time!");
        }        
    }    

    /**
     * Join SDC network.
     *
     * @param enableSearch Whether to search for other devices
     */
    public static void JoinSDCNetwork(boolean enableSearch) {
        if (joined) {
            return;
        }
        if (instance == null) {
            instance = SDCLib.getInstance();
            // Default MDPWS config
            ISDCTransportLayerConfiguration tlc = instance.getDefaultTransportLayerConfig(ISDCTransportLayerConfiguration.class);
            if (tlc instanceof MDPWSTransportLayerConfiguration) {
                MDPWSTransportLayerConfiguration mtlc = (MDPWSTransportLayerConfiguration) tlc;
                final MDPWSTransportLayerDetail configurationDetail = mtlc.getConfigurationDetail();
                configurationDetail.setPortStart(DEFAULT_PORT_START);
                mtlc.getConfigurationDetail().setBindInterface("0.0.0.0");
            }
        }
        if (enableSearch) {
            instance.getDefaultServiceManager().getBinding().addConsumerJoinedFirstHandler((consumer) -> {
                sdcConsumers.put(consumer.getEndpointReference(), consumer);
                BlockingDeque<AbstractState> eventDeque = events.get(consumer.getEndpointReference());
                consumer.internalSetEventDeque(eventDeque);
                consumer.addLifecycleHandler(new SDCLifecycleHandler() {

                    private void initEventDeque(SDCConsumer consumer) {
                        BlockingDeque<AbstractState> eventDeque = events.get(consumer.getEndpointReference());
                        if (eventDeque != null) {
                            consumer.internalSetEventDeque(eventDeque);
                        }
                    }

                    @Override
                    public void onConnectionLost(SDCConsumer consumer) {
                    }

                    @Override
                    public void onConnectionReestablished(SDCConsumer consumer) {
                        initEventDeque(consumer);
                    }

                    @Override
                    public void onClosed(SDCConsumer consumer) {
                        sdcConsumers.remove(consumer.getEndpointReference());
                    }

                    @Override
                    public void onOpened(SDCConsumer consumer) {
                        sdcConsumers.put(consumer.getEndpointReference(), consumer);
                        initEventDeque(consumer);
                    }
                });
            });
        }
        instance.startup(enableSearch);
        SDCLib.getInstance().getLogger().log(Level.INFO, "Fluent API active.");
        joined = true;
    }

    /**
     * Leave SDC network.
     *
     */
    public static void LeaveSDCNetwork() {
        sdcProviders.values().stream().forEach((provider) -> {
            try {
                provider.close();
            } catch (Exception ex) {
                SDCLib.getInstance().getLogger().log(Level.SEVERE, null, ex);
            }
        });
        sdcConsumers.clear();
        events.clear();
        if (asyncHandlerId != -1) {
            cancel(asyncHandlerId);
        }   
        joined = false;
        if (instance == null) {
            instance = SDCLib.getInstance();
        }
        instance.shutdown();
    }

    /**
     * Get a snapshot list of currently available devices (endpoint references).
     *
     * @return EPR list
     */
    public static Set<String> GetMembers() {
        return new HashSet<>(sdcConsumers.keySet());
    }

    /**
     * Create a new member (provider / device)
     *
     * @param member EPR
     * @param mds MDS descriptor
     * @param states Initial states
     * @return Deque for state change request events
     */
    public static BlockingDeque<SDCFluentStateChangeRequestContext> CreateLocalMember(String member, MdsDescriptor mds, Collection<AbstractState> states) {
        MdDescription description = new MdDescription();
        description.getMds().add(mds);
        return CreateLocalMember(member, description, states, null);
    }

    /**
     * Create a new member (provider / device)
     *
     * @param member EPR
     * @param description MDS description
     * @param states Initial states
     * @param outProvider Reference of created provider
     * @return Deque for state change request events
     */
    public static BlockingDeque<SDCFluentStateChangeRequestContext> CreateLocalMember(String member, MdDescription description, Collection<AbstractState> states, SDCProvider[] outProvider) {
        if (sdcProviders.containsKey(member)) {
            return null;
        }
        SDCProvider p = new SDCProvider();
        p.setEndpointReference(member);
        p.setMDDescription(description);
        BlockingDeque<SDCFluentStateChangeRequestContext> deque = new LinkedBlockingDeque<>();
        p.createFluentAutoSettableHandlers(states, deque);
        p.startup();
        sdcProviders.put(member, p);
        if (outProvider != null && outProvider.length == 1) {
            outProvider[0] = p;
        }
        return deque;
    }

    /**
     * Delete a new member (provider / device)
     *
     * @param member EPR
     */
    public static void DeleteLocalMember(String member) {
        SDCProvider p = sdcProviders.get(member);
        if (p == null) {
            return;
        }
        try {
            p.close();
        } catch (Exception ex) {
            SDCLib.getInstance().getLogger().log(Level.SEVERE, null, ex);
        }
        sdcProviders.remove(member);
    }

    /**
     * Set state value on local member (provider / device).
     *
     * @param member The EPR
     * @param state The state to update
     * 
     * @throws IllegalStateException in case of missing member with provided EPR
     */
    public static void UpdateLocalState(String member, AbstractState state) {
        SDCProvider p = sdcProviders.get(member);
        if (p == null) {
            throw new IllegalStateException("Not a local member with EPR: " + member);
        }
        p.updateState(state);
    }

    /**
     * Get the description of a member.
     *
     * @param member The member
     * @throws IllegalStateException In case member not found
     * @return The description or null
     */
    public static MdDescription GetDescription(String member) {
        if (!sdcConsumers.containsKey(member)) {
            throw new IllegalStateException("Member not present with EPR: " + member);
        }
        return sdcConsumers.get(member).getMDDescription();
    }

    /**
     * Get all states of a member.
     *
     * @param member The member
     * @throws IllegalStateException In case member not found
     * @return The states or null
     */
    public static MdState GetStates(String member) {
        return GetStates(member, null);
    }

    /**
     * Get states of a member.
     *
     * @param member The member
     * @param handles The list of handles (or null, for all)
     * @throws IllegalStateException In case member not found
     * @return The states or null
     */
    public static MdState GetStates(String member, List<String> handles) {
        if (!sdcConsumers.containsKey(member)) {
            throw new IllegalStateException("Member not present with EPR: " + member);
        }
        return sdcConsumers.get(member).getMDState(handles);
    }

    private static <T extends AbstractState> T GetState(String member, String handle, Class<T> stateType) {
        if (!sdcConsumers.containsKey(member)) {
            throw new IllegalStateException("Member not present with EPR: " + member);
        }
        return sdcConsumers.get(member).requestState(handle, stateType);
    }

    /**
     * Get a specific state.
     *
     * @param member The member.
     * @param handle The state or descriptor handle.
     * @throws IllegalStateException In case member not found
     * @return The state (or null)
     */
    public static NumericMetricState GetNumericState(String member, String handle) {
        return GetState(member, handle, NumericMetricState.class);
    }

    /**
     * Get a specific state.
     *
     * @param member The member.
     * @param handle The state or descriptor handle.
     * @throws IllegalStateException In case member not found
     * @return The state (or null)
     */
    public static StringMetricState GetStringState(String member, String handle) {
        return GetState(member, handle, StringMetricState.class);
    }

    /**
     * Get a specific state.
     *
     * @param member The member.
     * @param handle The state or descriptor handle.
     * @return The state (or null)
     */
    public static AbstractContextState GetContextState(String member, String handle) {
        return GetState(member, handle, AbstractContextState.class);
    }

    /**
     * Get a specific state.
     *
     * @param member The member.
     * @param handle The state or descriptor handle.
     * @return The state (or null)
     */
    public static AbstractAlertState GetAlertState(String member, String handle) {
        return GetState(member, handle, AbstractAlertState.class);
    }

    /**
     * Get a specific numeric value.
     *
     * @param member The member.
     * @param handle The state or descriptor handle.
     * @throws IllegalStateException In case member not found
     * @return The value (or Double.NaN)
     */
    public static double GetSimpleNumberValue(String member, String handle) {
        NumericMetricState state = GetState(member, handle, NumericMetricState.class);
        if (state == null) {
            return Double.NaN;
        }
        if (state.getMetricValue() == null) {
            return Double.NaN;
        }
        NumericMetricValue nv = state.getMetricValue();
        if (nv.getValue() == null) {
            return Double.NaN;
        }
        return nv.getValue().doubleValue();
    }

    /**
     * Get a specific string value.
     *
     * @param member The member.
     * @param handle The state or descriptor handle.
     * @throws IllegalStateException In case member not found
     * @return The string (or null)
     */
    public static String GetSimpleStringValue(String member, String handle) {
        StringMetricState state = GetState(member, handle, StringMetricState.class);
        if (state == null) {
            return null;
        }
        if (state.getMetricValue() == null) {
            return null;
        }
        return state.getMetricValue().getValue();
    }

    /**
     * Get a specific string or number value.
     *
     * @param member The member.
     * @param handle The state or descriptor handle.
     * @throws IllegalStateException In case member not found
     * @return The string (or null)
     */
    public static String GetSimpleValue(String member, String handle) {
        var state = GetState(member, handle, AbstractMetricState.class);
        if (state == null) {
            return null;
        }
        if (state instanceof NumericMetricState) {
            var nv = ((NumericMetricState)state).getMetricValue();
            if (nv.getValue() == null) {
                return null;
            }
            return nv.getValue().toString();
        }
        else if (state instanceof StringMetricState)
        {
            var nv = ((StringMetricState)state).getMetricValue();
            return nv.getValue();
        }
        return null;
    }

    /**
     * Set a state number value on a member.
     *
     * @param member The member
     * @param descriptorHandle The descriptor handle
     * @param value The value
     * @param timeout The timeout
     * @throws IllegalStateException In case member not found
     * @return True, in case of success
     */
    public static boolean SetSimpleNumberValue(String member, String descriptorHandle, double value, int timeout) {
        SDCConsumer consumer = sdcConsumers.get(member);
        if (consumer == null) {
            throw new IllegalStateException("Member not present with EPR: " + member);
        }
        FutureInvocationState fis = new FutureInvocationState();
        NumericMetricValue nv = new NumericMetricValue();
        nv.setValue(BigDecimal.valueOf(value));
        consumer.commitValue(descriptorHandle, nv, fis);
        return fis.waitReceived(new InvocationState[] { InvocationState.FIN, InvocationState.FIN_MOD }, timeout);
    }

    /**
     * Set a state string value on a member.
     *
     * @param member The member
     * @param descriptorHandle The descriptor handle
     * @param value The value
     * @param timeout The timeout
     * @throws IllegalStateException In case member not found
     * @return True, in case of success
     */
    public static boolean SetSimpleStringValue(String member, String descriptorHandle, String value, int timeout) {
        SDCConsumer consumer = sdcConsumers.get(member);
        if (consumer == null) {
            throw new IllegalStateException("Member not present with EPR: " + member);
        }
        FutureInvocationState fis = new FutureInvocationState();
        StringMetricValue sv = new StringMetricValue();
        sv.setValue(value);
        consumer.commitString(descriptorHandle, sv, fis);
        return fis.waitReceived(new InvocationState[] { InvocationState.FIN, InvocationState.FIN_MOD }, timeout);
    }

    /**
     * Set a state value on a member.
     *
     * @param member The member
     * @param state The state
     * @param fis The future invocation state
     * 
     * @throws IllegalStateException in case of missing member with provided EPR
     * @return InvocationState, the invocation state
     */
    public static InvocationState SetState(String member, AbstractState state, FutureInvocationState fis) {
        SDCConsumer consumer = sdcConsumers.get(member);
        if (consumer == null) {
            throw new IllegalStateException("Member not present with EPR: " + member);
        }
        return consumer.commitState(state, fis);
    }

    /**
     * Enable catch-all events and alarms for a member. Can be called even if
     * the device hasn't been found yet.
     *
     * @param member The member
     * @return Blocking Deque containing current events and alarms.
     */
    public static BlockingDeque<AbstractState> EnableEventing(String member) {
        BlockingDeque<AbstractState> eventDeque = events.get(member);
        if (eventDeque == null) {
            eventDeque = new LinkedBlockingDeque<>();
            events.put(member, eventDeque);
            SDCConsumer consumer = sdcConsumers.get(member);
            if (consumer != null && consumer.isConnected()) {
                consumer.internalSetEventDeque(eventDeque);
            }
            return eventDeque;
        } else {
            return eventDeque;
        }
    }

    /**
     * Handle events (provider -> consumer>.
     *
     * @param deque The deque
     * @param handler The handler
     */
    public static void HandleAsyncEvents(BlockingDeque<AbstractState> deque, ISDCFluentAsyncEventHandler<AbstractState> handler) {
        handleAsync(deque, handler);
    }

    /**
     * Handle requests (consumer -> provider).
     *
     * @param deque The deque
     * @param handler The handler
     */
    public static void HandleAsyncRequests(BlockingDeque<SDCFluentStateChangeRequestContext> deque, ISDCFluentAsyncEventHandler<SDCFluentStateChangeRequestContext> handler) {
        handleAsync(deque, handler);
    }

    private static <T extends Object> void handleAsync(BlockingDeque<T> deque, ISDCFluentAsyncEventHandler<T> handler) {
        asyncHandlerId = scheduleAsync(() -> {
            while (deque.size() > 0) {
                try {
                    T next = deque.take();
                    handler.handle(next);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SDCFluent.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
        }, 100, true);
    }

    /**
     * Get SDC consumer for a member
     *
     * @param member The member
     * @return SDCConsumer The consumer
     */
    public static SDCConsumer GetSDCConsumer(String member) {
        return sdcConsumers.get(member);
    }

    /**
     * Exchange XML raw message with remote MDPWS service operation
     *
     * @param member The member
     * @param rawData The XML raw data (SOAP body)
     * @param serviceId The service ID (GetService, SetService, ContextService)
     * @throws IllegalStateException in case of missing member with provided EPR
     * @return String The XML raw response
     */
    public static String ExchangeRaw(String member, String rawData, String serviceId) {
        SDCConsumer consumer = sdcConsumers.get(member);
        if (consumer == null) {
            throw new IllegalStateException("Member not present with EPR: " + member);
        }
        return consumer.exchangeRaw(rawData, serviceId);
    }

}
