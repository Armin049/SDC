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
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import org.ornet.cdm.SafetyInfoType;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InvokeMessage extends AbstractInvokeMessage {

    private InvokeMessage() {
        super("/soap/Invoke.xml");
    }

    public InvokeMessage(String action) {
        this();
        setAction(action);
    }

    public InvokeMessage(SOAPMessage content) {
        super(content);
    }

    public InvokeMessage(InputStream in) {
        super(in);
    }

    public InvokeMessage(Buffer buf) {
        super(buf);
    }

    public void setSafetyInfoHeader(SafetyInfoType safetyInfo) {
        try {
            if (safetyInfo == null) {
                return;
            }
            SOAPHeader soapHeader = content.getSOAPHeader();
            soapHeader.addNamespaceDeclaration("mdpws", WSConstants.MDPWS_NAMESPACE);
            Node siNode = JAXBUtil.getInstance().createSafetyInfo(safetyInfo);
            content.getSOAPPart().adoptNode(siNode);
            soapHeader.appendChild(siNode);
        } catch (SOAPException ex) {
            createFault(ex.toString());
            Logger.getLogger(InvokeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SafetyInfoType getSafetyInfoHeader() {
        try {
            SOAPHeader soapHeader = content.getSOAPHeader();
            final NodeList list = soapHeader.getElementsByTagNameNS("*", "SafetyInfo");
            if (list.getLength() == 0) {
                return null;
            }
            return JAXBUtil.getInstance().retrieveSafetyInfo(list.item(0));
        } catch (Exception ex) {
            Logger.getLogger(InvokeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
