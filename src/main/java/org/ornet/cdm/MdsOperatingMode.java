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
 * <p>Java-Klasse für MdsOperatingMode.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="MdsOperatingMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Nml"/>
 *     &lt;enumeration value="Dmo"/>
 *     &lt;enumeration value="Srv"/>
 *     &lt;enumeration value="Mtn"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MdsOperatingMode")
@XmlEnum
public enum MdsOperatingMode {


    /**
     * Nml = Normal. The POC MEDICAL DEVICE operates in a mode that supports the fulfillment of its clinical functions.
     * 
     */
    @XmlEnumValue("Nml")
    NML("Nml"),

    /**
     * Dmo = Demo. The POC MEDICAL DEVICE operates in a mode that is intended for demonstration purposes only. Arbitrary values are generated.
     * 
     */
    @XmlEnumValue("Dmo")
    DMO("Dmo"),

    /**
     * Srv = Service. The POC MEDICAL DEVICE operates in a mode that is intended for services purposes only.
     * 
     */
    @XmlEnumValue("Srv")
    SRV("Srv"),

    /**
     * MTN = Maintenance. The POC MEDICAL DEVICE operates in a mode that is intended for maintenance purposes only.
     * 
     */
    @XmlEnumValue("Mtn")
    MTN("Mtn");
    private final String value;

    MdsOperatingMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MdsOperatingMode fromValue(String v) {
        for (MdsOperatingMode c: MdsOperatingMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
