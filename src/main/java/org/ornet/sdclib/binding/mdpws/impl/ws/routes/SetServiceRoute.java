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

import io.vertx.ext.web.Router;
import java.util.List;
import org.ornet.cdm.Activate;
import org.ornet.cdm.ActivateResponse;
import org.ornet.cdm.SetAlertState;
import org.ornet.cdm.SetAlertStateResponse;
import org.ornet.cdm.SetMetricState;
import org.ornet.cdm.SetMetricStateResponse;
import org.ornet.cdm.SetString;
import org.ornet.cdm.SetStringResponse;
import org.ornet.cdm.SetValue;
import org.ornet.cdm.SetValueResponse;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetStatusMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetStatusResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.RenewMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.RenewResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.SubscribeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.SubscribeResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.UnsubscribeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.UnsubscribeResponseMessage;

public class SetServiceRoute extends ServiceBaseRoute {

    public static final String REALM = WSConstants.SERVICE_ID_SET;
    public static final String[] TYPES = new String[]{WSConstants.PORT_TYPE_SET};
    public static final String WSDL = "setservice.wsdl";

    public void configure(Router router, DeviceMsgManager msgManager, List<String> serviceHosted, String wsdlPath, String wsdlFile) {
        ServiceCallBuilder scb = new ServiceCallBuilder();

        scb.addCall(WSConstants.SUBSCRIBE_ACTION, SubscribeMessage.class, (IServiceCall<SubscribeMessage>) (rc, msg) -> {
            var localAddr = rc.request().scheme() + "://" + rc.request().localAddress().toString() + "/" + REALM;
            SubscribeResponseMessage response = msgManager.getSubscrManager().handleSubscribe(msg, localAddr);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.RENEW_ACTION, RenewMessage.class, (IServiceCall<RenewMessage>) (rc, msg) -> {
            RenewResponseMessage response = msgManager.getSubscrManager().handleRenew(msg);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.UNSCUBSCRIBE_ACTION, UnsubscribeMessage.class, (IServiceCall<UnsubscribeMessage>) (rc, msg) -> {
            UnsubscribeResponseMessage response = msgManager.getSubscrManager().handleUnsubscribe(msg);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.GET_STATUS_ACTION, GetStatusMessage.class, (IServiceCall<GetStatusMessage>) (rc, msg) -> {
            GetStatusResponseMessage response = msgManager.getSubscrManager().handleGetStatus(msg);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.SET_VALUE_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            SetValue request = msg.getMessageModelContent(SetValue.class);
            SetValueResponse mmr = msgManager.getSetOperationBinding().onSetState(request, SetValueResponse.class, msg.getSafetyInfoHeader());
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.SET_VALUE_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.SET_STRING_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            SetString request = msg.getMessageModelContent(SetString.class);
            SetStringResponse mmr = msgManager.getSetOperationBinding().onSetState(request, SetStringResponse.class, msg.getSafetyInfoHeader());
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.SET_STRING_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.SET_ALERT_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            SetAlertState request = msg.getMessageModelContent(SetAlertState.class);
            SetAlertStateResponse mmr = msgManager.getSetOperationBinding().onSetState(request, SetAlertStateResponse.class, msg.getSafetyInfoHeader());
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.SET_ALERT_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.ACTIVATE_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            Activate request = msg.getMessageModelContent(Activate.class);
            ActivateResponse mmr = msgManager.getSetOperationBinding().onSetState(request, ActivateResponse.class, msg.getSafetyInfoHeader());
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.ACTIVATE_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, false);
        });

        scb.addCall(WSConstants.SET_METRIC_STATE_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            SetMetricState request = msg.getMessageModelContent(SetMetricState.class);
            SetMetricStateResponse mmr = msgManager.getSetOperationBinding().onSetState(request, SetMetricStateResponse.class, msg.getSafetyInfoHeader());
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.SET_METRIC_STATE_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, false);
        });

        configure(router, msgManager, REALM, TYPES, serviceHosted, wsdlPath, wsdlFile, scb.build());
    }

}
