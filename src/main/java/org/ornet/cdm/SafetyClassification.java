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
 * <p>Java-Klasse für SafetyClassification.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="SafetyClassification">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Inf"/>
 *     &lt;enumeration value="MedA"/>
 *     &lt;enumeration value="MedB"/>
 *     &lt;enumeration value="MedC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SafetyClassification")
@XmlEnum
public enum SafetyClassification {


    /**
     * Inf = Informational. The descriptor and the related state information are intended to be used for information purposes only. They are not intended to be used in clinical functions.
     * 
     */
    @XmlEnumValue("Inf")
    INF("Inf"),

    /**
     * MedA = Medical Class A. The descriptor and related state information are intended to be used in clinical functions, specifically for general display in order to support patient and device monitoring. The displayed data is not intended to be used as sole source for diagnostic or therapeutic decisions. Deviations from this intended use are in the sole responsibility of the SERVICE CONSUMER.
     * 
     */
    @XmlEnumValue("MedA")
    MED_A("MedA"),

    /**
     * MedB = Medical Class B. The descriptor and related state information are intended to be used in clinical functions. The manufacturer has specified and considered a specific intended use for the data, which could result in non-serious injury. Deviations from this intended use are in the sole responsibility of the SERVICE CONSUMER.
     * 
     */
    @XmlEnumValue("MedB")
    MED_B("MedB"),

    /**
     * MedC = Medical Class C. The descriptor and related state information are intended to be used in clinical functions. The manufacturer has specified and considered a specific intended use for the data, which could result in serious injury. Deviations from this intended use are in the sole responsibility of the SERVICE CONSUMER.
     * 
     */
    @XmlEnumValue("MedC")
    MED_C("MedC");
    private final String value;

    SafetyClassification(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SafetyClassification fromValue(String v) {
        for (SafetyClassification c: SafetyClassification.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
