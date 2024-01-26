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
 * <p>Java-Klasse für AlertSignalManifestation.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertSignalManifestation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Aud"/>
 *     &lt;enumeration value="Vis"/>
 *     &lt;enumeration value="Tan"/>
 *     &lt;enumeration value="Oth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlertSignalManifestation")
@XmlEnum
public enum AlertSignalManifestation {


    /**
     * Aud = Audible. The ALERT SIGNAL manifests in an audible manner, i.e., the alert can be heard. Example: an alarm sound.
     * 
     */
    @XmlEnumValue("Aud")
    AUD("Aud"),

    /**
     * Vis = Visible. The ALERT SIGNAL manifests in a visible manner, i.e., the alert can be seen. Example: a red flashing light.
     * 
     */
    @XmlEnumValue("Vis")
    VIS("Vis"),

    /**
     * Tan = Tangible. The ALERT SIGNAL manifests in a tangible manner, i.e., the alert can be felt. Example: vibration.
     * 
     */
    @XmlEnumValue("Tan")
    TAN("Tan"),

    /**
     * Oth = Other. The ALERT SIGNAL manifests in a manner not further specified.
     * 
     */
    @XmlEnumValue("Oth")
    OTH("Oth");
    private final String value;

    AlertSignalManifestation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlertSignalManifestation fromValue(String v) {
        for (AlertSignalManifestation c: AlertSignalManifestation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
