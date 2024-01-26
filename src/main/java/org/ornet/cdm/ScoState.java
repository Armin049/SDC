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
 * Corresponding state of pm:ScoDescriptor.
 * 
 * <p>Java-Klasse für ScoState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ScoState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentState">
 *       &lt;sequence>
 *         &lt;element name="OperationGroup" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                   &lt;element name="Type" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="OperatingMode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperatingMode" />
 *                 &lt;attribute name="Operations" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperationRef" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="InvocationRequested" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperationRef" />
 *       &lt;attribute name="InvocationRequired" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperationRef" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScoState", propOrder = {
    "operationGroup"
})
public class ScoState
    extends AbstractDeviceComponentState
{

    @XmlElement(name = "OperationGroup")
    protected List<ScoState.OperationGroup> operationGroup;
    @XmlAttribute(name = "InvocationRequested")
    protected List<String> invocationRequested;
    @XmlAttribute(name = "InvocationRequired")
    protected List<String> invocationRequired;

    /**
     * Gets the value of the operationGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operationGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperationGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScoState.OperationGroup }
     * 
     * 
     */
    public List<ScoState.OperationGroup> getOperationGroup() {
        if (operationGroup == null) {
            operationGroup = new ArrayList<ScoState.OperationGroup>();
        }
        return this.operationGroup;
    }

    /**
     * Gets the value of the invocationRequested property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invocationRequested property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvocationRequested().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getInvocationRequested() {
        if (invocationRequested == null) {
            invocationRequested = new ArrayList<String>();
        }
        return this.invocationRequested;
    }

    /**
     * Gets the value of the invocationRequired property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invocationRequired property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvocationRequired().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getInvocationRequired() {
        if (invocationRequired == null) {
            invocationRequired = new ArrayList<String>();
        }
        return this.invocationRequired;
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
     *       &lt;attribute name="OperatingMode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperatingMode" />
     *       &lt;attribute name="Operations" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperationRef" />
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
    public static class OperationGroup {

        @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
        protected ExtensionType extension;
        @XmlElement(name = "Type", required = true)
        protected CodedValue type;
        @XmlAttribute(name = "OperatingMode")
        protected OperatingMode operatingMode;
        @XmlAttribute(name = "Operations")
        protected List<String> operations;

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
         * Ruft den Wert der operatingMode-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link OperatingMode }
         *     
         */
        public OperatingMode getOperatingMode() {
            return operatingMode;
        }

        /**
         * Legt den Wert der operatingMode-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link OperatingMode }
         *     
         */
        public void setOperatingMode(OperatingMode value) {
            this.operatingMode = value;
        }

        /**
         * Gets the value of the operations property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the operations property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOperations().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getOperations() {
            if (operations == null) {
                operations = new ArrayList<String>();
            }
            return this.operations;
        }

    }

}
