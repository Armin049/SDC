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

import com.bestingit.async.AsyncCancellationException;
import com.bestingit.async.CancellationInterrupt;
import static com.bestingit.async.Task.async;
import static com.bestingit.async.Task.blocking;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.ornet.cdm.AbstractMetricState;
import org.ornet.cdm.AbstractOperationState;
import org.ornet.cdm.AbstractSet;
import org.ornet.cdm.AbstractSetResponse;
import org.ornet.cdm.AbstractState;
import org.ornet.cdm.Activate;
import org.ornet.cdm.CtxtValueType;
import org.ornet.cdm.InvocationInfo;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.NumericMetricState;
import org.ornet.cdm.OperatingMode;
import org.ornet.cdm.SafetyContextType;
import org.ornet.cdm.SetAlertState;
import org.ornet.cdm.SetContextState;
import org.ornet.cdm.SetString;
import org.ornet.cdm.SetValue;
import org.ornet.cdm.StringMetricState;
import org.ornet.cdm.SafetyInfoType;
import org.ornet.cdm.SetMetricState;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.SDCToolbox;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.common.MaxHashMap;
import org.ornet.sdclib.provider.OperationInvocationContext;
import org.ornet.sdclib.provider.SDCProvider;
import org.ornet.sdclib.provider.SDCProviderActivateOperationHandler;
import org.ornet.sdclib.provider.SDCProviderHandler;
import org.ornet.sdclib.provider.SDCProviderMDStateHandler;
import org.w3c.dom.Document;

public class BICEPSProviderSetOperationBinding implements IProviderSetOperationBinding {

    private final SDCProvider provider;

    private static class SetableStateHandler {

        public SDCProviderMDStateHandler handler;
        public AbstractState state;
    }

    private final AtomicLong transactionId = new AtomicLong();
    private final LinkedBlockingQueue<SetNotification> queue = new LinkedBlockingQueue<>();
    private final Map<String, SetableStateHandler> checkStateCache = Collections.synchronizedMap(new MaxHashMap<>(64));
    private final XPath xPathEvaluator = XPathFactory.newInstance().newXPath();
    private final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder domBuilder = null;
    private CancellationInterrupt cancellationInterrupt;

