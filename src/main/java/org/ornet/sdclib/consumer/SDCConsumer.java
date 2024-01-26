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

import com.bestingit.async.AsyncMethodInvocationException;
import com.bestingit.async.Completer;
import io.vertx.core.buffer.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ornet.cdm.AbstractAlertReport;
import org.ornet.cdm.AbstractAlertState;
import org.ornet.cdm.AbstractContextReport;
import org.ornet.cdm.AbstractContextState;
import org.ornet.cdm.AbstractMetricReport;
import org.ornet.cdm.AbstractMetricState;
import org.ornet.cdm.AbstractSet;
import org.ornet.cdm.AbstractSetResponse;
import org.ornet.cdm.Activate;
import org.ornet.cdm.ActivateResponse;
import org.ornet.cdm.GetContextStates;
import org.ornet.cdm.GetContextStatesResponse;
import org.ornet.cdm.GetMdDescription;
import org.ornet.cdm.GetMdDescriptionResponse;
import org.ornet.cdm.GetMdib;
import org.ornet.cdm.GetMdibResponse;
import org.ornet.cdm.GetMdState;
import org.ornet.cdm.GetMdStateResponse;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.MdDescription;
import org.ornet.cdm.Mdib;
import org.ornet.cdm.MdState;
import org.ornet.cdm.NumericMetricState;
import org.ornet.cdm.NumericMetricValue;
import org.ornet.cdm.OperationInvokedReport;
import org.ornet.cdm.RealTimeSampleArrayMetricState;
import org.ornet.cdm.SetAlertState;
import org.ornet.cdm.SetContextState;
import org.ornet.cdm.SetString;
import org.ornet.cdm.SetValue;
import org.ornet.cdm.AbstractState;
import org.ornet.cdm.StringMetricState;
import org.ornet.cdm.StringMetricValue;
import org.ornet.cdm.WaveformStream;
import org.ornet.cdm.SafetyInfoType;
import org.ornet.cdm.SetMetricState;
import org.ornet.sdclib.SDCToolbox;
import org.ornet.sdclib.binding.client.IClientBinding;
import org.ornet.sdclib.provider.OperationInvocationContext;
import org.ornet.sdclib.provider.SDCEndpoint;
import static com.bestingit.async.Task.*;
import java.util.Map;
import org.ornet.sdclib.binding.BICEPSTypeConverter;
import org.ornet.sdclib.common.MaxHashMap;

public class SDCConsumer implements SDCEndpoint {

    public enum SetRequestType {
        VALUE,
        STATE
    }

    protected static final int MAX_QUEUED_EVENTS = 256;

    private final Set<SDCLifecycleHandler> lifecycleHandlers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Set<ISDCConsumerGlobalReportRawHandler> rawReportHandlers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private BlockingDeque<AbstractState> eventDeque = null;
    private final AtomicBoolean eventingInitialized = new AtomicBoolean(false);
    private final AtomicBoolean closed = new AtomicBoolean(true);
    private long schedulerId = -1;

    private void enqueueEvent(AbstractState next) {
        if (eventDeque == null) {
            return;
        }
        eventDeque.add(next);
        if (eventDeque.size() > MAX_QUEUED_EVENTS) {
            eventDeque.removeFirst();
        }
    }

    protected BlockingDeque<AbstractState> internalGetEventDeque() {
        return eventDeque;
    }

    public void internalSetEventDeque(BlockingDeque<AbstractState> eventDeque) {
        this.eventDeque = eventDeque;
        internalCheckEventingInit();
    }

    public void internalCheckEventingInit() {
        if (!eventingInitialized.get()) {
            if (eventDeque != null || eventHandler.size() > 0) {
                transportBinding.initEventing();
                eventingInitialized.set(true);
            }
        }
    }

    private void internalDisableEventing() {
        transportBinding.deInitEventing();
        eventingInitialized.set(false);
    }

    class TransactionState {

        public TransactionState(long transactionId, InvocationState is) {
            this.transactionId = transactionId;
            this.invocationState = is;
        }

        public synchronized long getTransactionId() {
            return transactionId;
        }

        public synchronized void setTransactionId(long transactionId) {
            this.transactionId = transactionId;
        }

