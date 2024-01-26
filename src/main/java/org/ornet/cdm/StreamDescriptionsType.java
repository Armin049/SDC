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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * This element contains the declarations of all the stream types that apply to a given context.
 * 
 * <p>Java-Klasse für StreamDescriptionsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StreamDescriptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Types" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any processContents='skip' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;anyAttribute processContents='skip' namespace='##other'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="StreamType" type="{http://standards.ieee.org/downloads/11073/11073-20702-2016}StreamTypeType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TargetNamespace" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;anyAttribute processContents='skip' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StreamDescriptionsType", namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", propOrder = {
    "types",
    "streamType"
})
public class StreamDescriptionsType {

    @XmlElement(name = "Types")
    protected StreamDescriptionsType.Types types;
    @XmlElement(name = "StreamType", required = true)
    protected List<StreamTypeType> streamType;
    @XmlAttribute(name = "TargetNamespace", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String targetNamespace;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Ruft den Wert der types-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StreamDescriptionsType.Types }
     *     
     */
    public StreamDescriptionsType.Types getTypes() {
        return types;
    }

    /**
     * Legt den Wert der types-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StreamDescriptionsType.Types }
     *     
     */
    public void setTypes(StreamDescriptionsType.Types value) {
        this.types = value;
    }

    /**
     * Gets the value of the streamType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the streamType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStreamType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StreamTypeType }
     * 
     * 
     */
    public List<StreamTypeType> getStreamType() {
        if (streamType == null) {
            streamType = new ArrayList<StreamTypeType>();
        }
        return this.streamType;
    }

    /**
     * Ruft den Wert der targetNamespace-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Legt den Wert der targetNamespace-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetNamespace(String value) {
        this.targetNamespace = value;
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


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
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
    @XmlType(name = "", propOrder = {
        "any"
    })
    public static class Types {

        @XmlAnyElement
        protected List<Element> any;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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

}
