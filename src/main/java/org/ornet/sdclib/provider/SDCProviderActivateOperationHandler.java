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
package org.ornet.sdclib.provider;

import org.ornet.cdm.ActivateOperationState;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.Mdib;

public abstract class SDCProviderActivateOperationHandler extends SDCProviderMDStateHandler<ActivateOperationState> {

    public SDCProviderActivateOperationHandler(String descriptorHandle) {
        super(descriptorHandle);
    }

    public InvocationState onActivateRequest(Mdib mdib, OperationInvocationContext oic) {
        return InvocationState.FAIL;
    }

}
