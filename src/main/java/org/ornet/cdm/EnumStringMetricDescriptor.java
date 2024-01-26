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
 * An enumerated string METRIC represents a textual status or annotation information with a constrained set of possible values.
 * 
 * Example: a ventilation mode.
 * 
 * <p>Java-Klasse für EnumStringMetricDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EnumStringMetricDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}StringMetricDescriptor">
 *       &lt;sequence>
 *         &lt;element name="AllowedValue" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *                   &lt;element name="Identification" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
 *                   &lt;element name="Characteristic" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnumStringMetricDescriptor", propOrder = {
    "allowedValue"
})
public class EnumStringMetricDescriptor
    extends StringMetricDescriptor
{

    @XmlElement(name = "AllowedValue", required = true)
    protected List<EnumStringMetricDescriptor.AllowedValue> allowedValue;

    /**
     * Gets the value of the allowedValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allowedValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllowedValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnumStringMetricDescriptor.AllowedValue }
     * 
     * 
     */
    public List<EnumStringMetricDescriptor.AllowedValue> getAllowedValue() {
        if (allowedValue == null) {
            allowedValue = new ArrayList<EnumStringMetricDescriptor.AllowedValue>();
        }
        return this.allowedValue;
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
     *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
     *         &lt;element name="Identification" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
     *         &lt;element name="Characteristic" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value",
        "type",
        "identification",
        "characteristic"
    })
    public static class AllowedValue {

        @XmlElement(name = "Value", required = true)
        protected String value;
        @XmlElement(name = "Type")
        protected CodedValue type;
        @XmlElement(name = "Identification")
        protected InstanceIdentifier identification;
        @XmlElement(name = "Characteristic")
        protected Measurement characteristic;

        /**
         * Ruft den Wert der value-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Legt den Wert der value-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
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
         * Ruft den Wert der identification-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link InstanceIdentifier }
         *     
         */
        public InstanceIdentifier getIdentification() {
            return identification;
        }

        /**
         * Legt den Wert der identification-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link InstanceIdentifier }
         *     
         */
        public void setIdentification(InstanceIdentifier value) {
            this.identification = value;
        }

        /**
         * Ruft den Wert der characteristic-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Measurement }
         *     
         */
        public Measurement getCharacteristic() {
            return characteristic;
        }

        /**
         * Legt den Wert der characteristic-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Measurement }
         *     
         */
        public void setCharacteristic(Measurement value) {
            this.characteristic = value;
        }

    }

}
