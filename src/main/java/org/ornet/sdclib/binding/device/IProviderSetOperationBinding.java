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

import org.ornet.cdm.AbstractSet;
import org.ornet.cdm.AbstractSetResponse;
import org.ornet.cdm.AbstractState;
import org.ornet.cdm.SafetyInfoType;

public interface IProviderSetOperationBinding {

    public <U extends AbstractSet, V extends AbstractSetResponse> V onSetState(U request, final Class<V> res, SafetyInfoType si);

    public void init();

    public void shutdown();

    void onStateChanged(AbstractState state);
}
