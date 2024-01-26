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
import org.ornet.cdm.GetMdDescription;
import org.ornet.cdm.GetMdDescriptionResponse;
import org.ornet.cdm.GetMdState;
import org.ornet.cdm.GetMdStateResponse;
import org.ornet.cdm.GetMdib;
import org.ornet.cdm.GetMdibResponse;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeResponseMessage;

public class GetServiceRoute extends ServiceBaseRoute {

    public static final String REALM = WSConstants.SERVICE_ID_GET;
    public static final String[] TYPES = new String[]{WSConstants.PORT_TYPE_GET};
    public static final String WSDL = "getservice.wsdl";

    public void configure(Router router, DeviceMsgManager msgManager, List<String> serviceHosted, String wsdlPath, String wsdlFile) {
        ServiceCallBuilder scb = new ServiceCallBuilder();

        scb.addCall(WSConstants.GET_MDIB_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            GetMdib request = msg.getMessageModelContent(GetMdib.class);
            GetMdibResponse mmr = msgManager.getGetOperationBinding().onGetMdib(request);
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.GET_MDIB_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, true);
        });

        scb.addCall(WSConstants.GET_MDSTATE_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            GetMdState request = msg.getMessageModelContent(GetMdState.class);
            GetMdStateResponse mmr = msgManager.getGetOperationBinding().onGetMdState(request);
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.GET_MDSTATE_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, true);
        });

        scb.addCall(WSConstants.GET_MDDESCRIPTION_ACTION, InvokeMessage.class, (IServiceCall<InvokeMessage>) (rc, msg) -> {
            GetMdDescription request = msg.getMessageModelContent(GetMdDescription.class);
            GetMdDescriptionResponse mmr = msgManager.getGetOperationBinding().onGetMdDescription(request);
            InvokeResponseMessage response = new InvokeResponseMessage(msg, WSConstants.GET_MDDESCRIPTION_RESPONSE_ACTION);
            response.setMessageModelContent(mmr);
            completeResponse(rc, response, true);
        });

        configure(router, msgManager, REALM, TYPES, serviceHosted, wsdlPath, wsdlFile, scb.build());
    }

}
