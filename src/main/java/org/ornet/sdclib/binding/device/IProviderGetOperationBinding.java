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

import org.ornet.cdm.GetContextStates;
import org.ornet.cdm.GetContextStatesResponse;
import org.ornet.cdm.GetMdDescription;
import org.ornet.cdm.GetMdDescriptionResponse;
import org.ornet.cdm.GetMdState;
import org.ornet.cdm.GetMdStateResponse;
import org.ornet.cdm.GetMdib;
import org.ornet.cdm.GetMdibResponse;

public interface IProviderGetOperationBinding {

    GetMdibResponse onGetMdib(GetMdib request);

    GetMdDescriptionResponse onGetMdDescription(GetMdDescription request);

    GetMdStateResponse onGetMdState(GetMdState request);

    GetContextStatesResponse onGetContextStates(GetContextStates request);

}
