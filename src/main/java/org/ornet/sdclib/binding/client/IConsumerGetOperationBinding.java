package org.ornet.sdclib.binding.client;

import com.bestingit.async.Completer;
import org.ornet.cdm.GetContextStates;
import org.ornet.cdm.GetContextStatesResponse;
import org.ornet.cdm.GetMdDescription;
import org.ornet.cdm.GetMdDescriptionResponse;
import org.ornet.cdm.GetMdState;
import org.ornet.cdm.GetMdStateResponse;
import org.ornet.cdm.GetMdib;
import org.ornet.cdm.GetMdibResponse;

public interface IConsumerGetOperationBinding {

    Completer<GetMdibResponse> getMdib(GetMdib request);

    Completer<GetMdDescriptionResponse> getMdDescription(GetMdDescription request);

    Completer<GetMdStateResponse> getMdState(GetMdState request);

    Completer<GetContextStatesResponse> getContextStates(GetContextStates request);
}
