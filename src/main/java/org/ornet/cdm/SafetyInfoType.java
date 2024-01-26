//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * Container element for embedding safety information in a SOAP message header.
 * 
 * <p>Java-Klasse für SafetyInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SafetyInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DualChannel" type="{http://standards.ieee.org/downloads/11073/11073-20702-2016}DualChannelType" minOccurs="0"/>
 *         &lt;element name="SafetyContext" type="{http://standards.ieee.org/downloads/11073/11073-20702-2016}SafetyContextType" minOccurs="0"/>
 *         &lt;any processContents='skip' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='skip' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SafetyInfoType", namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", propOrder = {
    "dualChannel",
    "safetyContext",
    "any"
})
public class SafetyInfoType {

    @XmlElement(name = "DualChannel")
    protected DualChannelType dualChannel;
    @XmlElement(name = "SafetyContext")
    protected SafetyContextType safetyContext;
    @XmlAnyElement
    protected List<Element> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Ruft den Wert der dualChannel-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DualChannelType }
     *     
     */
    public DualChannelType getDualChannel() {
        return dualChannel;
    }

    /**
     * Legt den Wert der dualChannel-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DualChannelType }
     *     
     */
    public void setDualChannel(DualChannelType value) {
        this.dualChannel = value;
    }

    /**
     * Ruft den Wert der safetyContext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SafetyContextType }
     *     
     */
    public SafetyContextType getSafetyContext() {
        return safetyContext;
    }

    /**
     * Legt den Wert der safetyContext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SafetyContextType }
     *     
     */
    public void setSafetyContext(SafetyContextType value) {
        this.safetyContext = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
