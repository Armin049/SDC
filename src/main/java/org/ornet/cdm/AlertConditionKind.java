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
 * <p>Java-Klasse für AlertConditionKind.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertConditionKind">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Phy"/>
 *     &lt;enumeration value="Tec"/>
 *     &lt;enumeration value="Oth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlertConditionKind")
@XmlEnum
public enum AlertConditionKind {


    /**
     * Phy = Physiological. The condition arises from a patient-related variable. Examples: "blood pressure high" or "minute volume low".
     * 
     */
    @XmlEnumValue("Phy")
    PHY("Phy"),

    /**
     * Tec = Technical. The condition arises from a monitored equipment-related or ALERT SYSTEM-related variable. Examples: "battery low" or "sensor unplugged".
     * 
     */
    @XmlEnumValue("Tec")
    TEC("Tec"),

    /**
     * Oth = Other. The condition arises from another origin, e.g., equipment-user advisory conditions like "room temperature high".
     * 
     */
    @XmlEnumValue("Oth")
    OTH("Oth");
    private final String value;

    AlertConditionKind(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlertConditionKind fromValue(String v) {
        for (AlertConditionKind c: AlertConditionKind.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
