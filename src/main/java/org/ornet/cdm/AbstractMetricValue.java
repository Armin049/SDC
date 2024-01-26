//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Abstract value of a METRIC.
 * 
 * <p>Java-Klasse für AbstractMetricValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractMetricValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="MetricQuality">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Validity" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MeasurementValidity" />
 *                 &lt;attribute name="Mode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}GenerationMode" />
 *                 &lt;attribute name="Qi" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}QualityIndicator" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Annotation" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                   &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="StartTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *       &lt;attribute name="StopTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *       &lt;attribute name="DeterminationTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMetricValue", propOrder = {
    "extension",
    "metricQuality",
    "annotation"
})
@XmlSeeAlso({
    NumericMetricValue.class,
    StringMetricValue.class,
    SampleArrayValue.class
})
public class AbstractMetricValue {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "MetricQuality", required = true)
    protected AbstractMetricValue.MetricQuality metricQuality;
    @XmlElement(name = "Annotation")
    protected List<AbstractMetricValue.Annotation> annotation;
    @XmlAttribute(name = "StartTime")
    protected Long startTime;
    @XmlAttribute(name = "StopTime")
    protected Long stopTime;
    @XmlAttribute(name = "DeterminationTime")
    protected Long determinationTime;

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
     * Ruft den Wert der metricQuality-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AbstractMetricValue.MetricQuality }
     *     
     */
    public AbstractMetricValue.MetricQuality getMetricQuality() {
        return metricQuality;
    }

    /**
     * Legt den Wert der metricQuality-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractMetricValue.MetricQuality }
     *     
     */
    public void setMetricQuality(AbstractMetricValue.MetricQuality value) {
        this.metricQuality = value;
    }

    /**
     * Gets the value of the annotation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the annotation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnnotation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractMetricValue.Annotation }
     * 
     * 
     */
    public List<AbstractMetricValue.Annotation> getAnnotation() {
        if (annotation == null) {
            annotation = new ArrayList<AbstractMetricValue.Annotation>();
        }
        return this.annotation;
    }

    /**
     * Ruft den Wert der startTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * Legt den Wert der startTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartTime(Long value) {
        this.startTime = value;
    }

    /**
     * Ruft den Wert der stopTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStopTime() {
        return stopTime;
    }

    /**
     * Legt den Wert der stopTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStopTime(Long value) {
        this.stopTime = value;
    }

    /**
     * Ruft den Wert der determinationTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDeterminationTime() {
        return determinationTime;
    }

    /**
     * Legt den Wert der determinationTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDeterminationTime(Long value) {
        this.determinationTime = value;
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
     *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
     *         &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
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
        "extension",
        "type"
    })
    public static class Annotation {

        @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
        protected ExtensionType extension;
        @XmlElement(name = "Type", required = true)
        protected CodedValue type;

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
     *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Validity" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MeasurementValidity" />
     *       &lt;attribute name="Mode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}GenerationMode" />
     *       &lt;attribute name="Qi" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}QualityIndicator" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "extension"
    })
    public static class MetricQuality {

        @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
        protected ExtensionType extension;
        @XmlAttribute(name = "Validity", required = true)
        protected MeasurementValidity validity;
        @XmlAttribute(name = "Mode")
        protected GenerationMode mode;
        @XmlAttribute(name = "Qi")
        protected BigDecimal qi;

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
         * Ruft den Wert der mode-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link GenerationMode }
         *     
         */
        public GenerationMode getMode() {
            return mode;
        }

        /**
         * Legt den Wert der mode-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link GenerationMode }
         *     
         */
        public void setMode(GenerationMode value) {
            this.mode = value;
        }

        /**
         * Ruft den Wert der qi-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getQi() {
            return qi;
        }

        /**
         * Legt den Wert der qi-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setQi(BigDecimal value) {
            this.qi = value;
        }

    }

}
