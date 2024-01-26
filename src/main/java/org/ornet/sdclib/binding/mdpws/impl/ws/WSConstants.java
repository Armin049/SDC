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
package org.ornet.sdclib.binding.mdpws.impl.ws;

public class WSConstants {

    public static final String DISCOVERY200901 = "http://docs.oasis-open.org/ws-dd/ns/discovery/2009/01";
    public static final String DPWS200901 = "http://docs.oasis-open.org/ws-dd/ns/dpws/2009/01";
    public static final String ADDRESSING = "http://www.w3.org/2005/08/addressing";
    public static final String EVENTING = "http://schemas.xmlsoap.org/ws/2004/08/eventing";

    public static final String MDPWS_NAMESPACE = "http://standards.ieee.org/downloads/11073/11073-20702-2016";

    public static final String BINDING_NAMESPACE = "http://standards.ieee.org/downloads/11073/11073-20701-2018";
    public static final String MESSAGETMODEL_NAMESPACE = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message";

    public static final String PORT_TYPE_DES = "DescriptionEventService";
    public static final String PORT_TYPE_GET = "GetService";
    public static final String PORT_TYPE_SET = "SetService";
    public static final String PORT_TYPE_CTX = "ContextService";
    public static final String PORT_TYPE_EVT = "StateEventService";
    public static final String PORT_TYPE_WAV = "WaveformService";
    /// <summary>   The service identifier desc. </summary>
    public static final String SERVICE_ID_DES = "DescriptionEventService";
    public static final String SERVICE_ID_GET = "GetService";
    public static final String SERVICE_ID_SET = "SetService";
    public static final String SERVICE_ID_BIC = "BicepsService";

    public static final String FILTER_EMR = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_EVT + "/EpisodicMetricReport";
    public static final String FILTER_ECR = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_CTX + "/EpisodicContextReport";
    public static final String FILTER_EAR = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_EVT + "/EpisodicAlertReport";
    public static final String FILTER_PMR = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_EVT + "/PeriodicMetricReport";
    public static final String FILTER_PCR = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_CTX + "/PeriodicContextReport";
    public static final String FILTER_PAR = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_EVT + "/PeriodicAlertReport";
    public static final String FILTER_OIR = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/OperationInvokedReport";

    public static final String SOAP_FAULT_ACTION = "http://www.w3.org/2005/08/addressing/fault";
    public static final String HELLO_ACTION = "http://docs.oasis-open.org/ws-dd/ns/discovery/2009/01/Hello";
    public static final String BYE_ACTION = "http://docs.oasis-open.org/ws-dd/ns/discovery/2009/01/Bye";
    public static final String PROBE_ACTION = "http://docs.oasis-open.org/ws-dd/ns/discovery/2009/01/Probe";
    public static final String RESOLVE_ACTION = "http://docs.oasis-open.org/ws-dd/ns/discovery/2009/01/Resolve";
    public static final String PROBE_MATCHES_ACTION = "http://docs.oasis-open.org/ws-dd/ns/discovery/2009/01/ProbeMatches";
    public static final String RESOLVE_MATCHES_ACTION = "http://docs.oasis-open.org/ws-dd/ns/discovery/2009/01/ResolveMatches";
    public static final String GET_ACTION = "http://schemas.xmlsoap.org/ws/2004/09/transfer/Get";
    public static final String GET_META_ACTION = "http://schemas.xmlsoap.org/ws/2004/09/mex/GetMetadata/Request";
    public static final String GET_RESPONSE_ACTION = "http://schemas.xmlsoap.org/ws/2004/09/transfer/GetResponse";
    public static final String GET_META_RESPONSE_ACTION = "http://schemas.xmlsoap.org/ws/2004/09/mex/GetMetadata/Response";
    public static final String SUBSCRIBE_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/Subscribe";
    public static final String SUBSCRIBE_RESPONSE_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/SubscribeResponse";
    public static final String RENEW_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/Renew";
    public static final String RENEW_RESPONSE_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/RenewResponse";
    public static final String UNSCUBSCRIBE_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/Unsubscribe";
    public static final String UNSCUBSCRIBE_RESPONSE_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/UnsubscribeResponse";
    public static final String GET_STATUS_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/GetStatus";
    public static final String GET_STATUS_RESPONSE_ACTION = "http://schemas.xmlsoap.org/ws/2004/08/eventing/GetStatusResponse";
    public static final String FILTER_WFS = "http://standards.ieee.org/downloads/11073/11073-20701-2018/WaveformService/WaveformStream";

    public static final String GET_MDIB_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_GET + "/GetMdib";
    public static final String GET_MDIB_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_GET + "/GetMdibResponse";
    public static final String GET_MDDESCRIPTION_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_GET + "/GetMdDescription";
    public static final String GET_MDDESCRIPTION_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_GET + "/GetMdDescriptionResponse";
    public static final String GET_MDSTATE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_GET + "/GetMdState";
    public static final String GET_MDSTATE_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_GET + "/GetMdStateResponse";

    public static final String SET_VALUE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetValue";
    public static final String SET_VALUE_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetValueResponse";
    public static final String ACTIVATE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/Activate";
    public static final String ACTIVATE_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/ActivateResponse";
    public static final String SET_STRING_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetString";
    public static final String SET_STRING_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetStringResponse";
    public static final String SET_ALERT_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetAlertState";
    public static final String SET_ALERT_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetAlertStateResponse";
    public static final String SET_METRIC_STATE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetMetricState";
    public static final String SET_METRIC_STATE_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_SET + "/SetMetricStateResponse";

    public static final String SET_CONTEXT_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_CTX + "/SetContextState";
    public static final String SET_CONTEXT_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_CTX + "/SetContextStateResponse";
    public static final String GET_CONTEXT_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_CTX + "/GetContextStates";
    public static final String GET_CONTEXT_RESPONSE_ACTION = "http://standards.ieee.org/downloads/11073/11073-20701-2018/" + PORT_TYPE_CTX + "/GetContextStatesResponse";

    public static final String EXTENSION_POINTXSD_FILE = "ExtensionPoint.xsd";
    public static final String BICEPS_MESSAGE_MODELXSD_FILE = "BICEPS_MessageModel.xsd";
    public static final String BICEPS_PARTICIPANT_MODELXSD_FILE = "BICEPS_ParticipantModel.xsd";
    public static final String MDPWS_XSD_FILE = "MDPWS.xsd";
    public static final String EXTENSION_POINTXSD = "/schema/" + EXTENSION_POINTXSD_FILE;
    public static final String BICEPS_MESSAGE_MODELXSD = "/schema/" + BICEPS_MESSAGE_MODELXSD_FILE;
    public static final String BICEPS_PARTICIPANT_MODELXSD = "/schema/" + BICEPS_PARTICIPANT_MODELXSD_FILE;
    public static final String MDPWS_XSD = "/schema/" + MDPWS_XSD_FILE;
    public static final String WSDL_RESOURCE_BASE_PATH = "/wsdl";

}
