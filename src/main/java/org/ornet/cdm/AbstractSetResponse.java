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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The SET SERVICE provides a set of request and response MESSAGEs. AbstractSetResponse is the building block for any SET SERVICE response MESSAGE.
 * 
 * Per convention any response MESSAGE ends up with "Response", whereas request MESSAGEs possess no specific suffix.
 * 
 * <p>Java-Klasse für AbstractSetResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractSetResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="InvocationInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}InvocationInfo"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MdibVersionGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSetResponse", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", propOrder = {
    "extension",
    "invocationInfo"
})
@XmlSeeAlso({
    SetValueResponse.class,
    ActivateResponse.class,
    SetAlertStateResponse.class,
    SetComponentStateResponse.class,
    SetStringResponse.class,
    SetContextStateResponse.class,
    SetMetricStateResponse.class
})
public class AbstractSetResponse {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "InvocationInfo", required = true)
    protected InvocationInfo invocationInfo;
    @XmlAttribute(name = "MdibVersion")
    protected Long mdibVersion;
    @XmlAttribute(name = "SequenceId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String sequenceId;
    @XmlAttribute(name = "InstanceId")
    @XmlSchemaType(name = "unsignedLong")
    protected Long instanceId;

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
     * Ruft den Wert der invocationInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InvocationInfo }
     *     
     */
    public InvocationInfo getInvocationInfo() {
        return invocationInfo;
    }

    /**
     * Legt den Wert der invocationInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InvocationInfo }
     *     
     */
    public void setInvocationInfo(InvocationInfo value) {
        this.invocationInfo = value;
    }

    /**
     * Ruft den Wert der mdibVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMdibVersion() {
        return mdibVersion;
    }

    /**
     * Legt den Wert der mdibVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMdibVersion(Long value) {
        this.mdibVersion = value;
    }

    /**
     * Ruft den Wert der sequenceId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceId() {
        return sequenceId;
    }

    /**
     * Legt den Wert der sequenceId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceId(String value) {
        this.sequenceId = value;
    }

    /**
     * Ruft den Wert der instanceId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInstanceId() {
        return instanceId;
    }

    /**
     * Legt den Wert der instanceId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInstanceId(Long value) {
        this.instanceId = value;
    }

}
