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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.w3c.dom.NodeList;

public class GetResponseMessage extends AbstractSoapMessage {

    private GetResponseMessage() {
        super("/soap/GetResponse.xml");
    }

    public GetResponseMessage(GetMessage msg, String epr, String manufacturer, String modelName, String friendly, String modelNumber, String serial) {
        this();
        initResponse(msg, epr, manufacturer, modelName, friendly, modelNumber, serial);
    }

    public GetResponseMessage(SOAPMessage content) {
        super(content);
    }

    public GetResponseMessage(Buffer buffer) {
        super(buffer);
    }

    protected GetResponseMessage(String resource) {
        super(resource);
    }

    private void initResponse(GetMessage msg, String epr, String manufacturer, String modelName, String friendly, String modelNumber, String serial) {
        try {
            setTextNodeContent("Address", epr, true);
            setRelatesTo(msg.getMessageId());
            SOAPElement model = getSoapElement("ThisModel", true);
            SOAPElement device = getSoapElement("ThisDevice", true);
            if (manufacturer != null) {
                model.addChildElement("Manufacturer", "dpws")
                        .addAttribute(model.createQName("lang", "xml"), "en-US")
                        .addTextNode(manufacturer);
            }
            if (modelName != null) {
                model.addChildElement("ModelName", "dpws")
                        .addAttribute(model.createQName("lang", "xml"), "en-US")
                        .addTextNode(modelName);
            }
            if (modelNumber != null) {
                model.addChildElement("ModelNumber", "dpws")
                        .addTextNode(modelNumber);
            }
            if (friendly != null) {
                device.addChildElement("FriendlyName", "dpws")
                        .addAttribute(device.createQName("lang", "xml"), "en-US")
                        .addTextNode(friendly);
            }
            if (serial != null) {
                device.addChildElement("SerialNumber", "dpws")
                        .addTextNode(serial);
            }
        } catch (SOAPException ex) {
            createFault(ex.toString());
            Logger.getLogger(GetResponseMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setHostedInformation(Map<String[], List<String>> serviceHosted) {
        try {
            SOAPElement rel = getSoapElement("Relationship", true);
            for (Map.Entry<String[], List<String>> nextService : serviceHosted.entrySet()) {
                final String[] key = nextService.getKey();
                String id = key[0];
                StringBuilder types = new StringBuilder();
                for (int i = 1; i < key.length; i++) {
                    types.append("pm:").append(key[i]).append(" ");
                }
                types.deleteCharAt(types.length() - 1);
                SOAPElement hosted = rel.addChildElement("Hosted", "dpws");
                for (String serviceAddr : nextService.getValue()) {
                    hosted
                            .addChildElement("EndpointReference", "wsa")
                            .addChildElement("Address", "wsa")
                            .addTextNode(serviceAddr + "/" + id);
                }
                hosted.addChildElement("Types", "dpws")
                        .addNamespaceDeclaration("pm", WSConstants.BINDING_NAMESPACE)
                        .addTextNode(types.toString());
                hosted.addChildElement("ServiceId", "dpws").addTextNode(id);
            }
        } catch (SOAPException ex) {
            createFault(ex.toString());
            Logger.getLogger(GetResponseMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Map<String[], List<String>> getServiceHosted() {
        try {
            Map<String[], List<String>> map = new HashMap<>();
            SOAPElement rel = getSoapElement("Relationship", true);
            if(rel != null) {
                Iterator it = rel.getChildElements();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof SOAPElement && ((SOAPElement) next).getNodeName().contains("Hosted")) {
                        List<String> adrList = new ArrayList<>();
                        SOAPElement el = (SOAPElement) next;
                        NodeList nl = el.getElementsByTagNameNS("*", "Address");
                        for (int i = 0; i < nl.getLength(); i++) {
                            adrList.add(nl.item(i).getTextContent());
                        }
                        String[] idType = new String[2];
                        
                        idType[0] = el.getElementsByTagNameNS("*", "ServiceId").item(0).getTextContent();                       
                        idType[1] = el.getElementsByTagNameNS("*", "Types").item(0).getTextContent();
                        
                        removePrefix(idType);
                        
                        map.put(idType, adrList);
                    }
                }
            }
            return map;
        } catch (Exception ex) {
            Logger.getLogger(GetResponseMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void removePrefix(String[] str) {
    	for(int i = 0; i < str.length; i++) {
	        if (str[i].contains(":")) {
	            str[i] = str[i].replaceAll("[^\\s]*:", "");
	        }
    	}
    }

}