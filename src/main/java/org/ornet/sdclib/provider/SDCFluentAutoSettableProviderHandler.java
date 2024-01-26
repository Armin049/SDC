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

import java.util.concurrent.BlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ornet.cdm.AbstractState;
import org.ornet.cdm.InvocationState;

public class SDCFluentAutoSettableProviderHandler extends SDCProviderMDStateHandler {

    private final AbstractState initialState;
    private final BlockingDeque<SDCFluentStateChangeRequestContext> deque;
    private InvocationState isResult;

    public SDCFluentAutoSettableProviderHandler(String descriptorHandle, AbstractState initialState, BlockingDeque<SDCFluentStateChangeRequestContext> deque) {
        super(descriptorHandle);
        this.initialState = initialState;
        this.deque = deque;
    }

    @Override
    protected AbstractState getInitialState() {
        return initialState;
    }

    @Override
    public InvocationState onStateChangeRequest(AbstractState state, OperationInvocationContext oic) {
        // Enqueue context to be handled by application domain
        deque.add(new SDCFluentStateChangeRequestContext(state, oic, this));

        // Wait for application domain to handle request
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SDCFluentAutoSettableProviderHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Return application domain result
        return isResult;
    }

    protected void endNotify(InvocationState is) {
        this.isResult = is;
        synchronized (this) {
            notifyAll();
        }
    }

}
