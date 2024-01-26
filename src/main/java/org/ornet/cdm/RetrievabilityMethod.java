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
 * <p>Java-Klasse für RetrievabilityMethod.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="RetrievabilityMethod">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Get"/>
 *     &lt;enumeration value="Per"/>
 *     &lt;enumeration value="Ep"/>
 *     &lt;enumeration value="Strm"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RetrievabilityMethod", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
@XmlEnum
public enum RetrievabilityMethod {


    /**
     * The CONTAINMENT TREE ENTRY is retrievable via a get request. Use the corresponding get MESSAGE.
     * 
     */
    @XmlEnumValue("Get")
    GET("Get"),

    /**
     * Per = Periodic. The CONTAINMENT TREE ENTRY is retrievable via a periodic event report. Use the corresponding periodic event report MESSAGE (e.g., pm:PeriodicMetricReport).
     * 
     */
    @XmlEnumValue("Per")
    PER("Per"),

    /**
     * Ep = Episodic. The CONTAINMENT TREE ENTRY is retrievable via an episodic event report. Use the corresponding episodic event report MESSAGE (e.g., pm:EpisodicMetricReport).
     * 
     */
    @XmlEnumValue("Ep")
    EP("Ep"),

    /**
     * Strm = Stream. The CONTAINMENT TREE ENTRY is retrievable via a waveform stream. Use the msg:WaveformStream or msg:ObservedValueStream MESSAGE.
     * 
     */
    @XmlEnumValue("Strm")
    STRM("Strm");
    private final String value;

    RetrievabilityMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RetrievabilityMethod fromValue(String v) {
        for (RetrievabilityMethod c: RetrievabilityMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
