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
 * <p>Java-Klasse für AlertSignalPrimaryLocation.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertSignalPrimaryLocation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Loc"/>
 *     &lt;enumeration value="Rem"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlertSignalPrimaryLocation")
@XmlEnum
public enum AlertSignalPrimaryLocation {


    /**
     * Loc = Local. The ALERT SIGNAL is perceivable on the machine where the ALERT CONDITION has been detected.
     * 
     */
    @XmlEnumValue("Loc")
    LOC("Loc"),

    /**
     * Rem = Remote. The ALERT SIGNAL is perceivable on a remote machine.
     * 
     */
    @XmlEnumValue("Rem")
    REM("Rem");
    private final String value;

    AlertSignalPrimaryLocation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlertSignalPrimaryLocation fromValue(String v) {
        for (AlertSignalPrimaryLocation c: AlertSignalPrimaryLocation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
