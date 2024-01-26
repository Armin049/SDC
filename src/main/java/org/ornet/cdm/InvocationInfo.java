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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * InvocationInfo conveys information to describe a transaction operation.
 * 
 * <p>Java-Klasse für InvocationInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="InvocationInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="TransactionId" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}TransactionId"/>
 *         &lt;element name="InvocationState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}InvocationState"/>
 *         &lt;element name="InvocationError" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}InvocationError" minOccurs="0"/>
 *         &lt;element name="InvocationErrorMessage" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvocationInfo", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", propOrder = {
    "extension",
    "transactionId",
    "invocationState",
    "invocationError",
    "invocationErrorMessage"
})
public class InvocationInfo {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "TransactionId")
    @XmlSchemaType(name = "unsignedInt")
    protected long transactionId;
    @XmlElement(name = "InvocationState", required = true)
    @XmlSchemaType(name = "string")
    protected InvocationState invocationState;
    @XmlElement(name = "InvocationError")
    @XmlSchemaType(name = "string")
    protected InvocationError invocationError;
    @XmlElement(name = "InvocationErrorMessage")
    protected List<LocalizedText> invocationErrorMessage;

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
     * Ruft den Wert der transactionId-Eigenschaft ab.
     * 
     */
    public long getTransactionId() {
        return transactionId;
    }

    /**
     * Legt den Wert der transactionId-Eigenschaft fest.
     * 
     */
    public void setTransactionId(long value) {
        this.transactionId = value;
    }

    /**
     * Ruft den Wert der invocationState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InvocationState }
     *     
     */
    public InvocationState getInvocationState() {
        return invocationState;
    }

    /**
     * Legt den Wert der invocationState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InvocationState }
     *     
     */
    public void setInvocationState(InvocationState value) {
        this.invocationState = value;
    }

    /**
     * Ruft den Wert der invocationError-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InvocationError }
     *     
     */
    public InvocationError getInvocationError() {
        return invocationError;
    }

    /**
     * Legt den Wert der invocationError-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InvocationError }
     *     
     */
    public void setInvocationError(InvocationError value) {
        this.invocationError = value;
    }

    /**
     * Gets the value of the invocationErrorMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invocationErrorMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvocationErrorMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedText }
     * 
     * 
     */
    public List<LocalizedText> getInvocationErrorMessage() {
        if (invocationErrorMessage == null) {
            invocationErrorMessage = new ArrayList<LocalizedText>();
        }
        return this.invocationErrorMessage;
    }

}
