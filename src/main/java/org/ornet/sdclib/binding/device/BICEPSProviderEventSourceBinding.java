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

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ornet.cdm.*;
import org.ornet.sdclib.SDCToolbox;
import org.ornet.sdclib.provider.OperationInvocationContext;
import org.ornet.sdclib.provider.SDCProvider;

public abstract class BICEPSProviderEventSourceBinding implements IProviderEventSourceBinding {

    private final SDCProvider provider;

    public BICEPSProviderEventSourceBinding(SDCProvider provider) {
        this.provider = provider;
    }

    @Override
    public void handleEpisodicMetricEvent(AbstractMetricState state, Long mdibVersion) {
        fireEpisodicMetricEventReport(createMetricReport(state, mdibVersion, EpisodicMetricReport.class));
    }

    @Override
    public void handlePeriodicMetricEvent(AbstractMetricState state, Long mdibVersion) {
        firePeriodicMetricEventReport(createMetricReport(state, mdibVersion, PeriodicMetricReport.class));
    }

    private <T extends AbstractMetricReport> T createMetricReport(AbstractMetricState state, Long mdibVersion, Class<T> reportType) {
        try {
            AbstractMetricReport.ReportPart mrp = new AbstractMetricReport.ReportPart();
            mrp.getMetricState().add(state);
            var report = reportType.getConstructor(null).newInstance();
            report.getReportPart().add(mrp);
            report.setMdibVersion(mdibVersion);
            report.setSequenceId("0");
            return report;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BICEPSProviderEventSourceBinding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void handleEpisodicAlertEvent(AbstractAlertState state, Long mdibVersion) {
        fireEpisodicAlertEventReport(createAlertReport(state, mdibVersion, EpisodicAlertReport.class));
    }

    @Override
    public void handlePeriodicAlertEvent(AbstractAlertState state, Long mdibVersion) {
        firePeriodicAlertEventReport(createAlertReport(state, mdibVersion, PeriodicAlertReport.class));
    }

    private <T extends AbstractAlertReport> T createAlertReport(AbstractAlertState state, Long mdibVersion, Class<T> reportType) {
        try {
            AbstractAlertReport.ReportPart arp = new AbstractAlertReport.ReportPart();
            arp.getAlertState().add(state);
            var report = reportType.getConstructor(null).newInstance();
            report.getReportPart().add(arp);
            report.setMdibVersion(mdibVersion);
            report.setSequenceId("0");
            return report;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BICEPSProviderEventSourceBinding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void handleOperationInvokedEvent(OperationInvocationContext oic, InvocationState is, Long mdibVersion, String operationErrorMsg) {
        OperationInvokedReport.ReportPart orp = new OperationInvokedReport.ReportPart();
        InvocationInfo invInf = new InvocationInfo();
        orp.setInvocationInfo(invInf);
        orp.setInvocationSource(new InstanceIdentifier());
        invInf.setTransactionId(oic.getTransactionId());
        invInf.setInvocationState(is);
        orp.setOperationHandleRef(oic.getOperationHandle());
        orp.setOperationTarget(SDCToolbox.getOperationTargetForOperationHandle(provider, oic.getOperationHandle()));
        if (operationErrorMsg != null) {
            invInf.setInvocationError(InvocationError.OTH);
            var text = new LocalizedText();
            text.setLang("en-US");
            text.setValue(operationErrorMsg);
            invInf.getInvocationErrorMessage().add(text);
        }
        OperationInvokedReport oir = new OperationInvokedReport();
        oir.getReportPart().add(orp);
        oir.setMdibVersion(mdibVersion);
        oir.setSequenceId("0");
        fireOperationInvokedEventReport(oir);
    }

    @Override
    public void handleEpisodicContextChangedEvent(AbstractContextState state, Long mdibVersion) {
        fireEpisodicContextEventReport(createContextReport(state, mdibVersion, EpisodicContextReport.class));
    }

    @Override
    public void handlePeriodicContextChangedEvent(AbstractContextState state, Long mdibVersion) {
        firePeriodicContextEventReport(createContextReport(state, mdibVersion, PeriodicContextReport.class));
    }

    private <T extends AbstractContextReport> T createContextReport(AbstractContextState state, Long mdibVersion, Class<T> reportType) {
        try {
            AbstractContextReport.ReportPart crp = new AbstractContextReport.ReportPart();
            crp.getContextState().add(state);
            var report = reportType.getConstructor(null).newInstance();
            report.getReportPart().add(crp);
            report.setMdibVersion(mdibVersion);
            report.setSequenceId("0");
            return report;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BICEPSProviderEventSourceBinding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void handleStream(RealTimeSampleArrayMetricState state, boolean sendTcp) {
        WaveformStream wfs = new WaveformStream();
        wfs.getState().add(state);
        wfs.setSequenceId("0");
        sendStream(wfs, sendTcp);
    }

}
