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
import org.ornet.cdm.GetContextStates;
import org.ornet.cdm.GetContextStatesResponse;
import org.ornet.cdm.SetContextState;
import org.ornet.cdm.SetContextStateResponse;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.*;

import java.util.List;

public class DescServiceRoute extends ServiceBaseRoute {

    public static final String REALM = WSConstants.SERVICE_ID_DES;
    public static final String[] TYPES = new String[]{WSConstants.PORT_TYPE_DES};
    public static final String WSDL = "desservice.wsdl";

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

        configure(router, msgManager, REALM, TYPES, serviceHosted, wsdlPath, wsdlFile, scb.build());
    }

}
