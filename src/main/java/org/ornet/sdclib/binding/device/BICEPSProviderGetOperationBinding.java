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

import java.util.List;
import org.ornet.cdm.GetContextStates;
import org.ornet.cdm.GetContextStatesResponse;
import org.ornet.cdm.GetMdDescription;
import org.ornet.cdm.GetMdDescriptionResponse;
import org.ornet.cdm.GetMdState;
import org.ornet.cdm.GetMdStateResponse;
import org.ornet.cdm.GetMdib;
import org.ornet.cdm.GetMdibResponse;
import org.ornet.cdm.Mdib;
import org.ornet.sdclib.provider.SDCProvider;

/**
 * This class implements default message model GET operations.
 *
 * @author besting
 */
public class BICEPSProviderGetOperationBinding implements IProviderGetOperationBinding {

    private final SDCProvider provider;

    BICEPSProviderGetOperationBinding(SDCProvider provider) {
        this.provider = provider;
    }

    @Override
    public GetMdibResponse onGetMdib(GetMdib request) {
        GetMdibResponse response = new GetMdibResponse();
        response.setSequenceId("0");
        final Mdib mdib = provider.getMDIB();
        response.setMdib(mdib);
        response.setSequenceId(mdib.getSequenceId());
        response.setMdibVersion(mdib.getMdibVersion());
        return response;
    }

    @Override
    public GetMdDescriptionResponse onGetMdDescription(GetMdDescription request) {
        GetMdDescriptionResponse response = new GetMdDescriptionResponse();
        response.setSequenceId("0");
        response.setMdibVersion(provider.getMdibVersion());
        response.setMdDescription(provider.getMDDescription());
        return response;
    }

    @Override
    public GetMdStateResponse onGetMdState(GetMdState request) {
        List<String> handles = request.getHandleRef();
        GetMdStateResponse response = new GetMdStateResponse();
        response.setSequenceId("0");
        response.setMdibVersion(provider.getMdibVersion());
        response.setMdState(handles == null || handles.isEmpty() ? provider.getMDState() : provider.getMDState(handles));
        return response;
    }

    @Override
    public GetContextStatesResponse onGetContextStates(GetContextStates request) {
        List<String> handles = request.getHandleRef();
        GetContextStatesResponse response = new GetContextStatesResponse();
        response.setSequenceId("0");
        response.setMdibVersion(provider.getMdibVersion());
        response.getContextState().addAll(provider.getContextStates(handles));
        return response;
    }

}
