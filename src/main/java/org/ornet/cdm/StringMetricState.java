//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * State of a string METRIC.
 * 
 * <p>Java-Klasse für StringMetricState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StringMetricState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractMetricState">
 *       &lt;sequence>
 *         &lt;element name="MetricValue" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}StringMetricValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StringMetricState", propOrder = {
    "metricValue"
})
@XmlSeeAlso({
    EnumStringMetricState.class
})
public class StringMetricState
    extends AbstractMetricState
{

    @XmlElement(name = "MetricValue")
    protected StringMetricValue metricValue;

    /**
     * Ruft den Wert der metricValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StringMetricValue }
     *     
     */
    public StringMetricValue getMetricValue() {
        return metricValue;
    }

    /**
     * Legt den Wert der metricValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StringMetricValue }
     *     
     */
    public void setMetricValue(StringMetricValue value) {
        this.metricValue = value;
    }

}
