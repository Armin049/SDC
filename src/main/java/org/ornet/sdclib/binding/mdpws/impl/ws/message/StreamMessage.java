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

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.ornet.cdm.WaveformStream;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceStreamingManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.w3c.dom.Document;

public class StreamMessage extends AbstractSoapMessage {

    private String mcastAddr, mcastPort;
    private WaveformStream stream;
    private int instanceId;
    private long msgNo;

    public StreamMessage(SOAPMessage content) {
        super(content);
    }

    public StreamMessage(WaveformStream stream, String mcastAddr, String mcastPort, int instanceId, long msgNo) {
        this.stream = stream;
        this.mcastAddr = mcastAddr;
        this.mcastPort = mcastPort;
        this.instanceId = instanceId;
        this.msgNo = msgNo;
    }

    @Override
    protected SOAPMessage createBase() throws Exception {
        SOAPMessage soapMsg;
        synchronized (SOAP_MANAGER) {
            soapMsg = SOAP_MANAGER.createSOAP();
        }
        SOAPPart soapPart = soapMsg.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("wsa", WSConstants.ADDRESSING);
        envelope.addNamespaceDeclaration("dpws", WSConstants.DPWS200901);
        envelope.addNamespaceDeclaration("wsd", WSConstants.DISCOVERY200901);
        SOAPHeader header = envelope.getHeader();
        header.addChildElement("Action", "wsa").addTextNode(DeviceStreamingManager.ACTION_URI);
        header.addChildElement("MessageID", "wsa").addTextNode("");
        header.addChildElement("To", "wsa").addTextNode(DeviceStreamingManager.PROTOCOL_PREFIX + mcastAddr + ":" + mcastPort);
        //header.addChildElement("From", "wsa").addChildElement("Address", "wsa", "").addTextNode(provider.getEndpointReference());
        SOAPElement appElem = header.addChildElement("AppSequence", "wsd").addAttribute(envelope.createName("InstanceId"), Integer.toString(instanceId));
        appElem.addAttribute(envelope.createName("MessageNumber"), Long.toString(msgNo));
        SOAPBody body = envelope.getBody();
        Document streamDoc;
        synchronized (SOAP_MANAGER) {
            streamDoc = SOAP_MANAGER.createDoc();
        }
        JAXBUtil.getInstance().marshallTo(stream, streamDoc, true);
        body.addDocument(streamDoc);
        return soapMsg;
    }

}