        public synchronized InvocationState getInvocationState() {
            return invocationState;
        }

        public synchronized void setInvocationState(InvocationState invocationState) {
            this.invocationState = invocationState;
        }

        private long transactionId;
        private InvocationState invocationState;

    }

    private final Mdib mdib = new Mdib();

    // Transport-specific binding (default MDPWS)
    private IClientBinding transportBinding;

    private final ConcurrentMap<String, Set<SDCConsumerHandler>> eventHandler = new ConcurrentHashMap<>();
    private final ConcurrentLinkedQueue<TransactionState> transactionQueue = new ConcurrentLinkedQueue<>();
    private final Map<Long, FutureInvocationState> fisMap = Collections.synchronizedMap(new MaxHashMap<>(256));

    interface IConsumerHandlerVisitor {

        void visit(SDCConsumerHandler handler);

    }

    class ConcurrentConsumerHandlerIterator {

        void accept(String handle, IConsumerHandlerVisitor visitor) {
            if (!eventHandler.containsKey(handle)) {
                return;
            }
            Set<SDCConsumerHandler> set = eventHandler.get(handle);
            synchronized (set) {
                set.forEach((next) -> {
                    visitor.visit(next);
                });
            }
        }
    }

    public SDCConsumer() {
    }

    public void setClientBinding(IClientBinding transportBinding) {
        this.transportBinding = transportBinding;
    }
    
    public String getXAdress() {
    	return this.transportBinding.getXAdress();
    }

    public synchronized void open() {
        if (transportBinding == null) {
            throw new IllegalStateException("Fatal error: transport binding missing for SDCConsumer!");
        }
        if (!transportBinding.init()) {
            return;
        }
        closed.set(false);
        eventingInitialized.set(false);
        schedulerId = scheduleAsync(() -> {
            if (!isConnected()) {
                for (SDCLifecycleHandler lcHandler : lifecycleHandlers) {
                    if (lcHandler != null && !lcHandler.isLostTriggered()) {
                        lcHandler.triggerLost(this);
                    }
                }
            } else {
                for (SDCLifecycleHandler lcHandler : lifecycleHandlers) {
                    if (lcHandler != null && lcHandler.isLostTriggered()) {
                        lcHandler.triggerReestablished(this);
                    }
                }
            }
            if (!isClosed()) {
                for (SDCLifecycleHandler lcHandler : lifecycleHandlers) {
                    if (lcHandler != null && lcHandler.isClosedTriggered()) {
                        lcHandler.triggerOpened(this);
                    }
                }
            }
        }, 1000, true);
    }

    public synchronized void close() {
        if (!closed.get()) {
            cancel(schedulerId);
            eventDeque = null;
            transportBinding.close();
            eventingInitialized.set(false);
            for (SDCLifecycleHandler lcHandler : lifecycleHandlers) {
                if (lcHandler != null && !lcHandler.isClosedTriggered()) {
                    lcHandler.triggerClosed(this);
                }
            }
        }
        closed.set(true);
    }

    public boolean isConnected() {
        return transportBinding.isConnected();
    }

    public boolean isClosed() {
        return closed.get();
    }

    /**
     * Fetch and return provider MDIB.
     *
     * @return The MDIB
     * @throws AsyncMethodInvocationException in case of failure
     */
    public Completer<Mdib> getMDIBAsync() {
        return async(() -> {
            GetMdibResponse response = complete(transportBinding.getGetOperationBinding().getMdib(new GetMdib()));
            synchronized (mdib) {
                Mdib temp = response.getMdib();
                mdib.setMdDescription(temp.getMdDescription());
                mdib.setMdState(temp.getMdState());
                mdib.setSequenceId(temp.getSequenceId());
                mdib.setMdibVersion(response.getMdibVersion());
                var cloned = BICEPSTypeConverter.cloneType(mdib, mdib.getClass());
                return cloned;
            }
        });
    }

    /**
     * Fetch and return provider MDIB (sync wrapper).
     *
     * @return The MDIB
     * @throws AsyncMethodInvocationException in case of failure
     */
    @Override
    public Mdib getMDIB() {
        try {
            return complete(getMDIBAsync());
        } catch (AsyncMethodInvocationException ex) {
            Logger.getLogger(SDCConsumer.class.getName()).log(Level.SEVERE, null, ex.getUnwrappedCause());
            throw ex;
        }
    }

