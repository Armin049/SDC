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
import javax.xml.datatype.Duration;


/**
 * Retrievability provides information on how it is possible to access a state.
 * 
 * <p>Java-Klasse für RetrievabilityInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RetrievabilityInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Method" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}RetrievabilityMethod" />
 *       &lt;attribute name="UpdatePeriod" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrievabilityInfo", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", propOrder = {
    "extension"
})
public class RetrievabilityInfo {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlAttribute(name = "Method", required = true)
    protected RetrievabilityMethod method;
    @XmlAttribute(name = "UpdatePeriod")
    protected Duration updatePeriod;

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
     * Ruft den Wert der method-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RetrievabilityMethod }
     *     
     */
    public RetrievabilityMethod getMethod() {
        return method;
    }

    /**
     * Legt den Wert der method-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrievabilityMethod }
     *     
     */
    public void setMethod(RetrievabilityMethod value) {
        this.method = value;
    }

    /**
     * Ruft den Wert der updatePeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * Legt den Wert der updatePeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setUpdatePeriod(Duration value) {
        this.updatePeriod = value;
    }

}
