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
import org.ornet.sdclib.binding.BICEPSTypeConverter;

public abstract class SDCProviderMDStateHandler<T extends AbstractState> extends SDCProviderHandler<T> {

    private boolean notifyOnFinshed = true;

    public SDCProviderMDStateHandler(String descriptorHandle) {
        super(descriptorHandle);
    }

    public InvocationState onStateChangeRequest(T state, OperationInvocationContext oic) {
        return InvocationState.FAIL;
    }

    protected final AbstractState getInitialClonedState() {
        var state = getInitialState();
        var cloned = BICEPSTypeConverter.cloneType(state, state.getClass());
        return cloned;
    }

    public void setNotifyOnFinshed(boolean notifyOnFinshed) {
        this.notifyOnFinshed = notifyOnFinshed;
    }

    public boolean isNotifyOnFinshed() {
        return notifyOnFinshed;
    }

    protected abstract T getInitialState();

}
