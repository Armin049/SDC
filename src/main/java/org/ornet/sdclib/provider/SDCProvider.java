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
package org.ornet.sdclib.provider;

import com.bestingit.async.AsyncMethodInvocationException;
import com.bestingit.async.Completer;
import static com.bestingit.async.Task.*;

import org.ornet.cdm.*;
import org.ornet.sdclib.SDCToolbox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ornet.sdclib.ISDCTransportLayerConfiguration;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.BICEPSTypeConverter;
import org.ornet.sdclib.binding.device.IDeviceBinding;

public final class SDCProvider implements SDCEndpoint, AutoCloseable {

    private final AtomicLong mdibVersion = new AtomicLong();

    private MdDescription mddescription = new MdDescription();

    private final List<AbstractState> mdibStates = Collections.synchronizedList(new ArrayList<>());
    private final List<AbstractOperationState> operationStates = Collections.synchronizedList(new ArrayList<>());

    private final Map<String, SDCProviderHandler> providerHandlers = Collections.synchronizedMap(new HashMap<>());

    private IDeviceBinding transportBinding;
    private ISDCTransportLayerConfiguration transportLayerConfiguration;
    private long periodicTaskId = -1;
    private boolean isTcpStreaming;

    public SDCProvider() {
        // The transport binding is automatically set by using the default transport configuration, but can be customized
        setTransportLayerConfiguration(SDCLib.getInstance().getDefaultTransportLayerConfig(ISDCTransportLayerConfiguration.class));
    }

    public final void setTransportLayerConfiguration(ISDCTransportLayerConfiguration transportLayerConfiguration) {
        this.transportLayerConfiguration = transportLayerConfiguration;
        transportBinding = transportLayerConfiguration.getDeviceBinding(this);
    }

    public final ISDCTransportLayerConfiguration getTransportLayerConfiguration() {
        return transportLayerConfiguration;
    }

    /**
     * Get deep clone of MD description.
     *
     * @return The description.
     */
    @Override
    public MdDescription getMDDescription() {
        return BICEPSTypeConverter.cloneType(mddescription, MdDescription.class);
    }

    public Long getMdibVersion() {
    	return Long.valueOf(mdibVersion.get()); 
    }

    public void setMDDescription(MdDescription mddescription) {
        this.mddescription = mddescription;
    }

    public void addMDS(MdsDescriptor mds) {
        boolean duplicate = mdsContainsHandle(mds.getHandle());

        if (duplicate == false) {
            this.mddescription.getMds().add(mds);
            mdibVersion.incrementAndGet();
        }
    }
    
    public void removeMDS(String handle) {
        if (handle != null) {
            boolean removed = mddescription.getMds().removeIf(x -> x.getHandle().equals(handle));

            if (removed == true) {                
                mdibVersion.incrementAndGet();
            }
        }
    }
    
