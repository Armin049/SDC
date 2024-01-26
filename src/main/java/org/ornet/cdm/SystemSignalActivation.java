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
import javax.xml.bind.annotation.XmlType;


/**
 * Defines a tuple consisting of an pm:AlertSignalManifestation and an pm:AlertActivation to describe the alert activation state of a certain ALERT SIGNAL manifestation.
 * 
 * Example: ./@Manifestation is "Aud" and ./@State is "Psd" means that any audible alert activation is paused.
 * 
 * 
 * <p>Java-Klasse für SystemSignalActivation complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SystemSignalActivation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Manifestation" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertSignalManifestation" />
 *       &lt;attribute name="State" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertActivation" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemSignalActivation")
public class SystemSignalActivation {

    @XmlAttribute(name = "Manifestation", required = true)
    protected AlertSignalManifestation manifestation;
    @XmlAttribute(name = "State", required = true)
    protected AlertActivation state;

    /**
     * Ruft den Wert der manifestation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertSignalManifestation }
     *     
     */
    public AlertSignalManifestation getManifestation() {
        return manifestation;
    }

    /**
     * Legt den Wert der manifestation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertSignalManifestation }
     *     
     */
    public void setManifestation(AlertSignalManifestation value) {
        this.manifestation = value;
    }

    /**
     * Ruft den Wert der state-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertActivation }
     *     
     */
    public AlertActivation getState() {
        return state;
    }

    /**
     * Legt den Wert der state-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertActivation }
     *     
     */
    public void setState(AlertActivation value) {
        this.state = value;
    }

}
