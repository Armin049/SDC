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
package org.ornet.sdclib.binding.mdpws.impl.device;

import org.ornet.cdm.EpisodicAlertReport;
import org.ornet.cdm.EpisodicContextReport;
import org.ornet.cdm.EpisodicMetricReport;
import org.ornet.cdm.OperationInvokedReport;
import org.ornet.cdm.PeriodicAlertReport;
import org.ornet.cdm.PeriodicContextReport;
import org.ornet.cdm.PeriodicMetricReport;
import org.ornet.cdm.WaveformStream;
import org.ornet.sdclib.binding.device.BICEPSProviderEventSourceBinding;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.provider.SDCProvider;

public class MDPWSProviderEventSourceBinding extends BICEPSProviderEventSourceBinding {

    private final DeviceMsgManager msgManager;

    public MDPWSProviderEventSourceBinding(SDCProvider provider, DeviceMsgManager msgManager) {
        super(provider);
        this.msgManager = msgManager;
    }

    @Override
    public void fireEpisodicMetricEventReport(EpisodicMetricReport report) {
        msgManager.getSubscrManager().sendReport(report, WSConstants.FILTER_EMR);
    }

    @Override
    public void fireEpisodicContextEventReport(EpisodicContextReport report) {
        msgManager.getSubscrManager().sendReport(report, WSConstants.FILTER_ECR);
    }

    @Override
    public void fireEpisodicAlertEventReport(EpisodicAlertReport report) {
        msgManager.getSubscrManager().sendReport(report, WSConstants.FILTER_EAR);
    }

    @Override
    public void fireOperationInvokedEventReport(OperationInvokedReport report) {
        msgManager.getSubscrManager().sendReport(report, WSConstants.FILTER_OIR);
    }

    @Override
    public void sendStream(WaveformStream stream, boolean sendTcp) {
        if (sendTcp) {
            msgManager.getSubscrManager().sendStream(stream, WSConstants.FILTER_WFS);
            return;
        }
        msgManager.getStreamingManager().sendStream(stream);
    }

    @Override
    public void firePeriodicMetricEventReport(PeriodicMetricReport report) {
        msgManager.getSubscrManager().sendReport(report, WSConstants.FILTER_PMR);
    }

    @Override
    public void firePeriodicContextEventReport(PeriodicContextReport report) {
        msgManager.getSubscrManager().sendReport(report, WSConstants.FILTER_PCR);
    }

    @Override
    public void firePeriodicAlertEventReport(PeriodicAlertReport report) {
        msgManager.getSubscrManager().sendReport(report, WSConstants.FILTER_PAR);
    }

}
