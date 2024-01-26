//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A range of decimal values which provides a lower and an upper bound as well as a step width.
 * 
 * <p>Java-Klasse für Range complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Range">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Lower" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="Upper" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="StepWidth" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="RelativeAccuracy" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="AbsoluteAccuracy" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Range", propOrder = {
    "extension"
})
public class Range {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlAttribute(name = "Lower")
    protected BigDecimal lower;
    @XmlAttribute(name = "Upper")
    protected BigDecimal upper;
    @XmlAttribute(name = "StepWidth")
    protected BigDecimal stepWidth;
    @XmlAttribute(name = "RelativeAccuracy")
    protected BigDecimal relativeAccuracy;
    @XmlAttribute(name = "AbsoluteAccuracy")
    protected BigDecimal absoluteAccuracy;

    /**
     * Ruft den Wert der extension-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Legt den Wert der extension-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

    /**
     * Ruft den Wert der lower-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLower() {
        return lower;
    }

    /**
     * Legt den Wert der lower-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLower(BigDecimal value) {
        this.lower = value;
    }

    /**
     * Ruft den Wert der upper-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUpper() {
        return upper;
    }

    /**
     * Legt den Wert der upper-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUpper(BigDecimal value) {
        this.upper = value;
    }

    /**
     * Ruft den Wert der stepWidth-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStepWidth() {
        return stepWidth;
    }

    /**
     * Legt den Wert der stepWidth-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStepWidth(BigDecimal value) {
        this.stepWidth = value;
    }

    /**
     * Ruft den Wert der relativeAccuracy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRelativeAccuracy() {
        return relativeAccuracy;
    }

    /**
     * Legt den Wert der relativeAccuracy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRelativeAccuracy(BigDecimal value) {
        this.relativeAccuracy = value;
    }

    /**
     * Ruft den Wert der absoluteAccuracy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAbsoluteAccuracy() {
        return absoluteAccuracy;
    }

    /**
     * Legt den Wert der absoluteAccuracy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAbsoluteAccuracy(BigDecimal value) {
        this.absoluteAccuracy = value;
    }

}
