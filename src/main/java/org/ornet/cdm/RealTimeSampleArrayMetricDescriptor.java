//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * Declares a sample array that represents a real-time continuous waveform. An example would be an electrocardiogram (ECG) real-time wave.
 * 
 * <p>Java-Klasse für RealTimeSampleArrayMetricDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RealTimeSampleArrayMetricDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractMetricDescriptor">
 *       &lt;sequence>
 *         &lt;element name="TechnicalRange" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Resolution" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="SamplePeriod" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RealTimeSampleArrayMetricDescriptor", propOrder = {
    "technicalRange"
})
public class RealTimeSampleArrayMetricDescriptor
    extends AbstractMetricDescriptor
{

    @XmlElement(name = "TechnicalRange")
    protected List<Range> technicalRange;
    @XmlAttribute(name = "Resolution", required = true)
    protected BigDecimal resolution;
    @XmlAttribute(name = "SamplePeriod", required = true)
    protected Duration samplePeriod;

    /**
     * Gets the value of the technicalRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the technicalRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTechnicalRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Range }
     * 
     * 
     */
    public List<Range> getTechnicalRange() {
        if (technicalRange == null) {
            technicalRange = new ArrayList<Range>();
        }
        return this.technicalRange;
    }

    /**
     * Ruft den Wert der resolution-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getResolution() {
        return resolution;
    }

    /**
     * Legt den Wert der resolution-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setResolution(BigDecimal value) {
        this.resolution = value;
    }

    /**
     * Ruft den Wert der samplePeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getSamplePeriod() {
        return samplePeriod;
    }

    /**
     * Legt den Wert der samplePeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setSamplePeriod(Duration value) {
        this.samplePeriod = value;
    }

}
