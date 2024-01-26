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
 * <p>Java-Klasse für MetricCategory.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="MetricCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unspec"/>
 *     &lt;enumeration value="Msrmt"/>
 *     &lt;enumeration value="Clc"/>
 *     &lt;enumeration value="Set"/>
 *     &lt;enumeration value="Preset"/>
 *     &lt;enumeration value="Rcmm"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MetricCategory")
@XmlEnum
public enum MetricCategory {


    /**
     * Unspec = Unspecified. None of the categories in MetricCategory is valid for the METRIC.
     * 
     */
    @XmlEnumValue("Unspec")
    UNSPEC("Unspec"),

    /**
     * Msrmt = Measurement. The METRIC has been derived by measurement.
     * 
     */
    @XmlEnumValue("Msrmt")
    MSRMT("Msrmt"),

    /**
     * Clc = Calculation. The METRIC has been derived by calculation only.
     * 
     */
    @XmlEnumValue("Clc")
    CLC("Clc"),

    /**
     * Set = Setting. The METRIC has a value that is adjustable by some (local or remote) control means.
     * 
     */
    @XmlEnumValue("Set")
    SET("Set"),

    /**
     * Preset = Presetting. The METRIC has a value that is adjustable by some (local or remote) control means. Once the value is adjusted, it remains a Preset until committed, at which point it becomes a setting.
     * 
     * Related settings MAY be defined by using pm:AbstractMetricDescriptor/pm:Relation.
     * 
     */
    @XmlEnumValue("Preset")
    PRESET("Preset"),

    /**
     * Rcmm = Recommendation. The METRIC is a proposal for a setting or presetting. The related setting or presetting MAY be defined by using pm:AbstractMetricDescriptor/pm:Relation.
     * 
     */
    @XmlEnumValue("Rcmm")
    RCMM("Rcmm");
    private final String value;

    MetricCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MetricCategory fromValue(String v) {
        for (MetricCategory c: MetricCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
