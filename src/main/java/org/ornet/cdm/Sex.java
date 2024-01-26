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
 * <p>Java-Klasse für Sex.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="Sex">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unspec"/>
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="Unkn"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Sex")
@XmlEnum
public enum Sex {


    /**
     * Unspec = Unspecified. Sex is not designated.
     * 
     */
    @XmlEnumValue("Unspec")
    UNSPEC("Unspec"),

    /**
     * M = Male. Indicates a male patient.
     * 
     */
    M("M"),

    /**
     * F = Female. Indicates a female patient.
     * 
     */
    F("F"),

    /**
     * Unkn = Unknown. Indicates that the sex is unknown for different reasons.
     * 
     */
    @XmlEnumValue("Unkn")
    UNKN("Unkn");
    private final String value;

    Sex(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Sex fromValue(String v) {
        for (Sex c: Sex.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
