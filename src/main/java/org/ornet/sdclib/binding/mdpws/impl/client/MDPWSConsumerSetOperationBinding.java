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
package org.ornet.sdclib.binding.mdpws.impl.client;

import com.bestingit.async.Completer;
import com.bestingit.async.EventCompleter;
import static com.bestingit.async.Task.event;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPException;
import org.ornet.cdm.AbstractSet;
import org.ornet.cdm.Activate;
import org.ornet.cdm.ActivateResponse;
import org.ornet.cdm.SetAlertState;
import org.ornet.cdm.SetAlertStateResponse;
import org.ornet.cdm.SetContextState;
import org.ornet.cdm.SetContextStateResponse;
import org.ornet.cdm.SetString;
import org.ornet.cdm.SetStringResponse;
import org.ornet.cdm.SetValue;
import org.ornet.cdm.SetValueResponse;
import org.ornet.cdm.SafetyInfoType;
import org.ornet.cdm.SetMetricState;
import org.ornet.cdm.SetMetricStateResponse;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.client.BICEPSConsumerSetBinding;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeResponseMessage;
import org.w3c.dom.Node;

public class MDPWSConsumerSetOperationBinding extends BICEPSConsumerSetBinding {

    private final MDPWSClient client;

    public MDPWSConsumerSetOperationBinding(MDPWSClient client) {
        this.client = client;
    }

    @Override
    public Completer<ActivateResponse> activate(Activate activate) {
        return GenericRemoteInvoker.invokeSDCBlocking(client,
                WSConstants.PORT_TYPE_SET,
                WSConstants.ACTIVATE_ACTION,
                activate,
                ActivateResponse.class);
    }

    @Override
    public Completer<String> exchangeRaw(String rawData, String servicePortType) {
        EventCompleter<String> ec = event(String.class);
        String trace = Arrays.toString(Thread.currentThread().getStackTrace());
        client.postServiceRaw(servicePortType, rawData, res -> {
            if (res.succeeded()) {
                var result = res.result();
                if (result.statusCode() != 200) {
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "HTTP client request failed: {0}", result.statusCode());
                    return;
                }
                var buf = result.body();
                try {
                    Node node = new InvokeResponseMessage(buf).getContent().getSOAPPart().getEnvelope();
                    ec.setFinishedSuccess(JAXBUtil.getInstance().nodeToString(node));
                } catch (SOAPException ex) {
                    Logger.getLogger(MDPWSConsumerSetOperationBinding.class.getName()).log(Level.SEVERE, null, ex);
                    ec.setFinishedFailed(ex);
                }
            } else {
                var exc = res.cause();
                SDCLib.getInstance().getLogger().log(Level.WARNING, "HTTP client request failed: {0}", exc.getMessage());
                SDCLib.getInstance().logException(exc, trace);
                ec.setFinishedFailed((Exception) exc);
            }
        });
        return ec;
    }

    @Override
    public Completer setState(AbstractSet request, SafetyInfoType safetyInfo) {
        if (request instanceof SetValue) {
            return GenericRemoteInvoker.invokeSDCBlocking(client,
                    WSConstants.PORT_TYPE_SET,
                    WSConstants.SET_VALUE_ACTION,
                    request,
                    safetyInfo,
                    SetValueResponse.class);
        } else if (request instanceof SetString) {
            return GenericRemoteInvoker.invokeSDCBlocking(client,
                    WSConstants.PORT_TYPE_SET,
                    WSConstants.SET_STRING_ACTION,
                    request,
                    safetyInfo,
                    SetStringResponse.class);
        }
        if (request instanceof SetMetricState) {
            return GenericRemoteInvoker.invokeSDCBlocking(client,
                    WSConstants.PORT_TYPE_SET,
                    WSConstants.SET_METRIC_STATE_ACTION,
                    request,
                    safetyInfo,
                    SetMetricStateResponse.class);
        } else if (request instanceof SetContextState) {
            return GenericRemoteInvoker.invokeSDCBlocking(client,
                    WSConstants.PORT_TYPE_CTX,
                    WSConstants.SET_CONTEXT_ACTION,
                    request,
                    safetyInfo,
                    SetContextStateResponse.class);
        } else if (request instanceof SetAlertState) {
            return GenericRemoteInvoker.invokeSDCBlocking(client,
                    WSConstants.PORT_TYPE_SET,
                    WSConstants.SET_ALERT_ACTION,
                    request,
                    safetyInfo,
                    SetAlertStateResponse.class);
        }
        return null;
    }

}
