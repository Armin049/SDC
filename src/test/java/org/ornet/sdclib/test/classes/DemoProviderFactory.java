/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the Eclipse Pulic License version 2.0.
 * http://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.txt
 *
 */
/**
 * @author besting
 * @Copyright (C) SurgiTAIX AG
 */
package org.ornet.sdclib.test.classes;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.ornet.cdm.*;
import org.ornet.sdclib.ISDCTransportLayerConfiguration;
import org.ornet.sdclib.provider.SDCProvider;
import org.ornet.sdclib.provider.SDCProviderActivateOperationHandler;
import org.ornet.sdclib.provider.SDCProviderAlertConditionStateHandler;
import org.ornet.sdclib.provider.SDCProviderMDStateHandler;
import org.ornet.sdclib.provider.OperationInvocationContext;
import org.ornet.sdclib.SDCToolbox;

public class DemoProviderFactory {

    public static class DemoNumericStateHandler extends SDCProviderMDStateHandler<NumericMetricState> {

        public static final String HANDLE_METRIC = "handle_metric";

        public DemoNumericStateHandler() {
            super(HANDLE_METRIC);
        }

        // Helper method
        public static NumericMetricState createState(String handle, double value) {
            NumericMetricState nms = new NumericMetricState();
            nms.setActivationState(ComponentActivation.ON);
            nms.setDescriptorHandle(handle);
            NumericMetricValue nv = new NumericMetricValue();
            nv.setValue(BigDecimal.valueOf(value));
            AbstractMetricValue.MetricQuality amq = new AbstractMetricValue.MetricQuality();
            amq.setValidity(MeasurementValidity.VLD);
            nv.setMetricQuality(amq);
            nms.setMetricValue(nv);
            return nms;
        }

        private NumericMetricState createState(double value) {
            return DemoNumericStateHandler.createState(getDescriptorHandle(), value);
        }

        @Override
        public InvocationState onStateChangeRequest(NumericMetricState state, OperationInvocationContext oic) {
            System.out.println("Received numeric value change request: " + state.getMetricValue().getValue());
            return InvocationState.FIN;  // Request O.K., let SDCLib update internal MDIB
            //return InvocationState.CANCELLED;  // State will not be updated in internal MDIB
        }

        @Override
        protected NumericMetricState getInitialState() {
            return createState(1);
        }

        public final void setValue(double value) {
            NumericMetricState state = createState(value);
            // Update state in internal MDIB and notify consumers (MDIB version will be increased)
            updateState(state);
        }

    }

    static class AlwaysOnComponentStateHandler extends SDCProviderMDStateHandler {

        private final boolean mds;

        public AlwaysOnComponentStateHandler(String descriptorHandle, boolean mds) {
            super(descriptorHandle);
            this.mds = mds;
        }

        @Override
        protected AbstractState getInitialState() {
            AbstractDeviceComponentState state = mds ? new MdsState() : new AbstractDeviceComponentState();
            state.setDescriptorHandle(super.getDescriptorHandle());
            state.setActivationState(ComponentActivation.ON);
            return state;
        }

    }

    // Create an arbitrary handler for any type of context state
    public static class DemoContextStateHandler<T extends AbstractContextState> extends SDCProviderMDStateHandler<AbstractContextState> {

        private final Class<T> clazz;

        public DemoContextStateHandler(Class<T> clazz, String handle) {
            super(handle);
            this.clazz = clazz;
        }

