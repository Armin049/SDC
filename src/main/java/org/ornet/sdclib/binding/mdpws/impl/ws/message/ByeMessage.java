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
package org.ornet.sdclib.binding.mdpws.impl.ws.message;

import javax.xml.soap.SOAPMessage;
import org.ornet.sdclib.binding.mdpws.impl.device.MDPWSDevice;

public class ByeMessage extends AbstractSoapMessage {

    private ByeMessage() {
        super("/soap/Bye.xml");
    }

    public ByeMessage(SOAPMessage content) {
        super(content);
    }

    public ByeMessage(String epr, MDPWSDevice.AppSequencer appSeq, int metadataVersion) {
        this();
        init(epr, appSeq, metadataVersion);
    }

    private void init(String epr, MDPWSDevice.AppSequencer appSeq, int metadataVersion) {
        setTextNodeContent("Address", epr, true);
        setTextNodeContent("MetadataVersion", Integer.toString(metadataVersion), true);
        setAttributeContent("AppSequence", "InstanceId", Long.toString(appSeq.getInstanceId()), false);
        setAttributeContent("AppSequence", "MessageNumber", Long.toString(appSeq.getNextMsgNo()), false);
    }

    public String getEPR() {
        return getTextNodeContent("Address", true);
    }

}
