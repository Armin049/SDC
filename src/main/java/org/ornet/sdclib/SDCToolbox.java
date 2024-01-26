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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBElement;

import org.ornet.cdm.*;
import org.ornet.sdclib.common.MaxHashMap;
import org.ornet.sdclib.provider.SDCEndpoint;

public class SDCToolbox {

    private static final Map<String, String> OPERATION_TARGET_CACHE = Collections.synchronizedMap(new MaxHashMap<>(64));
    private static final Map<String, String> OPERATION_HANDLE_CACHE = Collections.synchronizedMap(new MaxHashMap<>(64));

    private static String getKey(SDCEndpoint ep, String value) {
        return ep.getEndpointReference() + ":" + value;
    }

    public static String getSafetyReqValueBySelectorId(SDCEndpoint ep, String selectorId) {
        MdDescription mdd = ep.getMDDescription();
        for (MdsDescriptor mds : mdd.getMds()) {
            for (AbstractOperationDescriptor aod : mds.getSco().getOperation()) {
                ExtensionType ext = aod.getExtension();
                if (ext == null) {
                    continue;
                }
                List<Object> any = ext.getAny();
                if (any.size() == 1 && any.get(0) instanceof JAXBElement) {
                    JAXBElement<SafetyReqType> sre = (JAXBElement<SafetyReqType>) any.get(0);
                    SafetyContextDefType sc = sre.getValue().getSafetyContextDef();
                    var res = sc.getSelector().stream().filter(s -> s.getId().equals(selectorId)).findFirst();
                    if (res.isPresent())
                        return res.get().getValue();
                }
            }
        }
        return null;
    }

    public static String getOperationTargetForOperationHandle(SDCEndpoint ep, String operationHandle) {
        String res;
        if ((res = OPERATION_TARGET_CACHE.get(getKey(ep, operationHandle))) != null) {
            return res;
        }
        MdDescription mdd = ep.getMDDescription();
        for (MdsDescriptor nextMDS : mdd.getMds()) {
            if (!(nextMDS instanceof MdsDescriptor)) {
                continue;
            }
            MdsDescriptor hMDS = (MdsDescriptor) nextMDS;
            if (hMDS.getSco() != null) {
                var opRes = hMDS.getSco().getOperation().stream().filter(op -> op.getHandle().equals(operationHandle)).findFirst();
                if (opRes.isPresent()) {
                    OPERATION_TARGET_CACHE.put(getKey(ep, operationHandle), opRes.get().getOperationTarget());
                    return opRes.get().getOperationTarget();
                }
            }

            // Search in VMDs
            for(VmdDescriptor nextVMD : hMDS.getVmd()) {
                if (nextVMD.getSco() != null) {
                    var opResVmd = hMDS.getSco().getOperation().stream().filter(op -> op.getHandle().equals(operationHandle)).findFirst();
                    if (opResVmd.isPresent()) {
                        OPERATION_TARGET_CACHE.put(getKey(ep, operationHandle), opResVmd.get().getOperationTarget());
                        return opResVmd.get().getOperationTarget();
                    }
                }
            }
        }
        return null;
    }

    public static String getFirstOperationHandleForOperationTarget(SDCEndpoint ep, String operationTarget) {
        String res;
        if ((res = OPERATION_HANDLE_CACHE.get(getKey(ep, operationTarget))) != null) {
            return res;
        }
        MdDescription mdd = ep.getMDDescription();
        for (MdsDescriptor nextMDS : mdd.getMds()) {
            if (nextMDS.getSco() != null) {
                var opRes = nextMDS.getSco().getOperation().stream().filter(op -> op.getOperationTarget().equals(operationTarget)).findFirst();
                if (opRes.isPresent()) {
                    OPERATION_TARGET_CACHE.put(getKey(ep, operationTarget), opRes.get().getHandle());
                    return opRes.get().getHandle();
                }
            }
            // Search in VMDs
            for(VmdDescriptor nextVMD : nextMDS.getVmd()) {
                if (nextVMD.getSco() != null) {
                    var opResVmd = nextVMD.getSco().getOperation().stream().filter(op -> op.getOperationTarget().equals(operationTarget)).findFirst();
                    if (opResVmd.isPresent()) {
                        OPERATION_TARGET_CACHE.put(getKey(ep, operationTarget), opResVmd.get().getHandle());
                        return opResVmd.get().getHandle();
                    }
                }
            }
        }
        return null;
    }

