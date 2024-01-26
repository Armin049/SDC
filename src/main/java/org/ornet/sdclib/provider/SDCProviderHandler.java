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

import org.ornet.cdm.InvocationState;
import org.ornet.cdm.AbstractState;

public abstract class SDCProviderHandler<T extends AbstractState> {

    protected SDCProvider provider;
    private final String descriptorHandle;

    public SDCProviderHandler(String descriptorHandle) {
        this.descriptorHandle = descriptorHandle;
    }

    public void updateState(T state) {
        provider.updateState(state);
    }

    protected void setProvider(SDCProvider provider) {
        this.provider = provider;
    }

    public void notifyOperationInvoked(OperationInvocationContext oic, InvocationState is) {
        provider.notifyOperationInvoked(oic, is, null);
    }

    public String getDescriptorHandle() {
        return descriptorHandle;
    }

}
