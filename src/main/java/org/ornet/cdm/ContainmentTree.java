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
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * CONTAINMENT TREE part of an ELEMENT of an MDS CONTAINMENT TREE.
 * 
 * <p>Java-Klasse für ContainmentTree complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ContainmentTree">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="Entry" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ContainmentTreeEntry" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ContainmentTree", propOrder = {
    "extension",
    "entry"
})
public class ContainmentTree {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "Entry")
    protected List<ContainmentTreeEntry> entry;
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
     * Gets the value of the entry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContainmentTreeEntry }
     * 
     * 
     */
    public List<ContainmentTreeEntry> getEntry() {
        if (entry == null) {
            entry = new ArrayList<ContainmentTreeEntry>();
        }
        return this.entry;
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