    /**
     * Return provider MDD. Only fetches once.
     *
     * @return The MDD
     * @throws AsyncMethodInvocationException in case of failure
     */
    public Completer<MdDescription> getMDDescriptionAsync() {
        return async(() -> {
            synchronized (mdib) {
                if (mdib.getMdDescription() != null) {
                    var mdd = mdib.getMdDescription();
                    return BICEPSTypeConverter.cloneType(mdd, mdd.getClass());
                }
            }
            GetMdDescriptionResponse response = complete(transportBinding.getGetOperationBinding().getMdDescription(new GetMdDescription()));
            synchronized (mdib) {
                mdib.setMdDescription(response.getMdDescription());
                var mdd = mdib.getMdDescription();
                return BICEPSTypeConverter.cloneType(mdd, mdd.getClass());
            }
        });
    }

    /**
     * Return provider MDD. Only fetches once (sync wrapper).
     *
     * @return The MDD
     * @throws AsyncMethodInvocationException in case of failure
     */
    @Override
    public MdDescription getMDDescription() {
        try {
            return complete(getMDDescriptionAsync());
        } catch (AsyncMethodInvocationException ex) {
            Logger.getLogger(SDCConsumer.class.getName()).log(Level.SEVERE, null, ex.getUnwrappedCause());
            throw ex;
        }
    }

    /**
     * Fetch and return provider MDState.
     *
     * @return The MDState
     * @throws AsyncMethodInvocationException in case of failure
     */
    public Completer<MdState> getMDStateAsync() {
        return getMDStateAsync(null);
    }

    /**
     * Fetch and return provider MDState (sync wrapper).
     *
     * @return The MDState
     * @throws AsyncMethodInvocationException in case of failure
     */
    @Override
    public MdState getMDState() {
        return getMDState(null);
    }

    /**
     * Fetch and return provider MDState.
     *
     * @param handles The list of handles
     *
     * @return The MDState
     * @throws AsyncMethodInvocationException in case of failure
     */
    public Completer<MdState> getMDStateAsync(List<String> handles) {
        return async(() -> {
            GetMdState getState = new GetMdState();
            if (handles != null) {
                getState.getHandleRef().addAll(handles);
            }
            GetMdStateResponse response = complete(transportBinding.getGetOperationBinding().getMdState(getState));
            synchronized (mdib) {
                mdib.setMdState(response.getMdState());
                var state = mdib.getMdState();
                return BICEPSTypeConverter.cloneType(state, state.getClass());
            }
        });
    }

    /**
     * Fetch and return provider MDState (sync wrapper).
     *
     * @param handles The list of handles
     *
     * @return The MDState
     * @throws AsyncMethodInvocationException in case of failure
     */
    @Override
    public MdState getMDState(List<String> handles) {
        try {
            return complete(getMDStateAsync(handles));
        } catch (AsyncMethodInvocationException ex) {
            Logger.getLogger(SDCConsumer.class.getName()).log(Level.SEVERE, null, ex.getUnwrappedCause());
            throw ex;
        }
    }

    public <T extends AbstractState> Completer<T> requestStateAsync(String handle, Class<T> stateType) {
        return async(() -> {
            if (AbstractContextState.class.isAssignableFrom(stateType)) {
                GetContextStates getState = new GetContextStates();
                getState.getHandleRef().add(handle);
                GetContextStatesResponse response = complete(transportBinding.getGetOperationBinding().getContextStates(getState));
                final List<AbstractContextState> contextStates = response.getContextState();
                if (contextStates.isEmpty()) {
                    throw new IllegalStateException("No result returned!");
                }
                return stateType.cast(contextStates.get(0));
            }
            List<String> handles = new ArrayList<>();
            handles.add(handle);
            MdState mdState = complete(getMDStateAsync(handles));
            if (mdState == null || mdState.getState().isEmpty()) {
                throw new IllegalStateException("No result returned!");
            }
            return stateType.cast(mdState.getState().get(0));
        });
    }

