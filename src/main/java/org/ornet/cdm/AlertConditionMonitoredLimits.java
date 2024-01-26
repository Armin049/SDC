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
 * <p>Java-Klasse für AlertConditionMonitoredLimits.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertConditionMonitoredLimits">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="LoOff"/>
 *     &lt;enumeration value="HiOff"/>
 *     &lt;enumeration value="None"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlertConditionMonitoredLimits")
@XmlEnum
public enum AlertConditionMonitoredLimits {


    /**
     * Both alert limits are monitored.
     * 
     */
    @XmlEnumValue("All")
    ALL("All"),

    /**
     * LoOff = Low-Off. Low-limit violation detection is either currently turned off if the state possesses a low-limit value or is not supported at all.
     * 
     */
    @XmlEnumValue("LoOff")
    LO_OFF("LoOff"),

    /**
     * HiOff = Hi-Off. High-limit violation detection is either currently turned off if the state possesses a high-limit value or is not supported at all.
     * 
     */
    @XmlEnumValue("HiOff")
    HI_OFF("HiOff"),

    /**
     * No alert limits are monitored. 
     * 
     * NOTE—This flag is not equal to the activation state "Off" that pm:AlertConditionState/@ActivationState provides, although the result w.r.t. to alert signalization is the same.
     * 
     */
    @XmlEnumValue("None")
    NONE("None");
    private final String value;

    AlertConditionMonitoredLimits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlertConditionMonitoredLimits fromValue(String v) {
        for (AlertConditionMonitoredLimits c: AlertConditionMonitoredLimits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
