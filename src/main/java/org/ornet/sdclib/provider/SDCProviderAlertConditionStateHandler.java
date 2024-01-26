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

import java.math.BigDecimal;
import org.ornet.sdclib.SDCToolbox;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ornet.cdm.AbstractAlertState;
import org.ornet.cdm.AbstractMetricState;
import org.ornet.cdm.AlertSignalDescriptor;
import org.ornet.cdm.AlertSignalPresence;
import org.ornet.cdm.AlertConditionState;
import org.ornet.cdm.AlertSignalState;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.LimitAlertConditionState;
import org.ornet.cdm.NumericMetricState;
import org.ornet.cdm.Range;

public abstract class SDCProviderAlertConditionStateHandler<T extends AlertConditionState> extends SDCProviderMDStateHandler<T> {

    public SDCProviderAlertConditionStateHandler(String descriptorHandle) {
        super(descriptorHandle);
    }

    public void sourceHasChanged(AbstractMetricState source, T conditionState) {
        if (conditionState instanceof LimitAlertConditionState) {
            // Auto-check bounds
            if (!(source instanceof NumericMetricState)) {
                return;
            }
            NumericMetricState nms = (NumericMetricState) source;
            if (nms.getMetricValue() == null) {
                return;
            }
            BigDecimal nv;
            if ((nv = nms.getMetricValue().getValue()) == null) {
                return;
            }
            LimitAlertConditionState lac = (LimitAlertConditionState) conditionState;
            Range limits;
            if ((limits = lac.getLimits()) == null) {
                return;
            }
            if (limits.getUpper() == null || limits.getLower() == null) {
                return;
            }
            double val = nv.doubleValue();
            boolean trigger = (val > limits.getUpper().doubleValue() || val < limits.getLower().doubleValue());
            // The condition will eventually trigger all signals
            setAlertConditionPresence(conditionState, trigger);
        }
    }

    public void setAlertConditionPresence(AlertConditionState currentState, boolean trigger) {
        // Modify and update condition state in internal MDIB
        currentState.setPresence(trigger);
        if (onStateChangeRequest((T) currentState, null) == InvocationState.FIN) {
            updateState((T) currentState);
        } else {
            return;
        }

        // Search all alert signal descriptors with reference to the alert condition descriptor handler
        Map<String, Boolean> signalHandlesLatching = new HashMap<>();
        Set<AlertSignalDescriptor> signalDescriptors = SDCToolbox.collectAllAlertSignalDescriptors(provider);
        for (AlertSignalDescriptor next : signalDescriptors) {
            if (next.getConditionSignaled().equals(currentState.getDescriptorHandle())) {
                signalHandlesLatching.put(next.getHandle(), next.isLatching());
            }
        }

        // Search all relevant signal states
        List<AbstractAlertState> alertStates = provider.getAlertStates(Arrays.asList(signalHandlesLatching.keySet().toArray(new String[0])));
        for (AbstractAlertState next : alertStates) {
            if (!(next instanceof AlertSignalState)) {
                continue;
            }
            AlertSignalState cas = (AlertSignalState) next;
            if (trigger) {
                inform(cas, AlertSignalPresence.ON);
            } else {
                if (cas.getPresence() == null) {
                    inform(cas, AlertSignalPresence.OFF);
                } else if (signalHandlesLatching.get(cas.getDescriptorHandle())) { // is latching?
                    switch (cas.getPresence()) {
                        case ON:
                            inform(cas, AlertSignalPresence.LATCH);
                            break;
                        default:
                            break;
                    }
                } else {
                    inform(cas, AlertSignalPresence.OFF);
                }
            }
        }
    }

    private void inform(AlertSignalState cas, AlertSignalPresence signalPresence) {
        cas.setPresence(signalPresence);
        SDCProviderHandler handler = provider.getHandler(cas.getDescriptorHandle());
        if (handler == null || !(handler instanceof SDCProviderMDStateHandler)) {
            Logger.getLogger(SDCProvider.class.getName()).log(Level.SEVERE, "Error in updating alert signals. Handler missing for: {0}", cas.getDescriptorHandle());
            return;
        }
        SDCProviderMDStateHandler stateHandler = (SDCProviderMDStateHandler) handler;
        if (stateHandler.onStateChangeRequest(cas, null) == InvocationState.FIN) {
            stateHandler.updateState(cas);
        }
    }

}
