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

public class HelloMessage extends AbstractSoapMessage {

    private HelloMessage() {
        super("/soap/Hello.xml");
    }

    public HelloMessage(SOAPMessage content) {
        super(content);
    }

    public HelloMessage(String eprAddr, String xAddrs, MDPWSDevice.AppSequencer appSeq, int metadataVersion) {
        this();
        init(eprAddr, xAddrs, appSeq, metadataVersion);
    }

    private void init(String eprAddr, String xAddrs, MDPWSDevice.AppSequencer appSeq, int metadataVersion) {
        setTextNodeContent("Address", eprAddr, true);
        setTextNodeContent("XAddrs", xAddrs, true);
        setTextNodeContent("MetadataVersion", Integer.toString(metadataVersion), true);
        setAttributeContent("AppSequence", "InstanceId", Long.toString(appSeq.getInstanceId()), false);
        setAttributeContent("AppSequence", "MessageNumber", Long.toString(appSeq.getNextMsgNo()), false);
    }

    public String getXaddrs() {
        return getTextNodeContent("XAddrs", true);
    }

    public String getEPR() {
        return getTextNodeContent("Address", true);
    }

    public String getTypes() {
        return getTextNodeContent("Types", true);
    }

    public int getMetadataVersion() {
        return Integer.parseInt(getTextNodeContent("MetadataVersion", true));
    }

    public long getInstanceId() {
        return Long.parseLong(getAttributeContent("AppSequence", "InstanceId", false));
    }

}
