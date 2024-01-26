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

import com.bestingit.async.Completer;
import org.ornet.cdm.AbstractSet;
import org.ornet.cdm.AbstractSetResponse;
import org.ornet.cdm.Activate;
import org.ornet.cdm.ActivateResponse;
import org.ornet.cdm.SafetyInfoType;

public interface IConsumerSetOperationBinding {

    public Completer<ActivateResponse> activate(Activate activate);

    public Completer<AbstractSetResponse> setState(AbstractSet request, SafetyInfoType safetyInfo);

    public Completer<String> exchangeRaw(String rawData, String serviceId);

}
