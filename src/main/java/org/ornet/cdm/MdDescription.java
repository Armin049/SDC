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


/**
 * MdDescription is the root container to represent the descriptive part of the MDIB. The descriptive part describes the capabilities provided by a POC MEDICAL DEVICE, e.g., which measurements, alerts and settings it provides. As the descriptive part does not change as frequently as the state part, it is well-known as the (almost) static part of the MDIB. The MdDescription's counterpart is pm:MdState.
 * 
 * <p>Java-Klasse für MdDescription complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="MdDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="Mds" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MdsDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DescriptionVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}VersionCounter" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdDescription", propOrder = {
    "extension",
    "mds"
})
public class MdDescription {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "Mds")
    protected List<MdsDescriptor> mds;
    @XmlAttribute(name = "DescriptionVersion")
    protected Long descriptionVersion;

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
     * Gets the value of the mds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MdsDescriptor }
     * 
     * 
     */
    public List<MdsDescriptor> getMds() {
        if (mds == null) {
            mds = new ArrayList<MdsDescriptor>();
        }
        return this.mds;
    }

    /**
     * Ruft den Wert der descriptionVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDescriptionVersion() {
        return descriptionVersion;
    }

    /**
     * Legt den Wert der descriptionVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDescriptionVersion(Long value) {
        this.descriptionVersion = value;
    }

}
