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

import org.ornet.cdm.AbstractState;
import org.ornet.cdm.InvocationState;

public class SDCFluentStateChangeRequestContext {

    private final AbstractState state;
    private final OperationInvocationContext oic;
    private final SDCFluentAutoSettableProviderHandler handler;

    public SDCFluentStateChangeRequestContext(AbstractState state, OperationInvocationContext oic, SDCFluentAutoSettableProviderHandler handler) {
        this.state = state;
        this.oic = oic;
        this.handler = handler;
    }

    public AbstractState getState() {
        return state;
    }

    public OperationInvocationContext getOperationInvocationContext() {
        return oic;
    }

    public void endNotify(InvocationState is) {
        handler.endNotify(is);
    }

}
