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

import org.ornet.cdm.AbstractAlertState;
import org.ornet.cdm.AbstractContextState;
import org.ornet.cdm.AbstractMetricState;
import org.ornet.cdm.EpisodicAlertReport;
import org.ornet.cdm.EpisodicContextReport;
import org.ornet.cdm.EpisodicMetricReport;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.OperationInvokedReport;
import org.ornet.cdm.PeriodicAlertReport;
import org.ornet.cdm.PeriodicContextReport;
import org.ornet.cdm.PeriodicMetricReport;
import org.ornet.cdm.RealTimeSampleArrayMetricState;
import org.ornet.cdm.WaveformStream;
import org.ornet.sdclib.provider.OperationInvocationContext;

public interface IProviderEventSourceBinding {

    // Methods for BICEPS layer
    void handleEpisodicMetricEvent(AbstractMetricState state, Long mdibVersion);

    void handleEpisodicAlertEvent(AbstractAlertState state, Long mdibVersion);

    void handleEpisodicContextChangedEvent(AbstractContextState acs, Long mdibVersion);

    void handlePeriodicMetricEvent(AbstractMetricState state, Long mdibVersion);

    void handlePeriodicAlertEvent(AbstractAlertState state, Long mdibVersion);

    void handlePeriodicContextChangedEvent(AbstractContextState acs, Long mdibVersion);

    void handleOperationInvokedEvent(OperationInvocationContext oic, InvocationState is, Long mdibVersion, String operationErrorMsg);

    void handleStream(RealTimeSampleArrayMetricState state, boolean sendTcp);

    // Interface to transport layer
    void fireEpisodicMetricEventReport(EpisodicMetricReport report);

    void fireEpisodicContextEventReport(EpisodicContextReport report);

    void fireEpisodicAlertEventReport(EpisodicAlertReport report);

    void firePeriodicMetricEventReport(PeriodicMetricReport report);

    void firePeriodicContextEventReport(PeriodicContextReport report);

    void firePeriodicAlertEventReport(PeriodicAlertReport report);

    void fireOperationInvokedEventReport(OperationInvokedReport oir);

    void sendStream(WaveformStream stream, boolean sendTcp);

}
