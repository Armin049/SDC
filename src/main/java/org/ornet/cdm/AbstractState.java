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
 * AbstractState defines foundational meta information of any object that is included in the state part of the MDIB. Any state object is derived from pm:AbstractState. The pm:AbstractState's counterpart is pm:AbstractDescriptor.
 * 
 * <p>Java-Klasse für AbstractState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StateVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}VersionCounter" />
 *       &lt;attribute name="DescriptorHandle" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
 *       &lt;attribute name="DescriptorVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ReferencedVersion" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractState", propOrder = {
    "extension"
})
@XmlSeeAlso({
    AbstractOperationState.class,
    AbstractMetricState.class,
    AbstractMultiState.class,
    AbstractAlertState.class,
    AbstractDeviceComponentState.class
})
public class AbstractState {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlAttribute(name = "StateVersion")
    protected Long stateVersion;
    @XmlAttribute(name = "DescriptorHandle", required = true)
    protected String descriptorHandle;
    @XmlAttribute(name = "DescriptorVersion")
    protected Long descriptorVersion;

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
     * Ruft den Wert der stateVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStateVersion() {
        return stateVersion;
    }

    /**
     * Legt den Wert der stateVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStateVersion(Long value) {
        this.stateVersion = value;
    }

    /**
     * Ruft den Wert der descriptorHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptorHandle() {
        return descriptorHandle;
    }

    /**
     * Legt den Wert der descriptorHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptorHandle(String value) {
        this.descriptorHandle = value;
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

}
