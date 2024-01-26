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


/**
 * Declares a sample array that represents linear value distributions in the form of arrays containing scaled sample values. In contrast to real-time sample arrays, distribution sample arrays provide observed spatial values, not time points.
 * 
 * NOTE—An example for a distribution sample array metric might be a fourier-transformed electroencephalogram to derive frequency distribution.
 * 
 * <p>Java-Klasse für DistributionSampleArrayMetricDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DistributionSampleArrayMetricDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractMetricDescriptor">
 *       &lt;sequence>
 *         &lt;element name="TechnicalRange" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DomainUnit" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *         &lt;element name="DistributionRange" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Resolution" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributionSampleArrayMetricDescriptor", propOrder = {
    "technicalRange",
    "domainUnit",
    "distributionRange"
})
public class DistributionSampleArrayMetricDescriptor
    extends AbstractMetricDescriptor
{

    @XmlElement(name = "TechnicalRange")
    protected List<Range> technicalRange;
    @XmlElement(name = "DomainUnit", required = true)
    protected CodedValue domainUnit;
    @XmlElement(name = "DistributionRange", required = true)
    protected Range distributionRange;
    @XmlAttribute(name = "Resolution", required = true)
    protected BigDecimal resolution;

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
     * Ruft den Wert der domainUnit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getDomainUnit() {
        return domainUnit;
    }

    /**
     * Legt den Wert der domainUnit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setDomainUnit(CodedValue value) {
        this.domainUnit = value;
    }

    /**
     * Ruft den Wert der distributionRange-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Range }
     *     
     */
    public Range getDistributionRange() {
        return distributionRange;
    }

    /**
     * Legt den Wert der distributionRange-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Range }
     *     
     */
    public void setDistributionRange(Range value) {
        this.distributionRange = value;
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

}
