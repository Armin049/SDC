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
 * Provides information in terms of component calibration. By default, it only maintains a calibration flag.
 * 
 * <p>Java-Klasse für CalibrationInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CalibrationInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="CalibrationDocumentation" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Documentation" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="CalibrationResult" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Code" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *                             &lt;element name="Value" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="ComponentCalibrationState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CalibrationState" />
 *       &lt;attribute name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CalibrationType" />
 *       &lt;attribute name="Time" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalibrationInfo", propOrder = {
    "extension",
    "calibrationDocumentation"
})
public class CalibrationInfo {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "CalibrationDocumentation")
    protected List<CalibrationInfo.CalibrationDocumentation> calibrationDocumentation;
    @XmlAttribute(name = "ComponentCalibrationState")
    protected CalibrationState componentCalibrationState;
    @XmlAttribute(name = "Type")
    protected CalibrationType type;
    @XmlAttribute(name = "Time")
    protected Long time;

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
     * Gets the value of the calibrationDocumentation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calibrationDocumentation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalibrationDocumentation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalibrationInfo.CalibrationDocumentation }
     * 
     * 
     */
    public List<CalibrationInfo.CalibrationDocumentation> getCalibrationDocumentation() {
        if (calibrationDocumentation == null) {
            calibrationDocumentation = new ArrayList<CalibrationInfo.CalibrationDocumentation>();
        }
        return this.calibrationDocumentation;
    }

    /**
     * Ruft den Wert der componentCalibrationState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationState }
     *     
     */
    public CalibrationState getComponentCalibrationState() {
        return componentCalibrationState;
    }

    /**
     * Legt den Wert der componentCalibrationState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationState }
     *     
     */
    public void setComponentCalibrationState(CalibrationState value) {
        this.componentCalibrationState = value;
    }

    /**
     * Ruft den Wert der type-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationType }
     *     
     */
    public CalibrationType getType() {
        return type;
    }

    /**
     * Legt den Wert der type-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationType }
     *     
     */
    public void setType(CalibrationType value) {
        this.type = value;
    }

    /**
     * Ruft den Wert der time-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTime() {
        return time;
    }

    /**
     * Legt den Wert der time-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTime(Long value) {
        this.time = value;
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
     *         &lt;element name="Documentation" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="CalibrationResult" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Code" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
     *                   &lt;element name="Value" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement"/>
     *                 &lt;/sequence>
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
    @XmlType(name = "", propOrder = {
        "documentation",
        "calibrationResult"
    })
    public static class CalibrationDocumentation {

        @XmlElement(name = "Documentation")
        protected List<LocalizedText> documentation;
        @XmlElement(name = "CalibrationResult")
        protected List<CalibrationInfo.CalibrationDocumentation.CalibrationResult> calibrationResult;

        /**
         * Gets the value of the documentation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the documentation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDocumentation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LocalizedText }
         * 
         * 
         */
        public List<LocalizedText> getDocumentation() {
            if (documentation == null) {
                documentation = new ArrayList<LocalizedText>();
            }
            return this.documentation;
        }

        /**
         * Gets the value of the calibrationResult property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the calibrationResult property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCalibrationResult().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CalibrationInfo.CalibrationDocumentation.CalibrationResult }
         * 
         * 
         */
        public List<CalibrationInfo.CalibrationDocumentation.CalibrationResult> getCalibrationResult() {
            if (calibrationResult == null) {
                calibrationResult = new ArrayList<CalibrationInfo.CalibrationDocumentation.CalibrationResult>();
            }
            return this.calibrationResult;
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
         *         &lt;element name="Code" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
         *         &lt;element name="Value" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement"/>
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
            "code",
            "value"
        })
        public static class CalibrationResult {

            @XmlElement(name = "Code", required = true)
            protected CodedValue code;
            @XmlElement(name = "Value", required = true)
            protected Measurement value;

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

        }

    }

}
