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
import javax.xml.namespace.QName;


/**
 * An entry in a CONTAINMENT TREE.
 * 
 * <p>Java-Klasse für ContainmentTreeEntry complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ContainmentTreeEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ContainmentTreeInfo"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContainmentTreeEntry", propOrder = {
    "extension",
    "type"
})
public class ContainmentTreeEntry {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "Type")
    protected CodedValue type;
    @XmlAttribute(name = "HandleRef")
    protected String handleRef;
    @XmlAttribute(name = "ParentHandleRef")
    protected String parentHandleRef;
    @XmlAttribute(name = "EntryType")
    protected QName entryType;
    @XmlAttribute(name = "ChildrenCount")
    protected Integer childrenCount;

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
     * Ruft den Wert der handleRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandleRef() {
        return handleRef;
    }

    /**
     * Legt den Wert der handleRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandleRef(String value) {
        this.handleRef = value;
    }

    /**
     * Ruft den Wert der parentHandleRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentHandleRef() {
        return parentHandleRef;
    }

    /**
     * Legt den Wert der parentHandleRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentHandleRef(String value) {
        this.parentHandleRef = value;
    }

    /**
     * Ruft den Wert der entryType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getEntryType() {
        return entryType;
    }

    /**
     * Legt den Wert der entryType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setEntryType(QName value) {
        this.entryType = value;
    }

    /**
     * Ruft den Wert der childrenCount-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChildrenCount() {
        return childrenCount;
    }

    /**
     * Legt den Wert der childrenCount-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChildrenCount(Integer value) {
        this.childrenCount = value;
    }

}
