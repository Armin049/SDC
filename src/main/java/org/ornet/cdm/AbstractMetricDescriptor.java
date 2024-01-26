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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * An abstract descriptor for a METRIC.
 * 
 * <p>Java-Klasse für AbstractMetricDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractMetricDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDescriptor">
 *       &lt;sequence>
 *         &lt;element name="Unit" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *         &lt;element name="BodySite" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Relation" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                   &lt;element name="Code" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *                   &lt;element name="Identification" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Kind" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="Rcm"/>
 *                       &lt;enumeration value="PS"/>
 *                       &lt;enumeration value="SST"/>
 *                       &lt;enumeration value="ECE"/>
 *                       &lt;enumeration value="DCE"/>
 *                       &lt;enumeration value="Oth"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="Entries" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}EntryRef" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="MetricCategory" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MetricCategory" />
 *       &lt;attribute name="DerivationMethod" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}DerivationMethod" />
 *       &lt;attribute name="MetricAvailability" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MetricAvailability" />
 *       &lt;attribute name="MaxMeasurementTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="MaxDelayTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="DeterminationPeriod" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="LifeTimePeriod" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="ActivationDuration" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMetricDescriptor", propOrder = {
    "unit",
    "bodySite",
    "relation"
})
@XmlSeeAlso({
    NumericMetricDescriptor.class,
    DistributionSampleArrayMetricDescriptor.class,
    StringMetricDescriptor.class,
    RealTimeSampleArrayMetricDescriptor.class
})
public class AbstractMetricDescriptor
    extends AbstractDescriptor
{

    @XmlElement(name = "Unit", required = true)
    protected CodedValue unit;
    @XmlElement(name = "BodySite")
    protected List<CodedValue> bodySite;
    @XmlElement(name = "Relation")
    protected List<AbstractMetricDescriptor.Relation> relation;
    @XmlAttribute(name = "MetricCategory", required = true)
    protected MetricCategory metricCategory;
    @XmlAttribute(name = "DerivationMethod")
    protected DerivationMethod derivationMethod;
    @XmlAttribute(name = "MetricAvailability", required = true)
    protected MetricAvailability metricAvailability;
    @XmlAttribute(name = "MaxMeasurementTime")
    protected Duration maxMeasurementTime;
    @XmlAttribute(name = "MaxDelayTime")
    protected Duration maxDelayTime;
    @XmlAttribute(name = "DeterminationPeriod")
    protected Duration determinationPeriod;
    @XmlAttribute(name = "LifeTimePeriod")
    protected Duration lifeTimePeriod;
    @XmlAttribute(name = "ActivationDuration")
    protected Duration activationDuration;

    /**
     * Ruft den Wert der unit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getUnit() {
        return unit;
    }

    /**
     * Legt den Wert der unit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setUnit(CodedValue value) {
        this.unit = value;
    }

    /**
     * Gets the value of the bodySite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bodySite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBodySite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedValue }
     * 
     * 
     */
    public List<CodedValue> getBodySite() {
        if (bodySite == null) {
            bodySite = new ArrayList<CodedValue>();
        }
        return this.bodySite;
    }

    /**
     * Gets the value of the relation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractMetricDescriptor.Relation }
     * 
     * 
     */
    public List<AbstractMetricDescriptor.Relation> getRelation() {
        if (relation == null) {
            relation = new ArrayList<AbstractMetricDescriptor.Relation>();
        }
        return this.relation;
    }

    /**
     * Ruft den Wert der metricCategory-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MetricCategory }
     *     
     */
    public MetricCategory getMetricCategory() {
        return metricCategory;
    }

    /**
     * Legt den Wert der metricCategory-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MetricCategory }
     *     
     */
    public void setMetricCategory(MetricCategory value) {
        this.metricCategory = value;
    }

    /**
     * Ruft den Wert der derivationMethod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DerivationMethod }
     *     
     */
    public DerivationMethod getDerivationMethod() {
        return derivationMethod;
    }

    /**
     * Legt den Wert der derivationMethod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DerivationMethod }
     *     
     */
    public void setDerivationMethod(DerivationMethod value) {
        this.derivationMethod = value;
    }

    /**
     * Ruft den Wert der metricAvailability-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MetricAvailability }
     *     
     */
    public MetricAvailability getMetricAvailability() {
        return metricAvailability;
    }

    /**
     * Legt den Wert der metricAvailability-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MetricAvailability }
     *     
     */
    public void setMetricAvailability(MetricAvailability value) {
        this.metricAvailability = value;
    }

    /**
     * Ruft den Wert der maxMeasurementTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMaxMeasurementTime() {
        return maxMeasurementTime;
    }

    /**
     * Legt den Wert der maxMeasurementTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMaxMeasurementTime(Duration value) {
        this.maxMeasurementTime = value;
    }

    /**
     * Ruft den Wert der maxDelayTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMaxDelayTime() {
        return maxDelayTime;
    }

    /**
     * Legt den Wert der maxDelayTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMaxDelayTime(Duration value) {
        this.maxDelayTime = value;
    }

    /**
     * Ruft den Wert der determinationPeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDeterminationPeriod() {
        return determinationPeriod;
    }

    /**
     * Legt den Wert der determinationPeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDeterminationPeriod(Duration value) {
        this.determinationPeriod = value;
    }

    /**
     * Ruft den Wert der lifeTimePeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getLifeTimePeriod() {
        return lifeTimePeriod;
    }

    /**
     * Legt den Wert der lifeTimePeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setLifeTimePeriod(Duration value) {
        this.lifeTimePeriod = value;
    }

    /**
     * Ruft den Wert der activationDuration-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getActivationDuration() {
        return activationDuration;
    }

    /**
     * Legt den Wert der activationDuration-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setActivationDuration(Duration value) {
        this.activationDuration = value;
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
     *         &lt;element name="Code" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
     *         &lt;element name="Identification" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Kind" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="Rcm"/>
     *             &lt;enumeration value="PS"/>
     *             &lt;enumeration value="SST"/>
     *             &lt;enumeration value="ECE"/>
     *             &lt;enumeration value="DCE"/>
     *             &lt;enumeration value="Oth"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="Entries" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}EntryRef" />
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
        "code",
        "identification"
    })
    public static class Relation {

        @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
        protected ExtensionType extension;
        @XmlElement(name = "Code")
        protected CodedValue code;
        @XmlElement(name = "Identification")
        protected InstanceIdentifier identification;
        @XmlAttribute(name = "Kind", required = true)
        protected String kind;
        @XmlAttribute(name = "Entries", required = true)
        protected List<String> entries;

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
         * Ruft den Wert der kind-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKind() {
            return kind;
        }

        /**
         * Legt den Wert der kind-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKind(String value) {
            this.kind = value;
        }

        /**
         * Gets the value of the entries property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entries property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntries().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getEntries() {
            if (entries == null) {
                entries = new ArrayList<String>();
            }
            return this.entries;
        }

    }

}
