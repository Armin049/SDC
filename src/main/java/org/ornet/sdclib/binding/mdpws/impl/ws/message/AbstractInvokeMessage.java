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
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.ornet.sdclib.binding.JAXBUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public abstract class AbstractInvokeMessage extends AbstractSoapMessage {

    public AbstractInvokeMessage(String resourcePath) {
        super(resourcePath);
    }

    public AbstractInvokeMessage(SOAPMessage content) {
        super(content);
    }

    public AbstractInvokeMessage(InputStream in) {
        super(in);
    }

    public AbstractInvokeMessage(Buffer buf) {
        super(buf);
    }

    public <T> T getMessageModelContent(Class<T> clazz) throws SOAPException, JAXBException {
        SOAPBody soapBody = content.getSOAPBody();
        Node firstChild = soapBody.getFirstChild();
        // Check needed in case there is text between tags
        firstChild = firstChild instanceof org.w3c.dom.Element ? firstChild : firstChild.getNextSibling();
        return clazz.cast(JAXBUtil.getInstance().getUnmarshaller(clazz).unmarshal(firstChild));
    }

    public <T> T getMessageModelContentCreateFaultOnError(Class<T> clazz) {
        try {
            SOAPBody soapBody = content.getSOAPBody();
            Node firstChild = soapBody.getFirstChild();
            // Check needed in case there is text between tags
            firstChild = firstChild instanceof org.w3c.dom.Element ? firstChild : firstChild.getNextSibling();
            return clazz.cast(JAXBUtil.getInstance().getUnmarshaller(clazz).unmarshal(firstChild));
        } catch (Exception ex) {
            createFault(ex.toString());
            Logger.getLogger(InvokeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setMessageModelContent(Object object) {
        try {
            SOAPBody soapBody = content.getSOAPBody();
            Document streamDoc;
            synchronized (SOAP_MANAGER) {
                streamDoc = SOAP_MANAGER.createDoc();
            }
            JAXBUtil.getInstance().marshallTo(object, streamDoc, true);
            soapBody.addDocument(streamDoc);
        } catch (SOAPException ex) {
            Logger.getLogger(InvokeMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