    BICEPSProviderSetOperationBinding(SDCProvider provider) {
        this.provider = provider;
        try {
            domFactory.setNamespaceAware(true);
            domBuilder = domFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BICEPSProviderSetOperationBinding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public <U extends AbstractSet, V extends AbstractSetResponse> V onSetState(U request, Class<V> res, SafetyInfoType si) {
        long currentTid = queueRequest(provider, request, si);

        V response;
        try {
            response = res.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BICEPSProviderSetOperationBinding.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        prepareResponse(response, currentTid);

        response.setMdibVersion(provider.getMdibVersion());
        response.setSequenceId("0");

        return response;
    }

    @Override
    public void init() {
        cancellationInterrupt = new CancellationInterrupt();
        blocking((interrupt) -> {
            while (true) {
                 try {
                     SetNotification sn = queue.poll(100, TimeUnit.MILLISECONDS);
                     interrupt.throwIfInterrupted();
                     if (sn == null) {
                         continue;
                     }
                     Object request = sn.getRequest();
                     if (request instanceof SetValue) {
                         trySetValue(sn.getProvider(), (SetValue) request, sn.getContext());
                     } else if (request instanceof SetString) {
                         trySetString(sn.getProvider(), (SetString) request, sn.getContext());
                     } else if (request instanceof SetContextState) {
                         trySetContext(sn.getProvider(), (SetContextState) request, sn.getContext());
                     } else if (request instanceof SetAlertState) {
                         trySetAlert(sn.getProvider(), (SetAlertState) request, sn.getContext());
                     } else if (request instanceof Activate) {
                         tryActivate(sn.getProvider(), (Activate) request, sn.getContext());
                     } else if (request instanceof SetMetricState) {
                         trySetMetricState(sn.getProvider(), (SetMetricState) request, sn.getContext());
                     }
                 } catch (AsyncCancellationException ce) {
                     break;
                 } catch (Exception ex) {
                     Logger.getLogger(SDCProvider.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }, cancellationInterrupt);
    }

    @Override
    public void shutdown() {
        cancellationInterrupt.setInterrupted(true);
    }

    private static class SetNotification<R> {

        private final R request;
        private final OperationInvocationContext context;
        private final SDCProvider provider;

        public SetNotification(SDCProvider provider, R request, OperationInvocationContext context) {
            this.provider = provider;
            this.request = request;
            this.context = context;
        }

        public R getRequest() {
            return request;
        }

        public OperationInvocationContext getContext() {
            return context;
        }

        public SDCProvider getProvider() {
            return provider;
        }
    }

    public void updateSetableStateCache(SDCProvider provider, AbstractState state) {
        async(() -> {
            String oh = SDCToolbox.getFirstOperationHandleForOperationTarget(provider, state.getDescriptorHandle());
            if (oh == null) {
                // State was never intended to be setable due to missing SCO, skip this
                return;
            }
            OperationInvocationContext c = new OperationInvocationContext(oh, state.getDescriptorHandle(), 0);
            getSetableStateHandler(provider, c);
        });
    }

    private SetableStateHandler getSetableStateHandler(SDCProvider provider, OperationInvocationContext context) {
        SetableStateHandler sh = checkStateCache.get(context.getOperationHandle());
        if (sh == null) {
            String targetHandle = SDCToolbox.getOperationTargetForOperationHandle(provider, context.getOperationHandle());
            if (targetHandle == null) {
                provider.notifyOperationInvoked(context, InvocationState.FAIL, "No set-operation descriptor found");
                return null;
            }
            AbstractOperationState os = SDCToolbox.findState(provider, context.getOperationHandle(), AbstractOperationState.class);
            if (os == null || os.getOperatingMode() != OperatingMode.EN) {
                provider.notifyOperationInvoked(context, InvocationState.FAIL, "No operation target state or wrong operational state (must be enabled)");
                return null;
            }
            sh = new SetableStateHandler();
            sh.state = SDCToolbox.findState(provider, targetHandle, AbstractState.class);
            if (sh.state == null) {
                provider.notifyOperationInvoked(context, InvocationState.FAIL, "No state (probably wrong handle)");
                return null;
            }
            if (sh.state instanceof AbstractMetricState) {
                if (!SDCToolbox.isMetricChangeAllowed(provider, (AbstractMetricState) sh.state)) {
                    provider.notifyOperationInvoked(context, InvocationState.FAIL, "Requirements of metric violated");
                    return null;
                }
            }
            Map<String, SDCProviderHandler> handlers = provider.getStateHandlers();
            for (Entry<String, SDCProviderHandler> nextEntry : handlers.entrySet()) {
                if (nextEntry.getKey().equals(targetHandle) && nextEntry.getValue() instanceof SDCProviderMDStateHandler) {
                    sh.handler = (SDCProviderMDStateHandler) nextEntry.getValue();
                    break;
                }
            }
            if (sh.handler == null) {
                provider.notifyOperationInvoked(context, InvocationState.FAIL, "No state handler found on provider's side");
                return null;
            }
            // State is setable
            checkStateCache.put(context.getOperationHandle(), sh);
        }
        return sh;
    }

    private void trySetState(SDCProviderMDStateHandler handler, AbstractState state, OperationInvocationContext context, SDCProvider provider) {
        InvocationState is = handler.onStateChangeRequest(state, context);
        if (is.equals(InvocationState.FIN) || is.equals((InvocationState.FIN_MOD))) {
            provider.updateState(state, handler.isNotifyOnFinshed());
            provider.notifyOperationInvoked(context, InvocationState.FIN, null);
        } else {
            provider.notifyOperationInvoked(context, is, null);
        }
    }

    private void trySetValue(SDCProvider provider, SetValue sv, OperationInvocationContext context) {
        SetableStateHandler sh;
        if ((sh = getSetableStateHandler(provider, context)) == null) {
            return;
        }
        provider.notifyOperationInvoked(context, InvocationState.START, null);
        NumericMetricState nms = (NumericMetricState) sh.state;
        if (nms.getMetricValue() == null) {
            provider.notifyOperationInvoked(context, InvocationState.FAIL, "Observed value is null");
            return;
        }
        nms.getMetricValue().setValue(sv.getRequestedNumericValue());
        trySetState(sh.handler, nms, context, provider);
    }

    private void trySetMetricState(SDCProvider provider, SetMetricState ms, OperationInvocationContext context) {
        ms.getProposedMetricState().forEach((state) -> {
            SetableStateHandler sh;
            if ((sh = getSetableStateHandler(provider, context)) == null) {
                return;
            }
            provider.notifyOperationInvoked(context, InvocationState.START, null);
            trySetState(sh.handler, state, context, provider);
        });
    }

    private void trySetString(SDCProvider provider, SetString ss, OperationInvocationContext context) {
        SetableStateHandler sh;
        if ((sh = getSetableStateHandler(provider, context)) == null) {
            return;
        }
        provider.notifyOperationInvoked(context, InvocationState.START, null);
        StringMetricState sms = (StringMetricState) sh.state;
        if (sms.getMetricValue() == null) {
            provider.notifyOperationInvoked(context, InvocationState.FAIL, "Observed value is null");
            return;
        }
        sms.getMetricValue().setValue(ss.getRequestedStringValue());
        trySetState(sh.handler, sms, context, provider);
    }

    private void trySetContext(SDCProvider provider, SetContextState sc, OperationInvocationContext context) {
        sc.getProposedContextState().forEach((nextCS) -> {
            SetableStateHandler sh;
            if ((sh = getSetableStateHandler(provider, context)) != null) {
                provider.notifyOperationInvoked(context, InvocationState.START, null);
                trySetState(sh.handler, nextCS, context, provider);
            }
        });
    }

    private void trySetAlert(SDCProvider provider, SetAlertState sa, OperationInvocationContext context) {
        SetableStateHandler sh;
        if ((sh = getSetableStateHandler(provider, context)) == null) {
            return;
        }
        provider.notifyOperationInvoked(context, InvocationState.START, null);
        trySetState(sh.handler, sa.getProposedAlertState(), context, provider);
    }

    private void tryActivate(SDCProvider provider, Activate activate, OperationInvocationContext context) {
        SDCProviderActivateOperationHandler handler = null;
        Map<String, SDCProviderHandler> handlers = provider.getStateHandlers();
        for (Entry<String, SDCProviderHandler> nextEntry : handlers.entrySet()) {
            if (nextEntry.getKey().equals(context.getOperationHandle()) && nextEntry.getValue() instanceof SDCProviderActivateOperationHandler) {
                handler = (SDCProviderActivateOperationHandler) nextEntry.getValue();
                break;
            }
        }
        if (handler == null) {
            provider.notifyOperationInvoked(context, InvocationState.FAIL, "Activate handler is null");
            return;
        }
        provider.notifyOperationInvoked(context, InvocationState.START, null);
        InvocationState is = handler.onActivateRequest(provider.getMDIB(), context);
        if (is.equals(InvocationState.FIN) || is.equals(InvocationState.FIN_MOD)) {
            provider.notifyOperationInvoked(context, InvocationState.FIN, null);
        } else {
            provider.notifyOperationInvoked(context, is, null);
        }
    }

    private long queueRequest(final SDCProvider provider, AbstractSet request, SafetyInfoType si) {
        long currentTid = transactionId.addAndGet(1);
        String opTarget = SDCToolbox.getOperationTargetForOperationHandle(provider, request.getOperationHandleRef());
        OperationInvocationContext oic = new OperationInvocationContext(request.getOperationHandleRef(), opTarget, currentTid);
        boolean safetyInfoChecked = evaluateSafetyInfo(si, provider);

        if (!safetyInfoChecked) {
            provider.notifyOperationInvoked(oic, InvocationState.FAIL, "SafetyInfo evaluation prevented executing request.");
            return currentTid;
        }

        provider.notifyOperationInvoked(oic, InvocationState.WAIT, null);
        queue.add(new SetNotification<>(provider, request, oic));
        return currentTid;
    }

    private boolean evaluateSafetyInfo(SafetyInfoType si, final SDCProvider provider) {
        if (si == null || si.getSafetyContext() == null) {
            return true;
        }
        boolean safetyInfoChecked = true;
        final SafetyContextType sc = si.getSafetyContext();
        for (CtxtValueType val : sc.getCtxtValue()) {
            try {
                String xPathExpression = SDCToolbox.getSafetyReqValueBySelectorId(provider, val.getReferencedSelector());
                SDCLib.getInstance().getLogger().log(Level.FINE, "Running xPath expression: {0}", xPathExpression);
                XPathExpression expr = xPathEvaluator.compile(xPathExpression);
                if (xPathExpression == null) {
                    continue;
                }
                String providedValue = val.getValue().toString();
                Document doc = domBuilder.newDocument();
                JAXBUtil.getInstance().noneRootObjectToDOMDoc(provider.getMDIB(), doc, true);
                if (SDCLib.getInstance().getLogger().getLevel() == Level.FINEST) {
                    String str = JAXBUtil.getInstance().nodeToString(doc);
                    SDCLib.getInstance().getLogger().log(Level.FINEST, "MDIB used for x-Path: {0}", str);
                }
                final String xPathQuery = expr.evaluate(doc);
                SDCLib.getInstance().getLogger().log(Level.FINE, "Provided safety value: {0}", providedValue);
                SDCLib.getInstance().getLogger().log(Level.FINE, "Current x-Path result: {0}", xPathQuery);
                safetyInfoChecked = xPathQuery.equals(providedValue);
                SDCLib.getInstance().getLogger().log(Level.FINE, "Safety context evaluation: {0}", safetyInfoChecked);
            } catch (Exception ex) {
                Logger.getLogger(BICEPSProviderSetOperationBinding.class.getName()).log(Level.SEVERE, null, ex);
                safetyInfoChecked = false;
            }
        }
        return safetyInfoChecked;
    }

    private void prepareResponse(AbstractSetResponse response, long currentTid) {
        InvocationInfo invInf = new InvocationInfo();
        invInf.setTransactionId(currentTid);
        invInf.setInvocationState(InvocationState.WAIT);
        response.setInvocationInfo(invInf);
    }

    @Override
    public void onStateChanged(AbstractState state) {
        updateSetableStateCache(provider, state);
    }

}
