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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * AbstractDescriptor defines foundational meta information of any object that is included in the descriptive part of the MDIB. Any descriptor object is derived from pm:AbstractDescriptor. The AbstractDescriptor's counterpart is pm:AbstractState.
 * 
 * <p>Java-Klasse für AbstractDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Handle" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Handle" />
 *       &lt;attribute name="DescriptorVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}VersionCounter" />
 *       &lt;attribute name="SafetyClassification" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}SafetyClassification" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractDescriptor", propOrder = {
    "extension",
    "type"
})
@XmlSeeAlso({
    AbstractContextDescriptor.class,
    AbstractDeviceComponentDescriptor.class,
    AbstractMetricDescriptor.class,
    AbstractAlertDescriptor.class,
    AbstractOperationDescriptor.class
})
public class AbstractDescriptor {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "Type")
    protected CodedValue type;
    @XmlAttribute(name = "Handle", required = true)
    protected String handle;
    @XmlAttribute(name = "DescriptorVersion")
    protected Long descriptorVersion;
    @XmlAttribute(name = "SafetyClassification")
    protected SafetyClassification safetyClassification;

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
     * Ruft den Wert der type-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getType() {
        return type;
    }

    /**
     * Legt den Wert der type-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setType(CodedValue value) {
        this.type = value;
    }

    /**
     * Ruft den Wert der handle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Legt den Wert der handle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandle(String value) {
        this.handle = value;
    }

    /**
     * Ruft den Wert der descriptorVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDescriptorVersion() {
        return descriptorVersion;
    }

    /**
     * Legt den Wert der descriptorVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDescriptorVersion(Long value) {
        this.descriptorVersion = value;
    }

    /**
     * Ruft den Wert der safetyClassification-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SafetyClassification }
     *     
     */
    public SafetyClassification getSafetyClassification() {
        return safetyClassification;
    }

    /**
     * Legt den Wert der safetyClassification-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SafetyClassification }
     *     
     */
    public void setSafetyClassification(SafetyClassification value) {
        this.safetyClassification = value;
    }

}