    public <T extends AbstractState> T requestState(String handle, Class<T> stateType) {
        try {
            return complete(requestStateAsync(handle, stateType));
        } catch (AsyncMethodInvocationException ex) {
            Logger.getLogger(SDCConsumer.class.getName()).log(Level.SEVERE, null, ex.getUnwrappedCause());
            throw ex;
        }
    }

    public Completer<InvocationState> activateAsync(String handle, FutureInvocationState fis) {
        return async(() -> {
            Activate activate = new Activate();
            activate.setOperationHandleRef(handle);
            ActivateResponse response = complete(transportBinding.getSetOperationBinding().activate(activate));
            handleFutureInvocationState(response.getInvocationInfo().getTransactionId(), fis);
            return response.getInvocationInfo().getInvocationState();
        });
    }

    public InvocationState activate(String handle, FutureInvocationState fis) {
        try {
            return complete(activateAsync(handle, fis));
        } catch (AsyncMethodInvocationException ex) {
            Logger.getLogger(SDCConsumer.class.getName()).log(Level.SEVERE, null, ex.getUnwrappedCause());
            throw ex;
        }
    }

    public Completer<InvocationState> commitValueAsync(String descriptorHandle, NumericMetricValue value, FutureInvocationState fis) {
        NumericMetricState state = new NumericMetricState();
        state.setDescriptorHandle(descriptorHandle);
        state.setMetricValue(value);
        return commitStateAsync(state, fis, null, SetRequestType.VALUE);
    }

    public InvocationState commitValue(String descriptorHandle, NumericMetricValue value, FutureInvocationState fis) {
        return complete(commitValueAsync(descriptorHandle, value, fis));
    }

    public Completer<InvocationState> commitStringAsync(String descriptorHandle, StringMetricValue value, FutureInvocationState fis) {
        StringMetricState state = new StringMetricState();
        state.setDescriptorHandle(descriptorHandle);
        state.setMetricValue(value);
        return commitStateAsync(state, fis, null, SetRequestType.VALUE);
    }

    public InvocationState commitString(String descriptorHandle, StringMetricValue value, FutureInvocationState fis) {
        return complete(commitStringAsync(descriptorHandle, value, fis));
    }

    public Completer<InvocationState> commitStateAsync(AbstractState state, FutureInvocationState fis) {
        return commitStateAsync(state, fis, null);
    }

    public InvocationState commitState(AbstractState state, FutureInvocationState fis) {
        return commitState(state, fis, null);
    }

    public Completer<InvocationState> commitStateAsync(AbstractState state, FutureInvocationState fis, SafetyInfoType safetyInfo) {
        return commitStateAsync(state, fis, safetyInfo, SetRequestType.STATE);
    }

    public InvocationState commitState(AbstractState state, FutureInvocationState fis, SafetyInfoType safetyInfo) {
        return commitState(state, fis, safetyInfo, SetRequestType.STATE);
    }

    public Completer<InvocationState> commitStateAsync(AbstractState state, FutureInvocationState fis, SafetyInfoType safetyInfo, SetRequestType reqType) {
        return async(() -> {
            AbstractSet reqObj = createSetRequest(state, reqType);
            if (reqObj == null) {
                return InvocationState.FAIL;
            }
            reqObj.setOperationHandleRef(SDCToolbox.getFirstOperationHandleForOperationTarget(this, state.getDescriptorHandle()));
            if (reqObj.getOperationHandleRef() == null) {
                return InvocationState.FAIL;
            }
            AbstractSetResponse response = complete(transportBinding.getSetOperationBinding().setState(reqObj, safetyInfo));
            handleFutureInvocationState(response.getInvocationInfo().getTransactionId(), fis);
            return response.getInvocationInfo().getInvocationState();
        });
    }

    private InvocationState commitState(AbstractState state, FutureInvocationState fis, SafetyInfoType safetyInfo, SetRequestType reqType) {
        try {
            return complete(commitStateAsync(state, fis, safetyInfo, reqType));
        } catch (AsyncMethodInvocationException ex) {
            Logger.getLogger(SDCConsumer.class.getName()).log(Level.SEVERE, null, ex.getUnwrappedCause());
            throw ex;
        }
    }

    public Completer<String> exchangeRawAsync(String rawData, String serviceId) {
        return transportBinding.getSetOperationBinding().exchangeRaw(rawData, serviceId);
    }

