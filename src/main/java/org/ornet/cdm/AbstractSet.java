//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The SET SERVICE provides a set of request and response MESSAGEs. AbstractSet is the building block for any SET SERVICE request MESSAGE.
 * 
 * <p>Java-Klasse für AbstractSet complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="OperationHandleRef" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSet", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", propOrder = {
    "extension",
    "operationHandleRef"
})
@XmlSeeAlso({
    SetValue.class,
    SetString.class,
    SetMetricState.class,
    SetContextState.class,
    SetAlertState.class,
    Activate.class,
    SetComponentState.class
})
public class AbstractSet {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "OperationHandleRef", required = true)
    protected String operationHandleRef;

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
     * Ruft den Wert der operationHandleRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationHandleRef() {
        return operationHandleRef;
    }

    /**
     * Legt den Wert der operationHandleRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationHandleRef(String value) {
        this.operationHandleRef = value;
    }

}
