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


/**
 * Details about a location. This information is derived from the HL7 PV1-3 PL.
 * 
 * <p>Java-Klasse für LocationDetail complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="LocationDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PoC" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Room" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Bed" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Facility" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Building" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Floor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationDetail", propOrder = {
    "extension"
})
public class LocationDetail {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlAttribute(name = "PoC")
    protected String poC;
    @XmlAttribute(name = "Room")
    protected String room;
    @XmlAttribute(name = "Bed")
    protected String bed;
    @XmlAttribute(name = "Facility")
    protected String facility;
    @XmlAttribute(name = "Building")
    protected String building;
    @XmlAttribute(name = "Floor")
    protected String floor;

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
     * Ruft den Wert der poC-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoC() {
        return poC;
    }

    /**
     * Legt den Wert der poC-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoC(String value) {
        this.poC = value;
    }

    /**
     * Ruft den Wert der room-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoom() {
        return room;
    }

    /**
     * Legt den Wert der room-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoom(String value) {
        this.room = value;
    }

    /**
     * Ruft den Wert der bed-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBed() {
        return bed;
    }

    /**
     * Legt den Wert der bed-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBed(String value) {
        this.bed = value;
    }

    /**
     * Ruft den Wert der facility-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacility() {
        return facility;
    }

    /**
     * Legt den Wert der facility-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacility(String value) {
        this.facility = value;
    }

    /**
     * Ruft den Wert der building-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Legt den Wert der building-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuilding(String value) {
        this.building = value;
    }

    /**
     * Ruft den Wert der floor-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Legt den Wert der floor-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloor(String value) {
        this.floor = value;
    }

}
