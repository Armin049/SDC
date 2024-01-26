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
 * <p>Java-Klasse für ContextAssociation.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="ContextAssociation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="No"/>
 *     &lt;enumeration value="Pre"/>
 *     &lt;enumeration value="Assoc"/>
 *     &lt;enumeration value="Dis"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ContextAssociation")
@XmlEnum
public enum ContextAssociation {


    /**
     * No = Not Associated. There is currently no context information associated, such that there cannot be made any assumptions on the encompassing context.
     * 
     */
    @XmlEnumValue("No")
    NO("No"),

    /**
     * Pre = Pre-Associated. Context information is in a pre-association state.
     * 
     */
    @XmlEnumValue("Pre")
    PRE("Pre"),

    /**
     * Assoc = Associated. Context information is associated.
     * 
     */
    @XmlEnumValue("Assoc")
    ASSOC("Assoc"),

    /**
     * Dis = Disassociated. Context information is no longer associated.
     * 
     */
    @XmlEnumValue("Dis")
    DIS("Dis");
    private final String value;

    ContextAssociation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContextAssociation fromValue(String v) {
        for (ContextAssociation c: ContextAssociation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