        // Helper method
        public static <T extends AbstractContextState> T createState(String handle, String ext, Class<T> clazz) {
            try {
                T t = clazz.getConstructor(null).newInstance();
                t.setDescriptorHandle(handle);
                t.setHandle(handle + "_state");
                t.setContextAssociation(ContextAssociation.ASSOC);
                if (ext == null) {
                    return t;
                }
                InstanceIdentifier instId = new InstanceIdentifier();
                instId.setRoot("root");
                instId.setExtension(ext);
                t.getIdentification().add(instId);
                return t;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(DemoProviderFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        private T createState(String ext) {
            return createState(getDescriptorHandle(), ext, clazz);
        }

        @Override
        public InvocationState onStateChangeRequest(AbstractContextState state, OperationInvocationContext oic) {
            System.out.println("Received context value change request...");
            return InvocationState.FIN;  // Request O.K., let SDCLib update internal MDIB and notify consumers
            //return InvocationState.FAILED;  // State will not be updated in internal MDIB
        }

        @Override
        protected AbstractContextState getInitialState() {
            return createState("Test");
        }

        public final void setValueInternal(String ext) {
            T state = createState(ext);
            // Update state in internal MDIB and notify consumers (MDIB version will be increased)
            updateState(state);
        }

    }

    static class DemoActivateOperationHandler extends SDCProviderActivateOperationHandler {

        public static final String HANDLE_CMD = "handle_cmd";

        public DemoActivateOperationHandler() {
            super(HANDLE_CMD);
        }

        private ActivateOperationState mdState = null;

        @Override
        public InvocationState onActivateRequest(Mdib mdib, OperationInvocationContext oic) {
            System.out.println("Received activate request...");
            return InvocationState.FIN;
        }

        @Override
        protected ActivateOperationState getInitialState() {
            this.mdState = new ActivateOperationState();
            this.mdState.setDescriptorHandle(DemoActivateOperationHandler.HANDLE_CMD);
            this.mdState.setOperatingMode(OperatingMode.EN);
            this.mdState.setStateVersion(1L);
            this.mdState.setDescriptorVersion(1L);
            return this.mdState;
        }
    }

    public static class DemoStreamStateHandler extends SDCProviderMDStateHandler<RealTimeSampleArrayMetricState> {

        public static final String HANDLE_STREAM = "handle_stream";

        public DemoStreamStateHandler(String descriptorHandle) {
            super(descriptorHandle);
        }

        // Helper method
        public static RealTimeSampleArrayMetricState createState(String handle, double[] values) {
            RealTimeSampleArrayMetricState rtsams = new RealTimeSampleArrayMetricState();
            rtsams.setActivationState(ComponentActivation.ON);
            rtsams.setDescriptorHandle(handle);
            if (values == null) {
                return rtsams;
            }
            AbstractMetricValue.MetricQuality amq = new AbstractMetricValue.MetricQuality();
            amq.setValidity(MeasurementValidity.VLD);
            SampleArrayValue value = new SampleArrayValue();
            value.setMetricQuality(amq);
            List<BigDecimal> list = value.getSamples();
            for (double next : values) {
                list.add(BigDecimal.valueOf(next));
            }
            rtsams.setMetricValue(value);
            return rtsams;
        }

        private RealTimeSampleArrayMetricState createState(double[] values) {
            return DemoStreamStateHandler.createState(getDescriptorHandle(), values);
        }

        @Override
        protected RealTimeSampleArrayMetricState getInitialState() {
            return createState(null);
        }

        public final void setValueInternal(double[] values) {
            // Update state in internal MDIB and notify consumers (MDIB version will be increased)
            updateState(createState(values));
        }

    }

    public static class AlertSystemStateHandler extends SDCProviderMDStateHandler<AlertSystemState> {

        public AlertSystemStateHandler(String descriptorHandle) {
            super(descriptorHandle);
        }

        @Override
        protected AlertSystemState getInitialState() {
            AlertSystemState alertSystemState = new AlertSystemState();
            alertSystemState.setActivationState(AlertActivation.ON);
            alertSystemState.setDescriptorHandle(getDescriptorHandle());
            return alertSystemState;
        }

    }

    public static class DemoLimitAlertConditionStateHandler extends SDCProviderAlertConditionStateHandler<LimitAlertConditionState> {

        public static final String HANDLE_LIMIT_ALERT_CONDITION = "handle_limit_alert_condition";

        public DemoLimitAlertConditionStateHandler(String descriptorHandle) {
            super(descriptorHandle);
        }

        // Helper method
        public static LimitAlertConditionState createState(String handle, double lower, double upper) {
            LimitAlertConditionState alertCondition = new LimitAlertConditionState();
            alertCondition.setDescriptorHandle(handle);
            alertCondition.setActivationState(AlertActivation.ON);
            alertCondition.setPresence(false);
            alertCondition.setMonitoredAlertLimits(AlertConditionMonitoredLimits.ALL);
            Range range = new Range();
            range.setLower(BigDecimal.valueOf(lower));
            range.setUpper(BigDecimal.valueOf(upper));
            alertCondition.setLimits(range);
            return alertCondition;
        }

        private LimitAlertConditionState createState(double lower, double upper) {
            return DemoLimitAlertConditionStateHandler.createState(getDescriptorHandle(), lower, upper);
        }

        @Override
        protected LimitAlertConditionState getInitialState() {
            return createState(0, 2);
        }

        @Override
        public InvocationState onStateChangeRequest(LimitAlertConditionState state, OperationInvocationContext oic) {
            return InvocationState.FIN;
        }

    }

    public static class DemoAlertSignalStateHandler extends SDCProviderMDStateHandler<AlertSignalState> {

        public static final String HANDLE_ALERT_SIGNAL_LATCHING = "handle_alert_signal_latching";

        public DemoAlertSignalStateHandler(String descriptorHandle) {
            super(descriptorHandle);
        }

        // Helper method
        public static AlertSignalState createState(String handle) {
            AlertSignalState alertSignal = new AlertSignalState();
            alertSignal.setDescriptorHandle(handle);
            alertSignal.setActivationState(AlertActivation.ON);
            alertSignal.setPresence(AlertSignalPresence.OFF);
            return alertSignal;
        }

        private AlertSignalState createState() {
            return DemoAlertSignalStateHandler.createState(getDescriptorHandle());
        }

        @Override
        public InvocationState onStateChangeRequest(AlertSignalState state, OperationInvocationContext oic) {
            return InvocationState.FIN;
        }

        @Override
        protected AlertSignalState getInitialState() {
            return createState();
        }

    }

    public static synchronized SDCProvider getDemoProvider(String epr) {
        return getDemoProvider(epr, false, null);
    }

    public static synchronized SDCProvider getDemoProvider(String epr, boolean includeSafetyReq, ISDCTransportLayerConfiguration tlc) {
        SDCProvider provider = new SDCProvider();
        if (tlc != null) {
            provider.setTransportLayerConfiguration(tlc);
        }
        provider.setEndpointReference(epr);
        MdsDescriptor mds = getDemoMds();
        provider.addMDS(mds);
        addHandlers(provider);
        SafetyReqType safetyReq = null;
        if (includeSafetyReq) {
            safetyReq = new SafetyReqType();
            SafetyContextDefType safetyContext = new SafetyContextDefType();
            final SelectorType selector = new SelectorType();
            selector.setId("id_" + DemoNumericStateHandler.HANDLE_METRIC);
            selector.setValue("//Mdib/MdState/State[@DescriptorHandle=\"handle_metric\"]/MetricValue/@Value");
            safetyContext.getSelector().add(selector);
            safetyReq.setSafetyContextDef(safetyContext);
        }
        // Make states writable
        provider.createSetOperationForDescriptor(SDCToolbox.findMetricDescriptor(provider, DemoNumericStateHandler.HANDLE_METRIC), mds, safetyReq);
        provider.createSetOperationForDescriptor(mds.getSystemContext().getLocationContext(), mds);
        provider.createSetOperationForDescriptor(SDCToolbox.collectAllAlertSignalDescriptors(provider).iterator().next(), mds);
        // Make activation operation activatable
        ActivateOperationDescriptor aod = new ActivateOperationDescriptor();
        aod.setHandle(DemoActivateOperationHandler.HANDLE_CMD);
        aod.setOperationTarget("none");
        provider.createSetOperationForDescriptor(aod, mds);
        return provider;
    }

    public static void addHandlers(SDCProvider provider) {
        provider.addHandler(new DemoNumericStateHandler());
        provider.addHandler(new DemoContextStateHandler(LocationContextState.class, HANDLE_CONTEXT));
        provider.addHandler(new DemoActivateOperationHandler());
        provider.addHandler(new DemoStreamStateHandler("handle_stream"));
        // States for MDS, VMD and channel
        provider.addHandler(new AlwaysOnComponentStateHandler("handle_mds", true));
        provider.addHandler(new AlwaysOnComponentStateHandler("handle_vmd", false));
        provider.addHandler(new AlwaysOnComponentStateHandler("handle_chn", false));
        provider.addHandler(new AlertSystemStateHandler("handle_alert_system"));
        provider.addHandler(new DemoLimitAlertConditionStateHandler(DemoLimitAlertConditionStateHandler.HANDLE_LIMIT_ALERT_CONDITION));
        provider.addHandler(new DemoAlertSignalStateHandler(DemoAlertSignalStateHandler.HANDLE_ALERT_SIGNAL_LATCHING));
    }

    public static final String HANDLE_CONTEXT = "handle_context";

    public static MdsDescriptor getDemoMds() {
        MdsDescriptor mds = new MdsDescriptor();
        mds.setHandle("handle_mds");
        VmdDescriptor vmd = new VmdDescriptor();
        vmd.setHandle("handle_vmd");
        CodedValue VMD_ECG = new CodedValue();
        VMD_ECG.setCode("MDC_DEV_ECG_VMD");
        vmd.setType(VMD_ECG);       // Alert condition
        LimitAlertConditionDescriptor limitAlertCondition = new LimitAlertConditionDescriptor();
        limitAlertCondition.getSource().add(DemoNumericStateHandler.HANDLE_METRIC);
        CodedValue limitAlertConditionType = new CodedValue();
        limitAlertConditionType.setCode("MDCX_CODE_ID_ALERT_NUMERIC_CONDITION");
        limitAlertCondition.setType(limitAlertConditionType);
        limitAlertCondition.setKind(AlertConditionKind.TEC);
        limitAlertCondition.setPriority(AlertConditionPriority.ME);
        CauseInfo causeInfo = new CauseInfo();
        final RemedyInfo remedyInfo = new RemedyInfo();
        causeInfo.setRemedyInfo(remedyInfo);
        limitAlertCondition.getCauseInfo().add(causeInfo);
        limitAlertCondition.setHandle(DemoLimitAlertConditionStateHandler.HANDLE_LIMIT_ALERT_CONDITION);
        final Range maxLimitRange = new Range();
        maxLimitRange.setLower(BigDecimal.ZERO);
        maxLimitRange.setUpper(BigDecimal.TEN);
        limitAlertCondition.setMaxLimits(maxLimitRange);
        // Alert signal (latching)
        AlertSignalDescriptor latchingAlertSignal = new AlertSignalDescriptor();
        CodedValue latchingAlertSignalType = new CodedValue();
        latchingAlertSignalType.setCode("MDCX_CODE_ID_ALERT_NUMERIC_SIGNAL");
        latchingAlertSignal.setType(latchingAlertSignalType);
        latchingAlertSignal.setConditionSignaled(DemoLimitAlertConditionStateHandler.HANDLE_LIMIT_ALERT_CONDITION);
        latchingAlertSignal.setManifestation(AlertSignalManifestation.VIS);
        latchingAlertSignal.setLatching(true);
        latchingAlertSignal.setHandle(DemoAlertSignalStateHandler.HANDLE_ALERT_SIGNAL_LATCHING);
        // Alert system
        AlertSystemDescriptor alertSystem = new AlertSystemDescriptor();
        CodedValue alertSystemType = new CodedValue();
        alertSystemType.setCode("MDCX_CODE_ID_ALERT_SYSTEM");
        alertSystem.setType(alertSystemType);
        alertSystem.getAlertCondition().add(limitAlertCondition);
        alertSystem.getAlertSignal().add(latchingAlertSignal);
        alertSystem.setHandle("handle_alert_system");
        // Add alert system to vmd
        vmd.setAlertSystem(alertSystem);
        // VMD to MDS
        mds.getVmd().add(vmd);
        ChannelDescriptor chn = new ChannelDescriptor();
        chn.setHandle("handle_chn");
        vmd.getChannel().add(chn);
        NumericMetricDescriptor nmd = new NumericMetricDescriptor();
        nmd.setMetricCategory(MetricCategory.SET);
        nmd.setMetricAvailability(MetricAvailability.CONT);
        CodedValue unit = new CodedValue();
        unit.setCodingSystem("2720");
        unit.setCode("MDC_DIM_BEAT_PER_MIN");
        nmd.setUnit(unit);
        CodedValue type = new CodedValue();
        type.setCodingSystem("16770");
        type.setCode("MDC_ECG_HEART_RATE");
        nmd.setType(type);
        nmd.setHandle(DemoNumericStateHandler.HANDLE_METRIC);
        nmd.setResolution(BigDecimal.ONE);
        chn.getMetric().add(nmd);
        LocationContextDescriptor lcd = new LocationContextDescriptor();
        lcd.setHandle(HANDLE_CONTEXT);
        SystemContextDescriptor sc = new SystemContextDescriptor();
        sc.setLocationContext(lcd);
        sc.setHandle("handle_sc");
        mds.setSystemContext(sc);
        RealTimeSampleArrayMetricDescriptor rtsamd = new RealTimeSampleArrayMetricDescriptor();
        rtsamd.setHandle(DemoStreamStateHandler.HANDLE_STREAM);
        rtsamd.setUnit(unit);
        rtsamd.setType(type);
        rtsamd.setMetricCategory(MetricCategory.MSRMT);
        rtsamd.setMetricAvailability(MetricAvailability.CONT);
        try {
            rtsamd.setSamplePeriod(DatatypeFactory.newInstance().newDuration(10));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(DemoProviderFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        rtsamd.setResolution(BigDecimal.ONE);
        chn.getMetric().add(rtsamd);
        MdsDescriptor.MetaData md = new MdsDescriptor.MetaData();
        final LocalizedText manufacturer = new LocalizedText();
        manufacturer.setLang("en");
        manufacturer.setValue("OR.NET");
        md.getManufacturer().add(manufacturer);
        final LocalizedText device = new LocalizedText();
        device.setLang("en");
        device.setValue("SDCLib Demo device");
        md.getModelName().add(device);
        MdsDescriptor.MetaData.Udi udi = new MdsDescriptor.MetaData.Udi();
        udi.setDeviceIdentifier("UDI_1234");
        udi.setHumanReadableForm("UDI_1234_HR");
        InstanceIdentifier instId = new InstanceIdentifier();
        instId.setRoot("root");
        instId.setExtension("whatev");
        udi.setIssuer(instId);
        md.getUdi().add(udi);
        mds.setMetaData(md);
        return mds;
    }

}
