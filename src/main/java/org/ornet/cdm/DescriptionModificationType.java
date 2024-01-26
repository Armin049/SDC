//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DescriptionModificationType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="DescriptionModificationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Crt"/>
 *     &lt;enumeration value="Upt"/>
 *     &lt;enumeration value="Del"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DescriptionModificationType", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
@XmlEnum
public enum DescriptionModificationType {


    /**
     * Crt = Created. Indicates that the object transmitted by a modification MESSAGE has been created, i.e. inserted into the MDIB.
     * 
     */
    @XmlEnumValue("Crt")
    CRT("Crt"),

    /**
     * Upt = Updated. Indicates that the object transmitted by a modification MESSAGE has been updated.
     * 
     */
    @XmlEnumValue("Upt")
    UPT("Upt"),

    /**
     * Del = Deleted. Indicates that the object transmitted by a modification MESSAGE has been deleted, i.e. removed from the MDIB.
     * 
     */
    @XmlEnumValue("Del")
    DEL("Del");
    private final String value;

    DescriptionModificationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DescriptionModificationType fromValue(String v) {
        for (DescriptionModificationType c: DescriptionModificationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