    public String exchangeRaw(String rawData, String serviceId) {
        return complete(exchangeRawAsync(rawData, serviceId));
    }

    private AbstractSet createSetRequest(AbstractState state, SetRequestType reqType) {
        AbstractSet reqObj = null;
        if (state instanceof NumericMetricState) {
            if (reqType == SetRequestType.VALUE) {
                reqObj = new SetValue();
                ((SetValue) reqObj).setRequestedNumericValue(((NumericMetricState) state).getMetricValue().getValue());
            } else if (reqType == SetRequestType.STATE) {
                reqObj = new SetMetricState();
                ((SetMetricState) reqObj).getProposedMetricState().add((AbstractMetricState) state);
            }
        } else if (state instanceof StringMetricState) {
            if (reqType == SetRequestType.VALUE) {
                reqObj = new SetString();
                ((SetString) reqObj).setRequestedStringValue(((StringMetricState) state).getMetricValue().getValue());
            } else if (reqType == SetRequestType.STATE) {
                reqObj = new SetMetricState();
                ((SetMetricState) reqObj).getProposedMetricState().add((AbstractMetricState) state);
            }
        } else if (state instanceof AbstractContextState) {
            reqObj = new SetContextState();
            ((SetContextState) reqObj).getProposedContextState().add((AbstractContextState) state);
        } else if (state instanceof AbstractAlertState) {
            reqObj = new SetAlertState();
            ((SetAlertState) reqObj).setProposedAlertState((AbstractAlertState) state);
        }
        return reqObj;
    }

    public void addEventHandler(SDCConsumerHandler handler) {
        if (!eventHandler.containsKey(handler.getDescriptorHandle())) {
            eventHandler.put(handler.getDescriptorHandle(), Collections.newSetFromMap(new ConcurrentHashMap<>()));
        }
        eventHandler.get(handler.getDescriptorHandle()).add(handler);
        internalCheckEventingInit();
    }

    public void removeEventHandler(SDCConsumerHandler handler) {
        if (!eventHandler.containsKey(handler.getDescriptorHandle())) {
            return;
        }
        Set<SDCConsumerHandler> list = eventHandler.get(handler.getDescriptorHandle());
        list.remove(handler);
        if (eventHandlesEmpty()) {
            internalDisableEventing();
        }
    }

    public void clearEventHandlers() {
        eventHandler.clear();
        internalDisableEventing();
    }

