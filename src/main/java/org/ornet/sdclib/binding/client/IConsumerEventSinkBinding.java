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

public interface IConsumerEventSinkBinding {

    void onRawReport(Buffer buffer);

    void onMetricReportPart(AbstractMetricReport.ReportPart part);

    void onAlertReportPart(AbstractAlertReport.ReportPart part);

    void onContextReportPart(AbstractContextReport.ReportPart part);

    void onStreamReceived(WaveformStream wfs);

    void onOperationInvokedReceived(OperationInvokedReport oir);

}
