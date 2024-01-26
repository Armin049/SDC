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
package org.ornet.sdclib.binding.client;

import io.vertx.core.buffer.Buffer;
import org.ornet.cdm.AbstractAlertReport;
import org.ornet.cdm.AbstractContextReport;
import org.ornet.cdm.AbstractMetricReport;
import org.ornet.cdm.OperationInvokedReport;
import org.ornet.cdm.WaveformStream;
import org.ornet.sdclib.consumer.SDCConsumer;

public class BICEPSConsumerEventSinkBinding implements IConsumerEventSinkBinding {

    protected final SDCConsumer consumer;

    public BICEPSConsumerEventSinkBinding(SDCConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onMetricReportPart(AbstractMetricReport.ReportPart part) {
        consumer.handleMetricReportPart(part);
    }

    @Override
    public void onAlertReportPart(AbstractAlertReport.ReportPart part) {
        consumer.handleAlertReportPart(part);
    }

    @Override
    public void onContextReportPart(AbstractContextReport.ReportPart part) {
        consumer.handleContextReportPart(part);
    }

    @Override
    public void onStreamReceived(WaveformStream wfs) {
        consumer.handleStreamReceived(wfs);
    }

    @Override
    public void onOperationInvokedReceived(OperationInvokedReport oir) {
        consumer.handleOperationInvoked(oir);
    }

    @Override
    public void onRawReport(Buffer buffer) {
        consumer.handleRawReport(buffer);
    }

}
