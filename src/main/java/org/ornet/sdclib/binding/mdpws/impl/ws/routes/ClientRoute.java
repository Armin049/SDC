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
package org.ornet.sdclib.binding.mdpws.impl.ws.routes;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.logging.Level;

import org.ornet.cdm.*;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.client.ClientMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeMessage;

public class ClientRoute {

    public static final String REALM = "Client";

    public static void configure(Router router, ClientMsgManager msgManager) {
        router.route("/" + REALM + "*").handler(BodyHandler.create());
        router.post("/" + REALM).handler(rc -> {
            // Events handled here
            final Buffer body = rc.getBody();
            msgManager.getSubscriptionManager().getEventSinkBinding().onRawReport(body);
            AbstractSoapMessage asm = new AbstractSoapMessage(body);
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming message for client, Action: {0}", asm.getAction());
            int statusCode = 202;
            switch (asm.getAction()) {
                case WSConstants.FILTER_PAR:
                case WSConstants.FILTER_EAR: {
                    InvokeMessage msg = new InvokeMessage(asm.getContent());
                    AbstractAlertReport report = msg.getMessageModelContentCreateFaultOnError(AbstractAlertReport.class);
                    report.getReportPart().forEach((next) -> {
                        msgManager.getSubscriptionManager().getEventSinkBinding().onAlertReportPart(next);
                    });
                    break;
                }
                case WSConstants.FILTER_PCR:
                case WSConstants.FILTER_ECR: {
                    InvokeMessage msg = new InvokeMessage(asm.getContent());
                    AbstractContextReport report = msg.getMessageModelContentCreateFaultOnError(AbstractContextReport.class);
                    report.getReportPart().forEach((next) -> {
                        msgManager.getSubscriptionManager().getEventSinkBinding().onContextReportPart(next);
                    });
                    break;
                }
                case WSConstants.FILTER_PMR:
                case WSConstants.FILTER_EMR: {
                    InvokeMessage msg = new InvokeMessage(asm.getContent());
                    AbstractMetricReport report = msg.getMessageModelContentCreateFaultOnError(AbstractMetricReport.class);
                    report.getReportPart().forEach((next) -> {
                        msgManager.getSubscriptionManager().getEventSinkBinding().onMetricReportPart(next);
                    });
                    break;
                }
                case WSConstants.FILTER_OIR: {
                    InvokeMessage msg = new InvokeMessage(asm.getContent());
                    OperationInvokedReport report = msg.getMessageModelContentCreateFaultOnError(OperationInvokedReport.class);
                    report.getReportPart().forEach((next) -> {
                        msgManager.getSubscriptionManager().getEventSinkBinding().onOperationInvokedReceived(report);
                    });
                    break;
                }
                case WSConstants.FILTER_WFS: {
                    InvokeMessage msg = new InvokeMessage(asm.getContent());
                    WaveformStream stream = msg.getMessageModelContentCreateFaultOnError(WaveformStream.class);
                    msgManager.getSubscriptionManager().getEventSinkBinding().onStreamReceived(stream);
                    break;
                }
                default:
                    statusCode = 404;
                    break;
            }
            rc.response().headers().add("Content-Type", "application/soap+xml");
            rc.response().setStatusCode(statusCode).end();
        });
    }

}
