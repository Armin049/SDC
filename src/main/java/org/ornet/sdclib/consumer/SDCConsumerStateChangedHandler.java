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

import org.ornet.cdm.AbstractState;

public abstract class SDCConsumerStateChangedHandler<T extends AbstractState> extends SDCConsumerHandler implements ISDCConsumerStateChangedHandler<T> {

    public SDCConsumerStateChangedHandler(String descriptorHandle) {
        super(descriptorHandle);
    }

    @Override
    public abstract void onStateChanged(T state);

}
