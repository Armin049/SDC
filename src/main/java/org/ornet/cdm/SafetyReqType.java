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
 * Indicates that data is required that preserve patient safety during transmission.
 * 
 * <p>Java-Klasse für SafetyReqType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SafetyReqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DualChannelDef" type="{http://standards.ieee.org/downloads/11073/11073-20702-2016}DualChannelDefType" minOccurs="0"/>
 *         &lt;element name="SafetyContextDef" type="{http://standards.ieee.org/downloads/11073/11073-20702-2016}SafetyContextDefType" minOccurs="0"/>
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
@XmlType(name = "SafetyReqType", namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", propOrder = {
    "dualChannelDef",
    "safetyContextDef",
    "any"
})
public class SafetyReqType {

    @XmlElement(name = "DualChannelDef")
    protected DualChannelDefType dualChannelDef;
    @XmlElement(name = "SafetyContextDef")
    protected SafetyContextDefType safetyContextDef;
    @XmlAnyElement
    protected List<Element> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Ruft den Wert der dualChannelDef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DualChannelDefType }
     *     
     */
    public DualChannelDefType getDualChannelDef() {
        return dualChannelDef;
    }

    /**
     * Legt den Wert der dualChannelDef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DualChannelDefType }
     *     
     */
    public void setDualChannelDef(DualChannelDefType value) {
        this.dualChannelDef = value;
    }

    /**
     * Ruft den Wert der safetyContextDef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SafetyContextDefType }
     *     
     */
    public SafetyContextDefType getSafetyContextDef() {
        return safetyContextDef;
    }

    /**
     * Legt den Wert der safetyContextDef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SafetyContextDefType }
     *     
     */
    public void setSafetyContextDef(SafetyContextDefType value) {
        this.safetyContextDef = value;
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
