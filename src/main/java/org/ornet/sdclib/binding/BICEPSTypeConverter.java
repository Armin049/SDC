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

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMResult;

public class BICEPSTypeConverter {

    public static <T> T toType(String xml, Class<T> target) {
        try {
            var m = JAXBUtil.getInstance().getUnmarshaller(target);
            return target.cast(m.unmarshal(new ByteArrayInputStream(xml.getBytes(Charset.defaultCharset()))));
        } catch (JAXBException ex) {
            Logger.getLogger(BICEPSTypeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static <T> String toString(T source) {
        try {
            var m = JAXBUtil.getInstance().getMarshaller(source.getClass());
            var writer = new StringWriter();
            m.marshal(source, writer);
            return writer.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(BICEPSTypeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static <T> T cloneType(Object source, Class<T> target) {
        try {
            var jaxbContext = JAXBUtil.getInstance().createOrGetCachedContext(target.getName(), target);
            var m = jaxbContext.createMarshaller();
            var res = new DOMResult();
            var jaxb = new JAXBElement<T>(new QName(new String(), target.getName()), target, (T) source);
            m.marshal(jaxb, res);
            var node = res.getNode();
            var u = jaxbContext.createUnmarshaller();
            var element = (JAXBElement) u.unmarshal(node, target);
            return target.cast(element.getValue());
        } catch (JAXBException ex) {
            Logger.getLogger(BICEPSTypeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
