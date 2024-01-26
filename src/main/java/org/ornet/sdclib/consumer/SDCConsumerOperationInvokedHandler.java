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
package org.ornet.sdclib.consumer;

import org.ornet.cdm.InvocationState;
import org.ornet.sdclib.provider.OperationInvocationContext;

public abstract class SDCConsumerOperationInvokedHandler extends SDCConsumerHandler implements ISDCConsumerOperationInvokedHandler {

    public SDCConsumerOperationInvokedHandler(String descriptorHandle) {
        super(descriptorHandle);
    }

    @Override
    public abstract void onOperationInvoked(OperationInvocationContext oic, InvocationState is);

}
