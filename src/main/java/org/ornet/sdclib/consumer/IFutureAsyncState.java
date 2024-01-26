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

public interface IFutureAsyncState {

    /**
     * Called when invocation state is received.
     *
     * @param state The invocation state
     */
    void received(InvocationState state);

}
