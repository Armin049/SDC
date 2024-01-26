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
import javax.xml.bind.annotation.XmlType;


/**
 * A reference to an identifiable location with human readable location details.
 * 
 * <p>Java-Klasse für LocationReference complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="LocationReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="Identification" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" maxOccurs="unbounded"/>
 *         &lt;element name="LocationDetail" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocationDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationReference", propOrder = {
    "extension",
    "identification",
    "locationDetail"
})
public class LocationReference {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "Identification", required = true)
    protected List<InstanceIdentifier> identification;
    @XmlElement(name = "LocationDetail")
    protected LocationDetail locationDetail;

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
     * Gets the value of the identification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceIdentifier }
     * 
     * 
     */
    public List<InstanceIdentifier> getIdentification() {
        if (identification == null) {
            identification = new ArrayList<InstanceIdentifier>();
        }
        return this.identification;
    }

    /**
     * Ruft den Wert der locationDetail-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LocationDetail }
     *     
     */
    public LocationDetail getLocationDetail() {
        return locationDetail;
    }

    /**
     * Legt den Wert der locationDetail-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationDetail }
     *     
     */
    public void setLocationDetail(LocationDetail value) {
        this.locationDetail = value;
    }

}
