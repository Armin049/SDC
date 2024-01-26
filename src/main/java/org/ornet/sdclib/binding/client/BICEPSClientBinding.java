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
package org.ornet.sdclib.binding.client;

import org.ornet.sdclib.consumer.SDCConsumer;

public abstract class BICEPSClientBinding implements IClientBinding {

    protected final SDCConsumer consumer;

    public BICEPSClientBinding(SDCConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public abstract boolean init();

    @Override
    public abstract void initEventing();

    @Override
    public abstract void deInitEventing();

    @Override
    public abstract void close();

    @Override
    public abstract boolean isConnected();

    @Override
    public abstract String getEnpointReference();
    
    @Override
    public abstract String getXAdress();

    @Override
    public abstract IConsumerGetOperationBinding getGetOperationBinding();

    @Override
    public abstract IConsumerSetOperationBinding getSetOperationBinding();

    @Override
    public abstract IConsumerEventSinkBinding getEventSinkBinding();

}
