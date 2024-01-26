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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * Abstract state of a METRIC.
 * 
 * <p>Java-Klasse für AbstractMetricState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractMetricState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState">
 *       &lt;sequence>
 *         &lt;element name="BodySite" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PhysicalConnector" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PhysicalConnectorInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ActivationState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ComponentActivation" />
 *       &lt;attribute name="ActiveDeterminationPeriod" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="LifeTimePeriod" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMetricState", propOrder = {
    "bodySite",
    "physicalConnector"
})
@XmlSeeAlso({
    StringMetricState.class,
    DistributionSampleArrayMetricState.class,
    NumericMetricState.class,
    RealTimeSampleArrayMetricState.class
})
public class AbstractMetricState
    extends AbstractState
{

    @XmlElement(name = "BodySite")
    protected List<CodedValue> bodySite;
    @XmlElement(name = "PhysicalConnector")
    protected PhysicalConnectorInfo physicalConnector;
    @XmlAttribute(name = "ActivationState")
    protected ComponentActivation activationState;
    @XmlAttribute(name = "ActiveDeterminationPeriod")
    protected Duration activeDeterminationPeriod;
    @XmlAttribute(name = "LifeTimePeriod")
    protected Duration lifeTimePeriod;

    /**
     * Gets the value of the bodySite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bodySite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBodySite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedValue }
     * 
     * 
     */
    public List<CodedValue> getBodySite() {
        if (bodySite == null) {
            bodySite = new ArrayList<CodedValue>();
        }
        return this.bodySite;
    }

    /**
     * Ruft den Wert der physicalConnector-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PhysicalConnectorInfo }
     *     
     */
    public PhysicalConnectorInfo getPhysicalConnector() {
        return physicalConnector;
    }

    /**
     * Legt den Wert der physicalConnector-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PhysicalConnectorInfo }
     *     
     */
    public void setPhysicalConnector(PhysicalConnectorInfo value) {
        this.physicalConnector = value;
    }

    /**
     * Ruft den Wert der activationState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ComponentActivation }
     *     
     */
    public ComponentActivation getActivationState() {
        return activationState;
    }

    /**
     * Legt den Wert der activationState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponentActivation }
     *     
     */
    public void setActivationState(ComponentActivation value) {
        this.activationState = value;
    }

    /**
     * Ruft den Wert der activeDeterminationPeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getActiveDeterminationPeriod() {
        return activeDeterminationPeriod;
    }

    /**
     * Legt den Wert der activeDeterminationPeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setActiveDeterminationPeriod(Duration value) {
        this.activeDeterminationPeriod = value;
    }

    /**
     * Ruft den Wert der lifeTimePeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getLifeTimePeriod() {
        return lifeTimePeriod;
    }

    /**
     * Legt den Wert der lifeTimePeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setLifeTimePeriod(Duration value) {
        this.lifeTimePeriod = value;
    }

}
