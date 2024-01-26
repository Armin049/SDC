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
 * <p>Java-Klasse für OperatingMode.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="OperatingMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Dis"/>
 *     &lt;enumeration value="En"/>
 *     &lt;enumeration value="NA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OperatingMode")
@XmlEnum
public enum OperatingMode {


    /**
     * Dis = Disabled. Object is disabled.
     * 
     */
    @XmlEnumValue("Dis")
    DIS("Dis"),

    /**
     * En = Enabled. Object is enabled
     * 
     */
    @XmlEnumValue("En")
    EN("En"),

    /**
     * NA = Not Available. Object is not available for interaction. This means that it is defined but currently not in a mode so that it can be interacted with.
     * 
     */
    NA("NA");
    private final String value;

    OperatingMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperatingMode fromValue(String v) {
        for (OperatingMode c: OperatingMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
