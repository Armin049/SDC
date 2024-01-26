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
 * <p>Java-Klasse für MetricAvailability.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="MetricAvailability">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Intr"/>
 *     &lt;enumeration value="Cont"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MetricAvailability")
@XmlEnum
public enum MetricAvailability {


    /**
     * Intr = Intermittent. Stopping or ceasing for a time; alternately ceasing and beginning again. 
     * 
     * Example: non-invasive blood pressure measurement.
     * 
     */
    @XmlEnumValue("Intr")
    INTR("Intr"),

    /**
     * Cont = Continuous. Without break, cessation, or interruption; without intervening time.
     * 
     */
    @XmlEnumValue("Cont")
    CONT("Cont");
    private final String value;

    MetricAvailability(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MetricAvailability fromValue(String v) {
        for (MetricAvailability c: MetricAvailability.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
