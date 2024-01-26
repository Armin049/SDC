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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * MdsDescriptor represents an MDS that in turn represents a POC MEDICAL DEVICE such as an anesthesia workstation. It contains an abstraction of the hardware specification of a POC MEDICAL DEVICE plus a list of VMDs, contextual information and clock object.
 * 
 * NOTE—The IEEE 11073-10201 has different specializations that are all representable by MdsDescriptor.
 * 
 * <p>Java-Klasse für MdsDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="MdsDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractComplexDeviceComponentDescriptor">
 *       &lt;sequence>
 *         &lt;element name="MetaData" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                   &lt;element name="Udi" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                             &lt;element name="DeviceIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="HumanReadableForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Issuer" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
 *                             &lt;element name="Jurisdiction" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="LotNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Manufacturer" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="ManufactureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="ModelName" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="ModelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SystemContext" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}SystemContextDescriptor" minOccurs="0"/>
 *         &lt;element name="Clock" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ClockDescriptor" minOccurs="0"/>
 *         &lt;element name="Battery" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}BatteryDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ApprovedJurisdictions" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ApprovedJurisdictions" minOccurs="0"/>
 *         &lt;element name="Vmd" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}VmdDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdsDescriptor", propOrder = {
    "metaData",
    "systemContext",
    "clock",
    "battery",
    "approvedJurisdictions",
    "vmd"
})
public class MdsDescriptor
    extends AbstractComplexDeviceComponentDescriptor
{

    @XmlElement(name = "MetaData")
    protected MdsDescriptor.MetaData metaData;
    @XmlElement(name = "SystemContext")
    protected SystemContextDescriptor systemContext;
    @XmlElement(name = "Clock")
    protected ClockDescriptor clock;
    @XmlElement(name = "Battery")
    protected List<BatteryDescriptor> battery;
    @XmlElement(name = "ApprovedJurisdictions")
    protected ApprovedJurisdictions approvedJurisdictions;
    @XmlElement(name = "Vmd")
    protected List<VmdDescriptor> vmd;

    /**
     * Ruft den Wert der metaData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MdsDescriptor.MetaData }
     *     
     */
    public MdsDescriptor.MetaData getMetaData() {
        return metaData;
    }

    /**
     * Legt den Wert der metaData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MdsDescriptor.MetaData }
     *     
     */
    public void setMetaData(MdsDescriptor.MetaData value) {
        this.metaData = value;
    }

    /**
     * Ruft den Wert der systemContext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SystemContextDescriptor }
     *     
     */
    public SystemContextDescriptor getSystemContext() {
        return systemContext;
    }

    /**
     * Legt den Wert der systemContext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemContextDescriptor }
     *     
     */
    public void setSystemContext(SystemContextDescriptor value) {
        this.systemContext = value;
    }

    /**
     * Ruft den Wert der clock-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ClockDescriptor }
     *     
     */
    public ClockDescriptor getClock() {
        return clock;
    }

    /**
     * Legt den Wert der clock-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ClockDescriptor }
     *     
     */
    public void setClock(ClockDescriptor value) {
        this.clock = value;
    }

    /**
     * Gets the value of the battery property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the battery property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBattery().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatteryDescriptor }
     * 
     * 
     */
    public List<BatteryDescriptor> getBattery() {
        if (battery == null) {
            battery = new ArrayList<BatteryDescriptor>();
        }
        return this.battery;
    }

    /**
     * Ruft den Wert der approvedJurisdictions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApprovedJurisdictions }
     *     
     */
    public ApprovedJurisdictions getApprovedJurisdictions() {
        return approvedJurisdictions;
    }

    /**
     * Legt den Wert der approvedJurisdictions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovedJurisdictions }
     *     
     */
    public void setApprovedJurisdictions(ApprovedJurisdictions value) {
        this.approvedJurisdictions = value;
    }

    /**
     * Gets the value of the vmd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vmd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVmd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VmdDescriptor }
     * 
     * 
     */
    public List<VmdDescriptor> getVmd() {
        if (vmd == null) {
            vmd = new ArrayList<VmdDescriptor>();
        }
        return this.vmd;
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
     *         &lt;element name="Udi" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
     *                   &lt;element name="DeviceIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="HumanReadableForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Issuer" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
     *                   &lt;element name="Jurisdiction" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="LotNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Manufacturer" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ManufactureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="ModelName" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ModelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "udi",
        "lotNumber",
        "manufacturer",
        "manufactureDate",
        "expirationDate",
        "modelName",
        "modelNumber",
        "serialNumber"
    })
    public static class MetaData {

        @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
        protected ExtensionType extension;
        @XmlElement(name = "Udi")
        protected List<MdsDescriptor.MetaData.Udi> udi;
        @XmlElement(name = "LotNumber")
        protected String lotNumber;
        @XmlElement(name = "Manufacturer")
        protected List<LocalizedText> manufacturer;
        @XmlElement(name = "ManufactureDate")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar manufactureDate;
        @XmlElement(name = "ExpirationDate")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar expirationDate;
        @XmlElement(name = "ModelName")
        protected List<LocalizedText> modelName;
        @XmlElement(name = "ModelNumber")
        protected String modelNumber;
        @XmlElement(name = "SerialNumber")
        protected List<String> serialNumber;

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
         * Gets the value of the udi property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the udi property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUdi().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MdsDescriptor.MetaData.Udi }
         * 
         * 
         */
        public List<MdsDescriptor.MetaData.Udi> getUdi() {
            if (udi == null) {
                udi = new ArrayList<MdsDescriptor.MetaData.Udi>();
            }
            return this.udi;
        }

        /**
         * Ruft den Wert der lotNumber-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLotNumber() {
            return lotNumber;
        }

        /**
         * Legt den Wert der lotNumber-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLotNumber(String value) {
            this.lotNumber = value;
        }

        /**
         * Gets the value of the manufacturer property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the manufacturer property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getManufacturer().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LocalizedText }
         * 
         * 
         */
        public List<LocalizedText> getManufacturer() {
            if (manufacturer == null) {
                manufacturer = new ArrayList<LocalizedText>();
            }
            return this.manufacturer;
        }

        /**
         * Ruft den Wert der manufactureDate-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getManufactureDate() {
            return manufactureDate;
        }

        /**
         * Legt den Wert der manufactureDate-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setManufactureDate(XMLGregorianCalendar value) {
            this.manufactureDate = value;
        }

        /**
         * Ruft den Wert der expirationDate-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getExpirationDate() {
            return expirationDate;
        }

        /**
         * Legt den Wert der expirationDate-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setExpirationDate(XMLGregorianCalendar value) {
            this.expirationDate = value;
        }

        /**
         * Gets the value of the modelName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the modelName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getModelName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LocalizedText }
         * 
         * 
         */
        public List<LocalizedText> getModelName() {
            if (modelName == null) {
                modelName = new ArrayList<LocalizedText>();
            }
            return this.modelName;
        }

        /**
         * Ruft den Wert der modelNumber-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModelNumber() {
            return modelNumber;
        }

        /**
         * Legt den Wert der modelNumber-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModelNumber(String value) {
            this.modelNumber = value;
        }

        /**
         * Gets the value of the serialNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serialNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSerialNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getSerialNumber() {
            if (serialNumber == null) {
                serialNumber = new ArrayList<String>();
            }
            return this.serialNumber;
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
         *         &lt;element name="DeviceIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="HumanReadableForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Issuer" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
         *         &lt;element name="Jurisdiction" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
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
            "deviceIdentifier",
            "humanReadableForm",
            "issuer",
            "jurisdiction"
        })
        public static class Udi {

            @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
            protected ExtensionType extension;
            @XmlElement(name = "DeviceIdentifier", required = true)
            protected String deviceIdentifier;
            @XmlElement(name = "HumanReadableForm", required = true)
            protected String humanReadableForm;
            @XmlElement(name = "Issuer", required = true)
            protected InstanceIdentifier issuer;
            @XmlElement(name = "Jurisdiction")
            protected InstanceIdentifier jurisdiction;

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
             * Ruft den Wert der deviceIdentifier-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDeviceIdentifier() {
                return deviceIdentifier;
            }

            /**
             * Legt den Wert der deviceIdentifier-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDeviceIdentifier(String value) {
                this.deviceIdentifier = value;
            }

            /**
             * Ruft den Wert der humanReadableForm-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHumanReadableForm() {
                return humanReadableForm;
            }

            /**
             * Legt den Wert der humanReadableForm-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHumanReadableForm(String value) {
                this.humanReadableForm = value;
            }

            /**
             * Ruft den Wert der issuer-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link InstanceIdentifier }
             *     
             */
            public InstanceIdentifier getIssuer() {
                return issuer;
            }

            /**
             * Legt den Wert der issuer-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link InstanceIdentifier }
             *     
             */
            public void setIssuer(InstanceIdentifier value) {
                this.issuer = value;
            }

            /**
             * Ruft den Wert der jurisdiction-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link InstanceIdentifier }
             *     
             */
            public InstanceIdentifier getJurisdiction() {
                return jurisdiction;
            }

            /**
             * Legt den Wert der jurisdiction-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link InstanceIdentifier }
             *     
             */
            public void setJurisdiction(InstanceIdentifier value) {
                this.jurisdiction = value;
            }

        }

    }

}