    public static AbstractMetricDescriptor findMetricDescriptor(SDCEndpoint ep, String handle) {
        MdDescription mdd = ep.getMDDescription();
        for (MdsDescriptor nextMDS : mdd.getMds()) {
            for (VmdDescriptor nextVmd : nextMDS.getVmd()) {
                for (ChannelDescriptor nextChn : nextVmd.getChannel()) {
                    for (AbstractMetricDescriptor nextMet : nextChn.getMetric()) {
                        if (nextMet.getHandle().equals(handle)) {
                            return nextMet;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static AbstractMetricDescriptor findReferencedMetricDescriptor(SDCEndpoint ep, AbstractState s) {
        return findMetricDescriptor(ep, s.getDescriptorHandle());
    }

    public static AbstractMetricState findMetricState(SDCEndpoint ep, String handle) {
        return findState(ep, handle, AbstractMetricState.class);
    }

    public static AbstractContextState findContextState(SDCEndpoint ep, String handle) {
        return findState(ep, handle, AbstractContextState.class);
    }

    public static AbstractContextDescriptor findContextDescritor(SDCEndpoint ep, String handle) {
        MdDescription mdd = ep.getMDDescription();
        for (MdsDescriptor nextMDS : mdd.getMds()) {
            if (nextMDS.getSystemContext() != null ) {
                SystemContextDescriptor scd = nextMDS.getSystemContext();
                for ( EnsembleContextDescriptor ecd : scd.getEnsembleContext() ) {
                    if (ecd.getHandle().equals(handle)) {
                        return ecd;
                    }
                }
                if ( scd.getLocationContext() != null ) {
                    if (scd.getLocationContext().getHandle().equals(handle)) {
                        return scd.getLocationContext();
                    }
                }
                for ( MeansContextDescriptor mcd : scd.getMeansContext() ) {
                    if (mcd.getHandle().equals(handle)) {
                        return mcd;
                    }
                }
                for ( OperatorContextDescriptor ocd : scd.getOperatorContext() ) {
                    if ( ocd.getHandle().equals(handle)) {
                        return ocd;
                    }
                }
                if (scd.getPatientContext() != null ) {
                    if (scd.getPatientContext().getHandle().equals(handle)) {
                        return scd.getPatientContext();
                    }
                }
                for ( WorkflowContextDescriptor wcd : scd.getWorkflowContext() ) {
                    if (wcd.getHandle().equals(handle)) {
                        return wcd;
                    }
                }
            }

        }
        return null;
    }

    public static <T extends AbstractState> T findState(SDCEndpoint ep, String handle, Class<T> type) {
        List<String> handles = new ArrayList<>();
        handles.add(handle);
        MdState mdState = ep.getMDState(handles);
        List<AbstractState> stateList = mdState.getState();
        if (stateList.isEmpty()) {
            return null;
        }
        return type.cast(stateList.get(0));
    }

    public static boolean isMetricChangeAllowed(SDCEndpoint ep, AbstractMetricState state) {
        AbstractMetricDescriptor md = findMetricDescriptor(ep, state.getDescriptorHandle());
        if (md == null) {
            return false;
        }
        if (md.getMetricCategory() == MetricCategory.MSRMT) {
            return false;
        }
        ComponentActivation ca = state.getActivationState();
        if (ca != null) {
            return ca.equals(ComponentActivation.ON);
        }
        return true;
    }

    static abstract class AlertDescriptorGrabber<T extends AbstractAlertDescriptor> {

        abstract List<T> getAlertDescriptors(AlertSystemDescriptor as);
    }

    public static <T extends AbstractMetricDescriptor> Map<MdsDescriptor, List<T>> collectAllMetricDescriptors(SDCEndpoint ep) {
        Map<MdsDescriptor, List<T>> descriptors = new HashMap<>();
        MdDescription mdd = ep.getMDDescription();
        for (MdsDescriptor nextMDS : mdd.getMds()) {
            if (!descriptors.containsKey(nextMDS)) {
                descriptors.put(nextMDS, new LinkedList<>());
            }
            for (VmdDescriptor nextVmd : nextMDS.getVmd()) {
                for (ChannelDescriptor nextChn : nextVmd.getChannel()) {
                    for (AbstractMetricDescriptor nextMet : nextChn.getMetric()) {
                        descriptors.get(nextMDS).add((T) nextMet);
                    }
                }
            }
        }
        return descriptors;
    }

    private static <T extends AbstractAlertDescriptor> Map<MdsDescriptor, Set<T>> collectAllAlertDescriptorsMds(SDCEndpoint ep, AlertDescriptorGrabber<T> adg) {
        Map<MdsDescriptor, Set<T>> descriptors = new HashMap<>();
        MdDescription mdd = ep.getMDDescription();
        for (MdsDescriptor mds : mdd.getMds()) {
            if (!descriptors.containsKey(mds)) {
                descriptors.put(mds, new HashSet<>());
            }
            if (mds.getAlertSystem() != null) {
                descriptors.get(mds).addAll(adg.getAlertDescriptors(mds.getAlertSystem()));
            }
            for (VmdDescriptor vmd : mds.getVmd()) {
                if (vmd.getAlertSystem() != null) {
                    descriptors.get(mds).addAll(adg.getAlertDescriptors(vmd.getAlertSystem()));
                }
            }
        }
        return descriptors;
    }

    private static <T extends AbstractAlertDescriptor> Set<T> collectAllAlertDescriptors(SDCEndpoint ep, AlertDescriptorGrabber<T> adg) {
        Set<T> descriptors = new HashSet<>();
        Map<MdsDescriptor, Set<T>> map = collectAllAlertDescriptorsMds(ep, adg);
        map.values().forEach(entry -> descriptors.addAll(entry));
        return descriptors;
    }

    public static Map<MdsDescriptor, Set<AlertConditionDescriptor>> collectAllAlertConditionDescriptorsMds(SDCEndpoint ep) {
        return collectAllAlertDescriptorsMds(ep, new AlertDescriptorGrabber<AlertConditionDescriptor>() {
            @Override
            List<AlertConditionDescriptor> getAlertDescriptors(AlertSystemDescriptor as) {
                return as.getAlertCondition();
            }
        });
    }

    public static Map<MdsDescriptor, Set<AlertSignalDescriptor>> collectAllAlertSignalDescriptorsMds(SDCEndpoint ep) {
        return collectAllAlertDescriptorsMds(ep, new AlertDescriptorGrabber<AlertSignalDescriptor>() {
            @Override
            List<AlertSignalDescriptor> getAlertDescriptors(AlertSystemDescriptor as) {
                return as.getAlertSignal();
            }
        });
    }

    public static Set<AlertConditionDescriptor> collectAllAlertConditionDescriptors(SDCEndpoint ep) {
        return collectAllAlertDescriptors(ep, new AlertDescriptorGrabber<AlertConditionDescriptor>() {
            @Override
            List<AlertConditionDescriptor> getAlertDescriptors(AlertSystemDescriptor as) {
                return as.getAlertCondition();
            }
        });
    }

    public static Set<AlertSignalDescriptor> collectAllAlertSignalDescriptors(SDCEndpoint ep) {
        return collectAllAlertDescriptors(ep, new AlertDescriptorGrabber<AlertSignalDescriptor>() {
            @Override
            List<AlertSignalDescriptor> getAlertDescriptors(AlertSystemDescriptor as) {
                return as.getAlertSignal();
            }
        });
    }

    public static List<AbstractOperationDescriptor> createOperationDescriptor(List<AbstractOperationState> operationStates, AbstractDescriptor descr, MdsDescriptor mds, SafetyReqType safetyReq) {
        ScoDescriptor sco = mds.getSco();
        if (sco == null) {
            sco = new ScoDescriptor();
            sco.setHandle(mds.getHandle() + "_sco");
            mds.setSco(sco);
        }
        List<AbstractOperationDescriptor> matchingDescriptors = new ArrayList<>();
        List<AbstractOperationDescriptor> opDescr = sco.getOperation();
        for (AbstractOperationDescriptor nextOpDescr : opDescr) {
            if (nextOpDescr.getOperationTarget().equals(descr.getHandle())) {
                matchingDescriptors.add(nextOpDescr);
            }
        }
        if (!matchingDescriptors.isEmpty()) {
            return matchingDescriptors;
        }
        return SDCToolbox.createOperationDescriptor(descr, opDescr, operationStates, safetyReq);
    }

    private static List<AbstractOperationDescriptor> createOperationDescriptor(AbstractDescriptor descr, List<AbstractOperationDescriptor> opDescr, List<AbstractOperationState> opStates, SafetyReqType safetyReq) {
        List<AbstractOperationDescriptor> aod = new ArrayList<>();
        List<AbstractOperationState> aos = new ArrayList<>();
        if (descr instanceof NumericMetricDescriptor) {
            aod.add(new SetValueOperationDescriptor());
            aos.add(new SetValueOperationState());
            aod.add(new SetMetricStateOperationDescriptor());
            aos.add(new SetMetricStateOperationState());
        } else if (descr instanceof StringMetricDescriptor) {
            aod.add(new SetStringOperationDescriptor());
            aos.add(new SetStringOperationState());
        } else if (descr instanceof AbstractContextDescriptor) {
            aod.add(new SetContextStateOperationDescriptor());
            aos.add(new SetContextStateOperationState());
        } else if (descr instanceof AbstractAlertDescriptor) {
            aod.add(new SetAlertStateOperationDescriptor());
            aos.add(new SetAlertStateOperationState());
        } else if (descr instanceof ActivateOperationDescriptor) {
            opDescr.add((ActivateOperationDescriptor) descr);
            aos.add(new ActivateOperationState());
        }
        if (aos.isEmpty()) {
            throw new IllegalStateException("Operation state could not be created for descriptor handle = " + descr.getHandle());
        }
        int handleSuffix = 0;
        for (AbstractOperationState nextAos : aos) {
            nextAos.setDescriptorHandle(descr.getHandle() + "_sco_" + handleSuffix++);
            nextAos.setOperatingMode(OperatingMode.EN);
            opStates.add(nextAos);
        }
        if (descr instanceof ActivateOperationDescriptor) {
            // Descriptor already defined and added by application
            return null;
        }
        if (aod.isEmpty()) {
            throw new IllegalStateException("Operation descriptor could not be created for descriptor handle = " + descr.getHandle());
        }
        handleSuffix = 0;
        for (AbstractOperationDescriptor nextAod : aod) {
            nextAod.setHandle(descr.getHandle() + "_sco_" + handleSuffix++);
            nextAod.setOperationTarget(descr.getHandle());
            if (safetyReq != null) {
                ExtensionType ext = new ExtensionType();
                JAXBElement<SafetyReqType> sre = new ObjectFactory().createSafetyReq(safetyReq);
                ext.getAny().add(sre);
                nextAod.setExtension(ext);
            }
            opDescr.add(nextAod);
        }
        return aod;
    }

}
