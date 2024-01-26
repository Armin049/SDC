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
package org.ornet.sdclib.binding;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.ornet.cdm.ObjectFactory;
import org.ornet.cdm.SafetyInfoType;
import org.ornet.sdclib.common.MaxHashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JAXBUtil {

    private static JAXBUtil instance;
    private Transformer transformer;

    // Note: JAXBContext instances itself are thread-safe.
    private final Map<String, JAXBContext> contexts = Collections.synchronizedMap(new MaxHashMap<>(256));
    private JAXBContext staticContext;
    private ValidationEventHandler validationEventHandler;
    private javax.xml.validation.Schema schema;

    private JAXBUtil() {
    }

    public static JAXBUtil getInstance() {
        if (instance == null) {
            instance = new JAXBUtil();
        }
        return instance;
    }

    public void setValidationEventHandler(ValidationEventHandler validationEventHandler, javax.xml.validation.Schema schema) {
        this.validationEventHandler = validationEventHandler;
        this.schema = schema;
    }

    public ValidationEventHandler getValidationEventHandler() {
        return validationEventHandler;
    }

    public SafetyInfoType retrieveSafetyInfo(Node xmlNode) throws JAXBException {
        try {
            Unmarshaller u = getUnmarshaller(SafetyInfoType.class);
            JAXBElement<SafetyInfoType> el = (JAXBElement<SafetyInfoType>) u.unmarshal(xmlNode);
            return el.getValue();
        } catch (Exception ex) {
            Logger.getLogger(JAXBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Node createSafetyInfo(SafetyInfoType safetyInfo) {
        try {
            DOMResult res = new DOMResult();
            Marshaller m = getMarshaller(safetyInfo.getClass());
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            m.marshal(new ObjectFactory().createSafetyInfo(safetyInfo), res);
            Document doc = (Document) res.getNode();
            Node docNode = doc.getFirstChild().cloneNode(true);
            return docNode;
        } catch (Exception ex) {
            Logger.getLogger(JAXBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    

    public <T> Marshaller getMarshaller(Class<T> type) throws JAXBException {
        String typeStr = type.toString();
        JAXBContext c = getContext(typeStr, type);
        Marshaller m = c.createMarshaller();
        if (validationEventHandler != null && schema != null) {
            m.setEventHandler(validationEventHandler);
            m.setSchema(schema);
        }
        return m;
    }

    public <T> Unmarshaller getUnmarshaller(Class<T> type) throws JAXBException {
        String typeStr = type.toString();
        JAXBContext c = getContext(typeStr, type);
        Unmarshaller m = c.createUnmarshaller();
        if (validationEventHandler != null && schema != null) {
            m.setEventHandler(validationEventHandler);
            m.setSchema(schema);
        }
        return m;
    }

    public boolean marshallTo(Object object, Object target, boolean generateXmlHeader) {
        try {
            Marshaller m = getMarshaller(object.getClass());
            if (!generateXmlHeader) {
                m.setProperty(Marshaller.JAXB_FRAGMENT, true);
            }
            if (target instanceof StringWriter) {
                m.marshal(object, (StringWriter) target);
            } else if (target instanceof Document) {
                m.marshal(object, (Document) target);
            } else {
                throw new Exception("Type not supported.");
            }
        } catch (Exception ex) {
            Logger.getLogger(JAXBUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private <T> JAXBContext getContext(String typeStr, Class<T> type) throws JAXBException {
        if (staticContext != null) {
            return staticContext;
        }
        return createOrGetCachedContext(typeStr, type);
    }

    public <T> JAXBContext createOrGetCachedContext(String typeStr, Class<T> type) throws JAXBException {
        JAXBContext c;
        if (contexts.containsKey(typeStr)) {
            c = contexts.get(typeStr);
        } else {
            c = JAXBContext.newInstance(type);
            contexts.put(typeStr, c);
        }
        return c;
    }

    public void setStaticContext(JAXBContext context) {
        this.staticContext = context;
    }

    public String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            if (transformer == null) {
                transformer = TransformerFactory.newInstance().newTransformer();
            }
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            te.printStackTrace();
            return null;
        }
        return sw.toString();
    }

    private Document cleanNamespace(Document doc) {
        NodeList list = doc.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            removeNameSpace(list.item(i), "");
        }
        return doc;
    }

    private void removeNameSpace(Node node, String nameSpaceURI) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Document ownerDoc = node.getOwnerDocument();
            ownerDoc.renameNode(node, nameSpaceURI, node.getLocalName());
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            removeNameSpace(list.item(i), nameSpaceURI);
        }
    }

    public void noneRootObjectToDOMDoc(Object object, Document doc, boolean cleanNamespace) throws JAXBException {
        String typeStr = object.getClass().toString();
        JAXBContext c = createOrGetCachedContext(typeStr, object.getClass());
        Marshaller m = c.createMarshaller();
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);
        QName qName = new QName(object.getClass().getSimpleName());
        JAXBElement root = new JAXBElement(qName, object.getClass(), object);
        m.marshal(root, doc);
        if (cleanNamespace) {
            cleanNamespace(doc);
        }
    }
    
    public <T> T nodeToObject(Node xmlNode, Class<T> type) throws JAXBException {
        String typeStr = type.getName();
        JAXBContext c = createOrGetCachedContext(typeStr, type);
        Unmarshaller u = c.createUnmarshaller();
        JAXBElement<T> el = (JAXBElement<T>) u.unmarshal(xmlNode);
        return el.getValue();
    }    

}
