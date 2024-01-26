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

import io.vertx.core.buffer.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.Marshaller;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMResult;
import org.ornet.cdm.ObjectFactory;
import org.ornet.cdm.StreamDescriptionsType;
import org.ornet.cdm.StreamTransmissionType;
import org.ornet.cdm.StreamTypeType;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceStreamingManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetMetadataResponseMessage extends GetResponseMessage {

    private GetMetadataResponseMessage() {
        super("/soap/GetMetadataResponse.xml");
    }

    public GetMetadataResponseMessage(SOAPMessage content) {
        super(content);
    }

    public GetMetadataResponseMessage(Buffer buffer) {
        super(buffer);
    }

    public GetMetadataResponseMessage(GetMetadataMessage msg, String epr) {
        this();
        initResponse(msg, epr);
    }

    public String getStreamAddress() {
        return getTextNodeContent("StreamAddress", true);
    }

    private void initResponse(GetMetadataMessage msg, String epr) {
        setTextNodeContent("Address", epr, true);
        setRelatesTo(msg.getMessageId());
    }

    // TODO: Anschauen
    public void setHostedInformation(String id, String[] types, List<String> serviceHosted) {
        Map<String[], List<String>> temp = new HashMap<>();
        String[] idTypes = new String[types.length + 1];
        idTypes[0] = id;
        System.arraycopy(types, 0, idTypes, 1, types.length);
        temp.put(idTypes, serviceHosted);
        super.setHostedInformation(temp);
    }

    public void setWSDLLocation(String wsdl, String id, List<String> serviceHosted) {
        try {
            NodeList list = content.getSOAPBody().getElementsByTagNameNS("*", "MetadataSection");
            SOAPElement meta = null;
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getAttributes().getNamedItem("Dialect").getTextContent().equals("http://schemas.xmlsoap.org/wsdl/")) {
                    meta = (SOAPElement) list.item(i);
                    break;
                }
            }
            if (meta == null) {
                throw new Exception("Error in SOAP template, WSDL metatdata section missing.");
            }
            for (String nextAddr : serviceHosted) {
                meta.addChildElement("Location", "wsx").addTextNode(nextAddr + "/" + id + wsdl);
            }
        } catch (Exception ex) {
            createFault(ex.toString());
            Logger.getLogger(GetMetadataResponseMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStreamingSection() {
        StreamDescriptionsType description = new StreamDescriptionsType();
        description.setTargetNamespace(DeviceStreamingManager.TARGET_NAMESPACE);

        StreamTypeType type = new StreamTypeType();
        type.setActionUri(DeviceStreamingManager.ACTION_URI);
        type.setId("WaveformStream");
        type.setStreamType(DeviceStreamingManager.STREAM_TYPE);

        StreamTransmissionType transmission = new StreamTransmissionType();
        transmission.setStreamAddress("soap.udp://" + DeviceStreamingManager.MCAST_IP + ":" + DeviceStreamingManager.MCAST_PORT);
        type.setStreamTransmission(transmission);

        description.getStreamType().add(type);
        try {
            DOMResult res = new DOMResult();
            final Marshaller m = JAXBUtil.getInstance().getMarshaller(description.getClass());
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
            m.marshal(new ObjectFactory().createStreamDescriptions(description), res);
            SOAPEnvelope env = content.getSOAPPart().getEnvelope();
            SOAPElement meta = getSoapElement("Metadata", true);
            Document doc = (Document) res.getNode();
            Node descriptionsNode = content.getSOAPPart().importNode(doc.getFirstChild(), true);
            meta.addChildElement("MetadataSection", "wsx")
                    .addAttribute(env.createName("Dialect"), WSConstants.MDPWS_NAMESPACE)
                    .addAttribute(env.createName("Identifier"), DeviceStreamingManager.TARGET_NAMESPACE)
                    .appendChild(descriptionsNode);
        } catch (Exception e) {
            createFault(e.getMessage());
            Logger.getLogger(GetMetadataResponseMessage.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
