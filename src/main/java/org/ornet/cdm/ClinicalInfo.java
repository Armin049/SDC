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
 * This type describes a minimal clinical observation.
 * 
 * <p>Java-Klasse für ClinicalInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ClinicalInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *         &lt;element name="Code" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *         &lt;element name="Criticality" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Lo"/>
 *               &lt;enumeration value="Hi"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Description" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RelatedMeasurement" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Value" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement"/>
 *                   &lt;element name="ReferenceRange" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Range" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range"/>
 *                             &lt;element name="Meaning" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Validity" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MeasurementValidity" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClinicalInfo", propOrder = {
    "extension",
    "type",
    "code",
    "criticality",
    "description",
    "relatedMeasurement"
})
public class ClinicalInfo {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "Type")
    protected CodedValue type;
    @XmlElement(name = "Code")
    protected CodedValue code;
    @XmlElement(name = "Criticality")
    protected String criticality;
    @XmlElement(name = "Description")
    protected List<LocalizedText> description;
    @XmlElement(name = "RelatedMeasurement")
    protected List<ClinicalInfo.RelatedMeasurement> relatedMeasurement;

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
     * Ruft den Wert der code-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getCode() {
        return code;
    }

    /**
     * Legt den Wert der code-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setCode(CodedValue value) {
        this.code = value;
    }

    /**
     * Ruft den Wert der criticality-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCriticality() {
        return criticality;
    }

    /**
     * Legt den Wert der criticality-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCriticality(String value) {
        this.criticality = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedText }
     * 
     * 
     */
    public List<LocalizedText> getDescription() {
        if (description == null) {
            description = new ArrayList<LocalizedText>();
        }
        return this.description;
    }

    /**
     * Gets the value of the relatedMeasurement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedMeasurement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedMeasurement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClinicalInfo.RelatedMeasurement }
     * 
     * 
     */
    public List<ClinicalInfo.RelatedMeasurement> getRelatedMeasurement() {
        if (relatedMeasurement == null) {
            relatedMeasurement = new ArrayList<ClinicalInfo.RelatedMeasurement>();
        }
        return this.relatedMeasurement;
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
     *         &lt;element name="Value" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement"/>
     *         &lt;element name="ReferenceRange" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Range" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range"/>
     *                   &lt;element name="Meaning" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="Validity" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MeasurementValidity" />
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
        "referenceRange"
    })
    public static class RelatedMeasurement {

        @XmlElement(name = "Value", required = true)
        protected Measurement value;
        @XmlElement(name = "ReferenceRange")
        protected List<ClinicalInfo.RelatedMeasurement.ReferenceRange> referenceRange;
        @XmlAttribute(name = "Validity")
        protected MeasurementValidity validity;

        /**
         * Ruft den Wert der value-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Measurement }
         *     
         */
        public Measurement getValue() {
            return value;
        }

        /**
         * Legt den Wert der value-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Measurement }
         *     
         */
        public void setValue(Measurement value) {
            this.value = value;
        }

        /**
         * Gets the value of the referenceRange property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the referenceRange property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReferenceRange().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ClinicalInfo.RelatedMeasurement.ReferenceRange }
         * 
         * 
         */
        public List<ClinicalInfo.RelatedMeasurement.ReferenceRange> getReferenceRange() {
            if (referenceRange == null) {
                referenceRange = new ArrayList<ClinicalInfo.RelatedMeasurement.ReferenceRange>();
            }
            return this.referenceRange;
        }

        /**
         * Ruft den Wert der validity-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link MeasurementValidity }
         *     
         */
        public MeasurementValidity getValidity() {
            return validity;
        }

        /**
         * Legt den Wert der validity-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link MeasurementValidity }
         *     
         */
        public void setValidity(MeasurementValidity value) {
            this.validity = value;
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
         *         &lt;element name="Range" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range"/>
         *         &lt;element name="Meaning" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
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
            "range",
            "meaning"
        })
        public static class ReferenceRange {

            @XmlElement(name = "Range", required = true)
            protected Range range;
            @XmlElement(name = "Meaning")
            protected CodedValue meaning;

            /**
             * Ruft den Wert der range-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Range }
             *     
             */
            public Range getRange() {
                return range;
            }

            /**
             * Legt den Wert der range-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Range }
             *     
             */
            public void setRange(Range value) {
                this.range = value;
            }

            /**
             * Ruft den Wert der meaning-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link CodedValue }
             *     
             */
            public CodedValue getMeaning() {
                return meaning;
            }

            /**
             * Legt den Wert der meaning-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link CodedValue }
             *     
             */
            public void setMeaning(CodedValue value) {
                this.meaning = value;
            }

        }

    }

}
