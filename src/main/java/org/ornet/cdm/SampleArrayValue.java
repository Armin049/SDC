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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Type that contains sequences of values, i.e., sample arrays.
 * 
 * The ./pmMetricQuality ELEMENT relates to all samples.
 * 
 * NOTE 1—pm:Timestamp (see base: pm:AbstractMetricValue) refers to the first value of the array. The individual timestamps of the values can thus be computed from the sample rate (see pm:RealTimeSampleArrayMetricDescriptor).
 * NOTE 2—If ./pmMetricQuality cannot be applied to all samples due to, e.g., some invalid values, a SERVICE PROVIDER can decide to set ./pmMetricQuality/@Validity to "Qst" or "Inv".
 * 
 * <p>Java-Klasse für SampleArrayValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SampleArrayValue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractMetricValue">
 *       &lt;sequence>
 *         &lt;element name="ApplyAnnotation" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="AnnotationIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                 &lt;attribute name="SampleIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Samples" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}RealTimeValueType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleArrayValue", propOrder = {
    "applyAnnotation"
})
public class SampleArrayValue
    extends AbstractMetricValue
{

    @XmlElement(name = "ApplyAnnotation")
    protected List<SampleArrayValue.ApplyAnnotation> applyAnnotation;
    @XmlAttribute(name = "Samples")
    protected List<BigDecimal> samples;

    /**
     * Gets the value of the applyAnnotation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applyAnnotation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplyAnnotation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SampleArrayValue.ApplyAnnotation }
     * 
     * 
     */
    public List<SampleArrayValue.ApplyAnnotation> getApplyAnnotation() {
        if (applyAnnotation == null) {
            applyAnnotation = new ArrayList<SampleArrayValue.ApplyAnnotation>();
        }
        return this.applyAnnotation;
    }

    /**
     * Gets the value of the samples property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the samples property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSamples().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getSamples() {
        if (samples == null) {
            samples = new ArrayList<BigDecimal>();
        }
        return this.samples;
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
     *       &lt;attribute name="AnnotationIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
     *       &lt;attribute name="SampleIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ApplyAnnotation {

        @XmlAttribute(name = "AnnotationIndex", required = true)
        @XmlSchemaType(name = "unsignedInt")
        protected long annotationIndex;
        @XmlAttribute(name = "SampleIndex", required = true)
        @XmlSchemaType(name = "unsignedInt")
        protected long sampleIndex;

        /**
         * Ruft den Wert der annotationIndex-Eigenschaft ab.
         * 
         */
        public long getAnnotationIndex() {
            return annotationIndex;
        }

        /**
         * Legt den Wert der annotationIndex-Eigenschaft fest.
         * 
         */
        public void setAnnotationIndex(long value) {
            this.annotationIndex = value;
        }

        /**
         * Ruft den Wert der sampleIndex-Eigenschaft ab.
         * 
         */
        public long getSampleIndex() {
            return sampleIndex;
        }

        /**
         * Legt den Wert der sampleIndex-Eigenschaft fest.
         * 
         */
        public void setSampleIndex(long value) {
            this.sampleIndex = value;
        }

    }

}
