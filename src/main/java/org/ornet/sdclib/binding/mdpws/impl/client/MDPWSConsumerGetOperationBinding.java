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
package org.ornet.sdclib.binding.mdpws.impl.client;

import com.bestingit.async.Completer;
import org.ornet.cdm.GetContextStates;
import org.ornet.cdm.GetContextStatesResponse;
import org.ornet.cdm.GetMdDescription;
import org.ornet.cdm.GetMdDescriptionResponse;
import org.ornet.cdm.GetMdState;
import org.ornet.cdm.GetMdStateResponse;
import org.ornet.cdm.GetMdib;
import org.ornet.cdm.GetMdibResponse;
import org.ornet.sdclib.binding.client.BICEPSConsumerGetOperationBinding;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;

public class MDPWSConsumerGetOperationBinding extends BICEPSConsumerGetOperationBinding {

    private final MDPWSClient client;

    public MDPWSConsumerGetOperationBinding(MDPWSClient client) {
        this.client = client;
    }

    @Override
    public Completer<GetMdibResponse> getMdib(GetMdib request) {
        return GenericRemoteInvoker.invokeSDCBlocking(client,
                WSConstants.PORT_TYPE_GET,
                WSConstants.GET_MDIB_ACTION,
                request,
                GetMdibResponse.class);
    }

    @Override
    public Completer<GetMdDescriptionResponse> getMdDescription(GetMdDescription request) {
        return GenericRemoteInvoker.invokeSDCBlocking(client,
                WSConstants.PORT_TYPE_GET,
                WSConstants.GET_MDDESCRIPTION_ACTION,
                request,
                GetMdDescriptionResponse.class);
    }

    @Override
    public Completer<GetMdStateResponse> getMdState(GetMdState request) {
        return GenericRemoteInvoker.invokeSDCBlocking(client,
                WSConstants.PORT_TYPE_GET,
                WSConstants.GET_MDSTATE_ACTION,
                request,
                GetMdStateResponse.class);
    }

    @Override
    public Completer<GetContextStatesResponse> getContextStates(GetContextStates request) {
        return GenericRemoteInvoker.invokeSDCBlocking(client,
                WSConstants.PORT_TYPE_CTX,
                WSConstants.GET_CONTEXT_ACTION,
                request,
                GetContextStatesResponse.class);
    }

}