    private boolean mdsContainsHandle(String handle) {
        for (MdsDescriptor descriptor : mddescription.getMds()) {
            if (descriptor.getHandle().equals(handle)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Get deep clone of all MD states.
     *
     * @return The states.
     */
    @Override
    public MdState getMDState() {
        MdState states = new MdState();
        synchronized (mdibStates) {
            mdibStates.forEach((state -> {
                var cloned = BICEPSTypeConverter.cloneType(state, state.getClass());
                states.getState().add(cloned);
            }));
        }
        synchronized (operationStates) {
            operationStates.forEach((state -> {
                var cloned = BICEPSTypeConverter.cloneType(state, state.getClass());
                states.getState().add(cloned);
            }));
        }
        return states;
    }

    public List<AbstractContextState> getContextStates() {
        return getContextStates(null);
    }

    public List<AbstractContextState> getContextStates(List<String> handles) {
        return getTypedStates(handles, AbstractContextState.class);
    }

    public List<AbstractAlertState> getAlertStates() {
        return getTypedStates(null, AbstractAlertState.class);
    }

    public List<AbstractAlertState> getAlertStates(List<String> handles) {
        return getTypedStates(handles, AbstractAlertState.class);
    }

    private <T> List<T> getTypedStates(List<String> handles, Class<T> type) {
        MdState states = handles == null || handles.isEmpty() ? getMDState() : getMDState(handles);
        List<T> targetStates = new LinkedList<>();
        Iterator<AbstractState> it = states.getState().iterator();
        while (it.hasNext()) {
            AbstractState next = it.next();
            if (type.isAssignableFrom(next.getClass())) {
                targetStates.add(type.cast(next));
            }
        }
        return targetStates;
    }

    /**
     * Get deep clone of MD states.
     *
     * @param handles List of handles to match.
     * @return The states.
     */
    @Override
    public MdState getMDState(List<String> handles) {
        MdState states = new MdState();
        Set<String> handleSet = new HashSet<>(handles);
        synchronized (mdibStates) {
            addToStateContainer(handleSet, states, mdibStates.iterator());
        }
        synchronized (operationStates) {
            addToStateContainer(handleSet, states, operationStates.iterator());
        }
        return states;
    }

    private <T extends AbstractState> void addToStateContainer(Set<String> handleSet, MdState states, Iterator<T> it) {
        while (it.hasNext()) {
            T next = it.next();
            if (handleSet.contains(next.getDescriptorHandle())) {
                var cloned = BICEPSTypeConverter.cloneType(next, next.getClass());
                states.getState().add(cloned);
            }
        }
    }

    public List<AbstractOperationDescriptor> createSetOperationForDescriptor(AbstractDescriptor descr, MdsDescriptor mds) {
        return createSetOperationForDescriptor(descr, mds, null);
    }

    public List<AbstractOperationDescriptor> createSetOperationForDescriptor(AbstractDescriptor descr, MdsDescriptor mds, SafetyReqType safetyReq) {
        return SDCToolbox.createOperationDescriptor(operationStates, descr, mds, safetyReq);
    }

    /**
     * Create an MDIB container object. Modifications of this structure will not
     * be reflected into the internal MDIB representation.
     *
     * @return The MDIB.
     */
    @Override
    public final Mdib getMDIB() {
        Mdib mdib = new Mdib();
        mdib.setSequenceId("0");
        mdib.setMdibVersion(getMdibVersion());
        mdib.setMdState(getMDState());
        mdib.setMdDescription(getMDDescription());
        return mdib;
    }
    
    public void startup() throws AsyncMethodInvocationException {
        complete(startupAsync());
    }

    public Completer startupAsync() throws AsyncMethodInvocationException {
        SDCLib.getInstance().getLogger().log(Level.INFO, "Provider startup...");
        return async(() -> {
                if (!transportBinding.init()) {
                    Logger.getLogger(SDCProvider.class.getName()).log(Level.SEVERE, "Fatal error - provider init failed!");
                    return;
                }
                if (mdibStates.isEmpty()) {
                    synchronized (providerHandlers) {
                        for (SDCProviderHandler next : providerHandlers.values()) {
                            if (next instanceof SDCProviderMDStateHandler) {
                                final AbstractState newState = setDefaultStateValues(((SDCProviderMDStateHandler) next).getInitialClonedState());
                                mdibStates.add(newState);
                                transportBinding.initState(newState);
                            }
                        }
                    }
                }
                // Check for valid provider MDIB
                if (SDCLib.getInstance().isSchemaValidationEnabled()) {
                    GetMdibResponse response = new GetMdibResponse();
                    response.setSequenceId("0");
                    final Mdib mdib = getMDIB();
                    mdib.setSequenceId("0");
                    response.setMdib(mdib);
                    response.setSequenceId(mdib.getSequenceId());
                    BICEPSTypeConverter.toString(response);
                }
                transportBinding.start();          
        });
    }

    public boolean isRunning() {
        return transportBinding.isRunning();
    }

    @Override
    public void close() throws Exception {
        SDCLib.getInstance().getLogger().log(Level.INFO, "Provider shutdown...");
        try {
            transportBinding.stop();
            mdibStates.clear();
        } catch (Exception ex) {
            Logger.getLogger(SDCProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private <T extends AbstractState> boolean updateInternalMatchingState(AbstractState newState, List<T> list) {
        AbstractState temp = null;
        synchronized (list) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                AbstractState next = it.next();
                if (next.getDescriptorHandle().equals(newState.getDescriptorHandle())) {
                    temp = next;
                    break;
                }
            }
        }
        if (temp == null) {
            return false;
        }
        list.remove((T) temp);
        newState.setStateVersion(temp.getStateVersion()+1); // Increment state version
        var values = setDefaultStateValues(newState);
        list.add((T) BICEPSTypeConverter.cloneType(values, values.getClass()));
        mdibVersion.incrementAndGet();
        return true;
    }

    private boolean updateInternalMatchingState(AbstractState newState) {
        if (!updateInternalMatchingState(newState, mdibStates)) {
            return updateInternalMatchingState(newState, operationStates);
        }
        return true;
    }

    public void updateState(AbstractState state) {
        updateState(state, true);
    }

    public void updateState(AbstractState state, boolean notifyEvents) {
        if (!updateInternalMatchingState(state)) {
            throw new IllegalStateException("No state to update with given descriptor handle (state handle): " + state.getDescriptorHandle());
        }
        // Call transport state changed
        transportBinding.onStateChanged(state);
        if (!notifyEvents) {
            return;
        }
        // Eventing
        if (state instanceof RealTimeSampleArrayMetricState) {
            transportBinding.getEventSourceBinding().handleStream((RealTimeSampleArrayMetricState) state, isTcpStreaming);
        } else if (state instanceof AbstractMetricState) {
            transportBinding.getEventSourceBinding().handleEpisodicMetricEvent((AbstractMetricState) state, getMdibVersion());
            evaluateAlertConditions(((AbstractMetricState) state));
        } else if (state instanceof AbstractContextState) {
            transportBinding.getEventSourceBinding().handleEpisodicContextChangedEvent((AbstractContextState) state, getMdibVersion());
        } else if (state instanceof AbstractAlertState) {
            transportBinding.getEventSourceBinding().handleEpisodicAlertEvent((AbstractAlertState) state, getMdibVersion());
        } else if (state instanceof AbstractOperationState) {
            throw new IllegalStateException("Eventing not yet supported for operation states, handle: " + state.getDescriptorHandle());
        }
    }
    
    /**
     * Enables sending of periodic events
     * 
     * @param recurringTimeInMillis Time in milliseconds for sending out events
     */
    public void enablePeriodicEvents(int recurringTimeInMillis) {
        if (periodicTaskId != -1)
            return;
        periodicTaskId = scheduleAsync(() -> {
            sendPeriodicEvents();
        }, recurringTimeInMillis, true);
    }
    
    private void sendPeriodicEvents() {
        SDCLib.getInstance().getLogger().log(Level.FINER, "Sending periodic events...");
        
        for(Iterator<AbstractState> it=mdibStates.iterator(); it.hasNext();) {        
            AbstractState state = it.next();

            if (state instanceof AbstractMetricState) {
                transportBinding.getEventSourceBinding().handlePeriodicMetricEvent((AbstractMetricState) state, getMdibVersion());
            } else if (state instanceof AbstractContextState) {
                transportBinding.getEventSourceBinding().handlePeriodicContextChangedEvent((AbstractContextState) state, getMdibVersion());
            } else if (state instanceof AbstractAlertState) {
                transportBinding.getEventSourceBinding().handlePeriodicAlertEvent((AbstractAlertState) state, getMdibVersion());
            }                    
        };
    }    
    
    /**
     * Disables sending of periodic events
     */
    public void disablePeriodicEvents() {
        if (periodicTaskId != -1)
            cancel(periodicTaskId);
        periodicTaskId = -1;
    }

    public synchronized void notifyOperationInvoked(OperationInvocationContext oic, InvocationState is, String operationErrorMsg) {
        transportBinding.getEventSourceBinding().handleOperationInvokedEvent(oic, is, getMdibVersion(), operationErrorMsg);
    }

    public void setEndpointReference(String epr) {
        transportBinding.setEndpointReference(epr);
    }

    public void addHandler(SDCProviderHandler handler) {
        if (transportBinding.isRunning()) {
            throw new IllegalStateException("Provider is running!");
        }
        handler.setProvider(this);
        providerHandlers.put(handler.getDescriptorHandle(), handler);
    }

    public void removeHandler(SDCProviderHandler handler) {
        if (transportBinding.isRunning()) {
            throw new IllegalStateException("Provider is running!");
        }
        providerHandlers.remove(handler.getDescriptorHandle());
    }

    public SDCProviderHandler getHandler(String descriptorHandle) {
        return providerHandlers.get(descriptorHandle);
    }

    public Map<String, SDCProviderHandler> getStateHandlers() {
        return providerHandlers;
    }

    private AbstractState setDefaultStateValues(AbstractState state) {
        if (state.getStateVersion() == null) {
            state.setStateVersion(Long.valueOf(0));
        }
        if (state instanceof AbstractContextState) {
            AbstractContextState acs = (AbstractContextState) state;
            if (acs.getBindingMdibVersion() == null) {
                acs.setBindingMdibVersion(Long.valueOf(0));
            }
        }
        return state;
    }

    public IDeviceBinding getTransportBinding() {
        return transportBinding;
    }

    private void evaluateAlertConditions(AbstractMetricState state) {
        async(() -> {
            Set<AlertConditionDescriptor> acds = SDCToolbox.collectAllAlertConditionDescriptors(this);
            for (AlertConditionDescriptor next : acds) {
                if (next.getSource().contains(state.getDescriptorHandle())) {
                    SDCProviderHandler handler = getHandler(next.getHandle());
                    if (handler == null) {
                        Logger.getLogger(SDCProvider.class.getName()).log(Level.SEVERE, "Error in evaluating alert conditions. Handler missing for: {0}", state.getDescriptorHandle());
                        return;
                    }
                    if (handler instanceof SDCProviderAlertConditionStateHandler) {
                        List<AbstractAlertState> handlerStates = getAlertStates(new ArrayList<>(Arrays.asList(new String[]{next.getHandle()})));
                        if (handlerStates.size() > 0) {
                            ((SDCProviderAlertConditionStateHandler) handler).sourceHasChanged(state, (AlertConditionState) handlerStates.get(0));
                        }
                    }
                }
            }
        });
    }

    @Override
    public String getEndpointReference() {
        return transportBinding.getEnpointReference();
    }

    public void createFluentAutoSettableHandlers(Collection<AbstractState> states, BlockingDeque<SDCFluentStateChangeRequestContext> deque) {
        // Metric states
        for (MdsDescriptor nextMDS : mddescription.getMds()) {
            for (VmdDescriptor nextVmd : nextMDS.getVmd()) {
                for (ChannelDescriptor nextChn : nextVmd.getChannel()) {
                    for (AbstractMetricDescriptor nextMet : nextChn.getMetric()) {
                        final AbstractState state = getStateByHandle(states, nextMet.getHandle());
                        if (state == null) {
                            SDCLib.getInstance().getLogger().log(Level.WARNING, "State missing for metric handle: {0}. Skipping state (initial value & SCO operation).", nextMet.getHandle());
                            continue;
                        }
                        if (SDCToolbox.isMetricChangeAllowed(this, (AbstractMetricState) state)) {
                            createSetOperationForDescriptor(nextMet, nextMDS);
                        }
                        addHandler(new SDCFluentAutoSettableProviderHandler(nextMet.getHandle(), state, deque));
                    }
                }
            }
        }
        // Alert states
        for (MdsDescriptor mds : mddescription.getMds()) {
            handleAlertOperations(mds.getAlertSystem(), mds, states, deque);
            for (VmdDescriptor vmd : mds.getVmd()) {
                handleAlertOperations(vmd.getAlertSystem(), mds, states, deque);
            }
        }
        // Context states
        for (MdsDescriptor mds : mddescription.getMds()) {
            final SystemContextDescriptor systemContext = mds.getSystemContext();
            if (systemContext != null) {
                handleContextOperations(systemContext.getLocationContext(), states, mds, deque);
            }
        }
    }

    private AbstractState getStateByHandle(Collection<AbstractState> states, String handle) {
        Iterator<AbstractState> it = states.iterator();
        while (it.hasNext()) {
            final AbstractState next = it.next();
            if (next.getDescriptorHandle().equals(handle)) {
                return next;
            }
        }
        return null;
    }

    private void handleAlertOperations(AlertSystemDescriptor alertSystem, MdsDescriptor mds, Collection<AbstractState> states, BlockingDeque<SDCFluentStateChangeRequestContext> deque) {
        if (alertSystem == null) {
            return;
        }
        handleAlertOperationDescriptors(new ArrayList<>(alertSystem.getAlertCondition()), mds, states, deque);
        handleAlertOperationDescriptors(new ArrayList<>(alertSystem.getAlertSignal()), mds, states, deque);
    }

    private void handleAlertOperationDescriptors(List<AbstractAlertDescriptor> descriptors, MdsDescriptor mds, Collection<AbstractState> states, BlockingDeque<SDCFluentStateChangeRequestContext> deque) {
        for (AbstractAlertDescriptor next : descriptors) {
            final AbstractState state = getStateByHandle(states, next.getHandle());
            if (state == null) {
                SDCLib.getInstance().getLogger().log(Level.WARNING, "State missing for alert handle: {0}. Skipping SCO operation creation.", next.getHandle());
                continue;
            }
            createSetOperationForDescriptor(next, mds);
            addHandler(new SDCFluentAutoSettableProviderHandler(next.getHandle(), state, deque));
        }
    }

    private void handleContextOperations(AbstractContextDescriptor cd, Collection<AbstractState> states, MdsDescriptor mds, BlockingDeque<SDCFluentStateChangeRequestContext> deque) {
        final AbstractState state = getStateByHandle(states, cd.getHandle());
        if (state == null) {
            SDCLib.getInstance().getLogger().log(Level.WARNING, "State missing for context handle: {0}. Skipping SCO operation creation.", cd.getHandle());
            return;
        }
        createSetOperationForDescriptor(cd, mds);
        addHandler(new SDCFluentAutoSettableProviderHandler(cd.getHandle(), state, deque));
    }

    public boolean isTcpStreaming() {
        return isTcpStreaming;
    }

    public void setTcpStreaming(boolean tcpStreaming) {
        isTcpStreaming = tcpStreaming;
    }
}
