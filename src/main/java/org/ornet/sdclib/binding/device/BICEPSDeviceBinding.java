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

import org.ornet.cdm.AbstractState;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.provider.SDCProvider;

/**
 * Subclasses of this class represent a bridge between the transport-specific
 * and transport-agnostic BICEPS layer. This class provides default
 * implementations for SDC operation bindings.
 *
 * @author besting
 */
public abstract class BICEPSDeviceBinding implements IDeviceBinding {

    private final BICEPSProviderGetOperationBinding bicepsGetBinding;
    private final BICEPSProviderSetOperationBinding bicepsSetBinding;

    public BICEPSDeviceBinding(SDCProvider provider) {
        bicepsGetBinding = new BICEPSProviderGetOperationBinding(provider);
        bicepsSetBinding = new BICEPSProviderSetOperationBinding(provider);
    }

    public abstract void startBinding();

    public abstract void stopBinding();

    @Override
    public void start() {
        getSetOperationBinding().init();
        startBinding();
    }

    @Override
    public void stop() {
        getSetOperationBinding().shutdown();
        stopBinding();
    }

    @Override
    public final String getNamespace() {
        return WSConstants.BINDING_NAMESPACE;
    }

    @Override
    public void onStateChanged(AbstractState state) {
        getSetOperationBinding().onStateChanged(state);
    }

    @Override
    public final IProviderGetOperationBinding getGetOperationBinding() {
        return bicepsGetBinding;
    }

    @Override
    public final IProviderSetOperationBinding getSetOperationBinding() {
        return bicepsSetBinding;
    }

}
