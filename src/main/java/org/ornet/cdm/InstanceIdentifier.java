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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * An identifier that uniquely identifies a thing or object.
 * 
 * Examples: object identifiers for medical record numbers, order ids, location ids, etc. InstanceIdentifier is defined in accordance to [InstanceIdentifier].
 * 
 * ./@Root and ./@Extension of an instance identifier do not identify the type of the object being identified, or the type of the association between the object and the identifier - they only form the identifier itself. The identifier type SHALL be expressed by ./Type.
 * 
 * <p>Java-Klasse für InstanceIdentifier complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="InstanceIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *         &lt;element name="IdentifierName" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Root">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyURI">
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Extension">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstanceIdentifier", propOrder = {
    "extension1",
    "type",
    "identifierName"
})
@XmlSeeAlso({
    OperatingJurisdiction.class
})
public class InstanceIdentifier {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension1;
    @XmlElement(name = "Type")
    protected CodedValue type;
    @XmlElement(name = "IdentifierName")
    protected List<LocalizedText> identifierName;
    @XmlAttribute(name = "Root")
    protected String root;
    @XmlAttribute(name = "Extension")
    protected String extension;

    /**
     * Ruft den Wert der extension1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension1() {
        return extension1;
    }

    /**
     * Legt den Wert der extension1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension1(ExtensionType value) {
        this.extension1 = value;
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
     * Gets the value of the identifierName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifierName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifierName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedText }
     * 
     * 
     */
    public List<LocalizedText> getIdentifierName() {
        if (identifierName == null) {
            identifierName = new ArrayList<LocalizedText>();
        }
        return this.identifierName;
    }

    /**
     * Ruft den Wert der root-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoot() {
        return root;
    }

    /**
     * Legt den Wert der root-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoot(String value) {
        this.root = value;
    }

    /**
     * Ruft den Wert der extension-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Legt den Wert der extension-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtension(String value) {
        this.extension = value;
    }

}
