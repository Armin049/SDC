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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMessage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SOAPManager {

    private MessageFactory messageFactory;
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    
    private static SOAPManager instance;

    private SOAPManager() {
        try {
            messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setNamespaceAware(true);
            docBuilder = docFactory.newDocumentBuilder();
        } catch (Exception e) {
            Logger.getLogger(SOAPManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static SOAPManager getInstance() {
    	if (SOAPManager.instance == null) {
    		SOAPManager.instance = new SOAPManager();
    	}
    	
    	return SOAPManager.instance;
    }

    public Document createDoc() {
        return docBuilder.newDocument();
    }

    public Document parse(InputStream stream) throws SAXException, IOException {
        return docBuilder.parse(stream);
    }

    public String getAction(SOAPMessage message) {
        try {
            final SOAPHeader soapHeader = message.getSOAPHeader();
            return soapHeader.getElementsByTagNameNS("*", "Action").item(0).getTextContent();
        } catch (Exception ex) {
            Logger.getLogger(ProbeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SOAPMessage createSOAP() {
        try {
            return messageFactory.createMessage();
        } catch (SOAPException ex) {
            Logger.getLogger(SOAPManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SOAPMessage createSOAP(InputStream is) {
        SOAPMessage soapMessage = null;
        try {
            soapMessage = messageFactory.createMessage(null, is);
        } catch (IOException | SOAPException ex) {
            Logger.getLogger(SOAPManager.class.getName()).log(Level.SEVERE, "Error: createSOAP", ex);
        }
        return soapMessage;
    }

    public byte[] toBytes(SOAPMessage msg) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            msg.writeTo(out);
            return clearCrLf(out.toByteArray());
        } catch (SOAPException | IOException ex) {
            Logger.getLogger(SOAPManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte [] clearCrLf(byte [] input) {
        StringBuffer result = new StringBuffer();
        try {
            try (var reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input)))) {
                String line;
                while ((line = reader.readLine()) != null)
                    result.append(line.trim());
                return result.toString().getBytes(StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
