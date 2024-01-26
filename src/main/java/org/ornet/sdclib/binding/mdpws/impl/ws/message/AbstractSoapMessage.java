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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPManager;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AbstractSoapMessage {

    protected SOAPMessage content;
    protected final static SOAPManager SOAP_MANAGER = SOAPManager.getInstance();

    public AbstractSoapMessage() {
    }

    public AbstractSoapMessage(SOAPMessage content) {
        this.content = content;
    }

    public AbstractSoapMessage(String resourcePath) {
        synchronized (SOAP_MANAGER) {
            content = SOAP_MANAGER.createSOAP(this.getClass().getResourceAsStream(resourcePath));
        }
    }

    public AbstractSoapMessage(InputStream in) {
        synchronized (SOAP_MANAGER) {
            content = SOAP_MANAGER.createSOAP(in);
        }
    }

    public AbstractSoapMessage(Buffer buffer) {
        this(new ByteArrayInputStream(buffer.getBytes()));
    }

    public AbstractSoapMessage(byte[] bytes) {
        this(new ByteArrayInputStream(bytes));
    }

    public SOAPMessage getContent() {
        return content;
    }

    public void createFault(String reason) {
        synchronized (SOAP_MANAGER) {
            content = SOAP_MANAGER.createSOAP(this.getClass().getResourceAsStream("/soap/SoapFault.xml"));
        }
        setTextNodeContent("faultstring", reason, true);
    }

    protected String getTextNodeContent(String node, boolean body) {
        return getTextNodeContent(node, "*", body);
    }

    protected String getTextNodeContent(String node, String namespace, boolean body) {
        try {
            NodeList list = getNodelist(node, namespace, body, false);
            if (list != null) {
                return list.item(0).getTextContent();
            }
        } catch (Exception ex) {
            Logger.getLogger(ProbeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected final boolean setTextNodeContent(String node, String text, boolean body) {
        return setTextNodeContent(node, text, body, false);
    }

    protected final boolean setTextNodeContent(String node, String text, boolean body, boolean applyToAllNodes) {
        try {
            NodeList list = getNodelist(node, "*", body, false);
            if (list != null) {
                if (!applyToAllNodes)
                    list.item(0).setTextContent(text);
                else
                    for(int i = 0; i < list.getLength(); i++)
                        list.item(i).setTextContent(text);
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ProbeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    protected String getAttributeContent(String node, String attr, boolean body) {
        try {
            NodeList list = getNodelist(node, "*", body, true);
            if (list != null) {
                return list.item(0).getAttributes().getNamedItem(attr).getTextContent();
            }
        } catch (Exception ex) {
            Logger.getLogger(ProbeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected final boolean setAttributeContent(String node, String attr, String text, boolean body) {
        try {
            NodeList list = getNodelist(node, "*", body, true);
            if (list != null) {
                list.item(0).getAttributes().getNamedItem(attr).setTextContent(text);
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ProbeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private NodeList getNodelist(String node, String namespace, boolean body, boolean attr) throws DOMException, SOAPException {
        final SOAPElement soapHeader = body ? content.getSOAPBody() : content.getSOAPHeader();
        final NodeList list = soapHeader.getElementsByTagNameNS(namespace, node);
        if (list.getLength() == 0) {
            return null;
        }
        if (attr && !list.item(0).hasAttributes()) {
            return null;
        }
        return list;
    }

    public SOAPElement getSoapElement(String name, boolean body) {
        return getSoapElement(name, body, true);
    }

    public SOAPElement tryGetSoapElement(String name, boolean body) {
        return getSoapElement(name, body, false);
    }

    private SOAPElement getSoapElement(String name, boolean body, boolean createFault) {
        try {
            SOAPElement parent = body ? content.getSOAPBody() : content.getSOAPHeader();
            NodeList list = parent.getElementsByTagNameNS("*", name);
            if (list.getLength() == 0) {
                if (createFault)
                    createFault("SOAP error - header or body not present.");
                return null;
            }
            return (SOAPElement) list.item(0);
        } catch (SOAPException ex) {
            Logger.getLogger(GetResponseMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void setRandomMessageId() {
        try {
            NodeList list = getNodelist("MessageID", "*", false, false);
            if (list != null && list.getLength() == 1) {
                list.item(0).setTextContent("urn:uuid:" + UUID.randomUUID().toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstractSoapMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void appendToSoapHeader(Node node) {
        try {
            var importedNode = content.getSOAPPart().importNode(node, true);
            content.getSOAPHeader().appendChild(importedNode);
        } catch (SOAPException | DOMException ex) {
            Logger.getLogger(AbstractSoapMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public final String getMessageId() {
        return getTextNodeContent("MessageID", false);
    }

    public final void setAction(String action) {
        setTextNodeContent("Action", action, false);
    }

    public final String getAction() {
        return getTextNodeContent("Action", false);
    }

    public final void setTo(String epr) {
        setTextNodeContent("To", epr, false);
    }

    public final String getTo() {
        return getTextNodeContent("To", false);
    }

    public final void setRelatesTo(String relatesTo) {
        setTextNodeContent("RelatesTo", relatesTo, false);
    }

    public final String getRelatesTo() {
        return getTextNodeContent("RelatesTo", false);
    }

    protected SOAPMessage createBase() throws Exception {
        return null;
    }

    public SOAPMessage create() {
        try {
            if (content == null) {
                try {
                    content = createBase();
                } catch (Exception ex) {
                    Logger.getLogger(AbstractSoapMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (content == null) {
                    createFault("No base content.");
                }
            }
            setRandomMessageId();
            content.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
            content.saveChanges();
        } catch (SOAPException ex) {
            Logger.getLogger(AbstractSoapMessage.class.getName()).log(Level.SEVERE, null, ex);
            createFault(ex.toString());
        }
        return content;
    }

    public byte[] createBytes() {
        SOAPMessage msg = create();
        return SOAP_MANAGER.toBytes(msg);
    }

    public Buffer createBuffer() {
        return Buffer.buffer(createBytes());
    }

}
