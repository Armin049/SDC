//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * LimitAlertConditionDescriptor is a specialization of an ALERT CONDITION that is active if at least one limit for a referenced METRIC has been violated.
 * 
 * <p>Java-Klasse für LimitAlertConditionDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="LimitAlertConditionDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionDescriptor">
 *       &lt;sequence>
 *         &lt;element name="MaxLimits" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AutoLimitSupported" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitAlertConditionDescriptor", propOrder = {
    "maxLimits"
})
public class LimitAlertConditionDescriptor
    extends AlertConditionDescriptor
{

    @XmlElement(name = "MaxLimits", required = true)
    protected Range maxLimits;
    @XmlAttribute(name = "AutoLimitSupported")
    protected Boolean autoLimitSupported;

    /**
     * Ruft den Wert der maxLimits-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Range }
     *     
     */
    public Range getMaxLimits() {
        return maxLimits;
    }

    /**
     * Legt den Wert der maxLimits-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Range }
     *     
     */
    public void setMaxLimits(Range value) {
        this.maxLimits = value;
    }

    /**
     * Ruft den Wert der autoLimitSupported-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoLimitSupported() {
        return autoLimitSupported;
    }

    /**
     * Legt den Wert der autoLimitSupported-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoLimitSupported(Boolean value) {
        this.autoLimitSupported = value;
    }

}
