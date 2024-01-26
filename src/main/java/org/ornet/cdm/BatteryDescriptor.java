//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * For battery-powered devices, battery information can be contained in this object.
 * 
 * <p>Java-Klasse für BatteryDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="BatteryDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentDescriptor">
 *       &lt;sequence>
 *         &lt;element name="CapacityFullCharge" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="CapacitySpecified" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="VoltageSpecified" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatteryDescriptor", propOrder = {
    "capacityFullCharge",
    "capacitySpecified",
    "voltageSpecified"
})
public class BatteryDescriptor
    extends AbstractDeviceComponentDescriptor
{

    @XmlElement(name = "CapacityFullCharge")
    protected Measurement capacityFullCharge;
    @XmlElement(name = "CapacitySpecified")
    protected Measurement capacitySpecified;
    @XmlElement(name = "VoltageSpecified")
    protected Measurement voltageSpecified;

    /**
     * Ruft den Wert der capacityFullCharge-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getCapacityFullCharge() {
        return capacityFullCharge;
    }

    /**
     * Legt den Wert der capacityFullCharge-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setCapacityFullCharge(Measurement value) {
        this.capacityFullCharge = value;
    }

    /**
     * Ruft den Wert der capacitySpecified-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getCapacitySpecified() {
        return capacitySpecified;
    }

    /**
     * Legt den Wert der capacitySpecified-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setCapacitySpecified(Measurement value) {
        this.capacitySpecified = value;
    }

    /**
     * Ruft den Wert der voltageSpecified-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getVoltageSpecified() {
        return voltageSpecified;
    }

    /**
     * Legt den Wert der voltageSpecified-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setVoltageSpecified(Measurement value) {
        this.voltageSpecified = value;
    }

}
