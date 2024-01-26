//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * State of a distribution sample array METRIC descriptor. It contains a list of sample values. This sample array is used to transport spatial range information.
 * 
 * <p>Java-Klasse für DistributionSampleArrayMetricState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DistributionSampleArrayMetricState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractMetricState">
 *       &lt;sequence>
 *         &lt;element name="MetricValue" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}SampleArrayValue" minOccurs="0"/>
 *         &lt;element name="PhysiologicalRange" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributionSampleArrayMetricState", propOrder = {
    "metricValue",
    "physiologicalRange"
})
public class DistributionSampleArrayMetricState
    extends AbstractMetricState
{

    @XmlElement(name = "MetricValue")
    protected SampleArrayValue metricValue;
    @XmlElement(name = "PhysiologicalRange")
    protected List<Range> physiologicalRange;

    /**
     * Ruft den Wert der metricValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SampleArrayValue }
     *     
     */
    public SampleArrayValue getMetricValue() {
        return metricValue;
    }

    /**
     * Legt den Wert der metricValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SampleArrayValue }
     *     
     */
    public void setMetricValue(SampleArrayValue value) {
        this.metricValue = value;
    }

    /**
     * Gets the value of the physiologicalRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the physiologicalRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhysiologicalRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Range }
     * 
     * 
     */
    public List<Range> getPhysiologicalRange() {
        if (physiologicalRange == null) {
            physiologicalRange = new ArrayList<Range>();
        }
        return this.physiologicalRange;
    }

}
