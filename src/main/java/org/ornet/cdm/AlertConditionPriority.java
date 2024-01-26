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
 * <p>Java-Klasse für AlertConditionPriority.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertConditionPriority">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Lo"/>
 *     &lt;enumeration value="Me"/>
 *     &lt;enumeration value="Hi"/>
 *     &lt;enumeration value="None"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlertConditionPriority")
@XmlEnum
public enum AlertConditionPriority {


    /**
     * Lo = Low. Awareness of the ALERT CONDITION is required.
     * 
     */
    @XmlEnumValue("Lo")
    LO("Lo"),

    /**
     * Me = Medium. Prompt response to remove the ALERT CONDITION is required.
     * 
     */
    @XmlEnumValue("Me")
    ME("Me"),

    /**
     * Hi = High. Immediate response to remove the ALERT CONDITION is required.
     * 
     */
    @XmlEnumValue("Hi")
    HI("Hi"),

    /**
     * No awareness of the ALERT CONDITION is required.
     * 
     */
    @XmlEnumValue("None")
    NONE("None");
    private final String value;

    AlertConditionPriority(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlertConditionPriority fromValue(String v) {
        for (AlertConditionPriority c: AlertConditionPriority.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
