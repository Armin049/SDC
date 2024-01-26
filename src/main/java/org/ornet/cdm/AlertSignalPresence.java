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
 * <p>Java-Klasse für AlertSignalPresence.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertSignalPresence">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="On"/>
 *     &lt;enumeration value="Off"/>
 *     &lt;enumeration value="Latch"/>
 *     &lt;enumeration value="Ack"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlertSignalPresence")
@XmlEnum
public enum AlertSignalPresence {


    /**
     * Indicates that an ALERT SIGNAL is currently generated.
     * 
     */
    @XmlEnumValue("On")
    ON("On"),

    /**
     * Indicates that an ALERT SIGNAL is currently not generated.
     * 
     */
    @XmlEnumValue("Off")
    OFF("Off"),

    /**
     * Latch = Latched. "Latched" indicates that an ALERT SIGNAL is currently generated even if the ALERT CONDITION is no longer present.
     * 
     */
    @XmlEnumValue("Latch")
    LATCH("Latch"),

    /**
     * Ack = Acknowledged. "Acknowledged" indicates that an ALERT SIGNAL is currently not generated due to an acknowledgment even if the ALERT CONDITION is still present. Acknowledged signals are those, where an auditory ALERT SIGNAL that is related to a currently active ALERT CONDITION, is inactive until the ALERT CONDITION is no longer present.
     * 
     */
    @XmlEnumValue("Ack")
    ACK("Ack");
    private final String value;

    AlertSignalPresence(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlertSignalPresence fromValue(String v) {
        for (AlertSignalPresence c: AlertSignalPresence.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