    public boolean eventHandlesEmpty() {
        for (Set<SDCConsumerHandler> list : eventHandler.values()) {
            if (!list.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void removeAllEventHandlers(String handle) {
        eventHandler.remove(handle);
        if (eventHandlesEmpty()) {
            internalDisableEventing();
        }
    }

    public void addLifecycleHandler(SDCLifecycleHandler lcHandler) {
        this.lifecycleHandlers.add(lcHandler);
    }

    public void removeLifecycleHandler(SDCLifecycleHandler lcHandler) {
        this.lifecycleHandlers.remove(lcHandler);
    }

    public void addRawReportHandler(ISDCConsumerGlobalReportRawHandler rawHandler) {
        this.rawReportHandlers.add(rawHandler);
    }

    public void removeRawReportHandler(ISDCConsumerGlobalReportRawHandler rawHandler) {
        this.rawReportHandlers.remove(rawHandler);
    }

    public void handleStreamReceived(WaveformStream wfs) {
        for (RealTimeSampleArrayMetricState rtsams : wfs.getState()) {
            enqueueEvent(rtsams);
            new ConcurrentConsumerHandlerIterator().accept(rtsams.getDescriptorHandle(), (handler) -> {
                if (handler instanceof ISDCConsumerStateChangedHandler) {
                    ((ISDCConsumerStateChangedHandler) handler).onStateChanged(rtsams);
                }
            });
        }
    }

    protected void unregisterFutureInvocationstate(FutureInvocationState fis) {
        fisMap.remove(fis.getTransactionId());
    }

    private void handleFutureInvocationState(long transactionId, FutureInvocationState fis) {
        if (fisMap.containsValue(fis)) {
            throw new IllegalStateException("FutureInvocationState instance already in use!");
        }
        fis.setTransactionId(transactionId);
        fis.setConsumer(this);
        fisMap.put(transactionId, fis);
        // Dequeue possible intermediate events
        while (transactionQueue.size() > 0) {
            TransactionState ts = transactionQueue.poll();
            if (fisMap.containsKey(ts.getTransactionId())) {
                fisMap.get(ts.getTransactionId()).setActual(ts.getInvocationState());
            }
        }
    }

    public void handleRawReport(Buffer buffer) {
        rawReportHandlers.forEach((handler) -> {
            try {
                handler.handleRawReport(new String(buffer.getBytes()));
            } catch (Exception e) {
                Logger.getLogger(SDCConsumer.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public void handleAlertReportPart(AbstractAlertReport.ReportPart part) {
        for (AbstractAlertState next : part.getAlertState()) {
            enqueueEvent(next);
            new ConcurrentConsumerHandlerIterator().accept(next.getDescriptorHandle(), (handler) -> {
                if (handler instanceof ISDCConsumerStateChangedHandler) {
                    ((ISDCConsumerStateChangedHandler) handler).onStateChanged(next);
                }
            });
        }
    }

    public void handleMetricReportPart(AbstractMetricReport.ReportPart part) {
        for (AbstractMetricState next : part.getMetricState()) {
            enqueueEvent(next);
            new ConcurrentConsumerHandlerIterator().accept(next.getDescriptorHandle(), (handler) -> {
                if (handler instanceof ISDCConsumerStateChangedHandler) {
                    ((ISDCConsumerStateChangedHandler) handler).onStateChanged(next);
                }
            });
        }
    }

    public void handleContextReportPart(AbstractContextReport.ReportPart part) {
        async(() -> {
            for (AbstractContextState next : part.getContextState()) {
                enqueueEvent(next);
                new ConcurrentConsumerHandlerIterator().accept(next.getDescriptorHandle(), (handler) -> {
                    if (handler instanceof ISDCConsumerStateChangedHandler) {
                        ((ISDCConsumerStateChangedHandler) handler).onStateChanged(next);
                    }
                });
            }
        });
    }

    public void handleOperationInvoked(OperationInvokedReport oir) {
        for (OperationInvokedReport.ReportPart part : oir.getReportPart()) {
            String target = part.getOperationTarget();
            String handleRef = part.getOperationHandleRef();
            String handlerKey;
            if (target == null) {
                target = SDCToolbox.getOperationTargetForOperationHandle(this, part.getOperationHandleRef());
            }
            // Use OperationHandleRef from OperationInvokedReport
            if (target == null) {
                target = handleRef;
            }
            if (target == null) {
                Logger.getLogger(SDCConsumer.class.getName()).log(Level.WARNING, "Error in operation invoked report, can't resolve target: " + part.getInvocationInfo().getInvocationState().toString());
                return;
            }
            if (eventHandler.containsKey(part.getOperationHandleRef())) {
                // Activate operations use operation handle
                handlerKey = part.getOperationHandleRef();
            } else {
                handlerKey = target;
            }
            final long transactionId = part.getInvocationInfo().getTransactionId();
            final InvocationState invocationState = part.getInvocationInfo().getInvocationState();
            // Queue to check intermediate events during commits
            transactionQueue.add(new TransactionState(transactionId, invocationState));
            // Notify about future invocation state events
            if (fisMap.containsKey(transactionId)) {
                fisMap.get(transactionId).setActual(invocationState);
            }
            OperationInvocationContext oic = new OperationInvocationContext(part.getOperationHandleRef(), target, transactionId);
            new ConcurrentConsumerHandlerIterator().accept(handlerKey, (handler) -> {
                if (handler instanceof ISDCConsumerOperationInvokedHandler) {
                    ((ISDCConsumerOperationInvokedHandler) handler).onOperationInvoked(oic, invocationState);
                }
            });
        }
    }

    @Override
    public String getEndpointReference() {
        return transportBinding.getEnpointReference();
    }

    @Override
    public int hashCode() {
        return getEndpointReference().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SDCConsumer)) {
            return false;
        }
        SDCConsumer other = (SDCConsumer) obj;
        return this.getEndpointReference().equals(other.getEndpointReference());
    }

}
