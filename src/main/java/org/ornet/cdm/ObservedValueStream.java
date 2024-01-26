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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReport">
 *       &lt;sequence>
 *         &lt;element name="Value" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Value" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}SampleArrayValue" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Metric" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
 *                 &lt;attribute name="StateVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}VersionCounter" />
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
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "ObservedValueStream", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class ObservedValueStream
    extends AbstractReport
{

    @XmlElement(name = "Value", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    protected List<ObservedValueStream.Value> value;

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObservedValueStream.Value }
     * 
     * 
     */
    public List<ObservedValueStream.Value> getValue() {
        if (value == null) {
            value = new ArrayList<ObservedValueStream.Value>();
        }
        return this.value;
    }


    /**
     * A stream ELEMENT that contains observed values of a stream-able state.
     * 
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Value" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}SampleArrayValue" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Metric" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
     *       &lt;attribute name="StateVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}VersionCounter" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Value {

        @XmlElement(name = "Value", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
        protected SampleArrayValue value;
        @XmlAttribute(name = "Metric", required = true)
        protected String metric;
        @XmlAttribute(name = "StateVersion")
        protected Long stateVersion;

        /**
         * Ruft den Wert der value-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link SampleArrayValue }
         *     
         */
        public SampleArrayValue getValue() {
            return value;
        }

        /**
         * Legt den Wert der value-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link SampleArrayValue }
         *     
         */
        public void setValue(SampleArrayValue value) {
            this.value = value;
        }

        /**
         * Ruft den Wert der metric-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMetric() {
            return metric;
        }

        /**
         * Legt den Wert der metric-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMetric(String value) {
            this.metric = value;
        }

        /**
         * Ruft den Wert der stateVersion-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getStateVersion() {
            return stateVersion;
        }

        /**
         * Legt den Wert der stateVersion-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setStateVersion(Long value) {
            this.stateVersion = value;
        }

    }

}
