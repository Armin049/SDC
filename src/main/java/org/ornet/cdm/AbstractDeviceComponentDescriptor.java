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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * AbstractDeviceComponentDescriptor describes a basic DEVICE COMPONENT.
 * 
 * <p>Java-Klasse für AbstractDeviceComponentDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractDeviceComponentDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDescriptor">
 *       &lt;sequence>
 *         &lt;element name="ProductionSpecification" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SpecType" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *                   &lt;element name="ProductionSpec" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ComponentId" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
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
@XmlType(name = "AbstractDeviceComponentDescriptor", propOrder = {
    "productionSpecification"
})
@XmlSeeAlso({
    SystemContextDescriptor.class,
    BatteryDescriptor.class,
    ScoDescriptor.class,
    ChannelDescriptor.class,
    ClockDescriptor.class,
    AbstractComplexDeviceComponentDescriptor.class
})
public class AbstractDeviceComponentDescriptor
    extends AbstractDescriptor
{

    @XmlElement(name = "ProductionSpecification")
    protected List<AbstractDeviceComponentDescriptor.ProductionSpecification> productionSpecification;

    /**
     * Gets the value of the productionSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productionSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductionSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractDeviceComponentDescriptor.ProductionSpecification }
     * 
     * 
     */
    public List<AbstractDeviceComponentDescriptor.ProductionSpecification> getProductionSpecification() {
        if (productionSpecification == null) {
            productionSpecification = new ArrayList<AbstractDeviceComponentDescriptor.ProductionSpecification>();
        }
        return this.productionSpecification;
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
     *         &lt;element name="SpecType" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
     *         &lt;element name="ProductionSpec" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ComponentId" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
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
        "specType",
        "productionSpec",
        "componentId"
    })
    public static class ProductionSpecification {

        @XmlElement(name = "SpecType", required = true)
        protected CodedValue specType;
        @XmlElement(name = "ProductionSpec", required = true)
        protected String productionSpec;
        @XmlElement(name = "ComponentId")
        protected InstanceIdentifier componentId;

        /**
         * Ruft den Wert der specType-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link CodedValue }
         *     
         */
        public CodedValue getSpecType() {
            return specType;
        }

        /**
         * Legt den Wert der specType-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link CodedValue }
         *     
         */
        public void setSpecType(CodedValue value) {
            this.specType = value;
        }

        /**
         * Ruft den Wert der productionSpec-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductionSpec() {
            return productionSpec;
        }

        /**
         * Legt den Wert der productionSpec-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductionSpec(String value) {
            this.productionSpec = value;
        }

        /**
         * Ruft den Wert der componentId-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link InstanceIdentifier }
         *     
         */
        public InstanceIdentifier getComponentId() {
            return componentId;
        }

        /**
         * Legt den Wert der componentId-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link InstanceIdentifier }
         *     
         */
        public void setComponentId(InstanceIdentifier value) {
            this.componentId = value;
        }

    }

}
