//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * State of a battery of an MDS.
 * 
 * The current power source is designated by ./@ActivationState:
 * 
 * - If ./@ActivationState equals "On", the device is running on battery
 * - If ./@ActivationState equals "Off", the device is in mains operation and currently not able to be run on battery
 * - If ./@ActivationState equals "StndBy", the device is in mains operation and can be switched to run on battery
 * - If ./@ActivationState equals "Fail", the battery has a malfunction. Detailed error information SHOULD be communicated by using an ALERT SYSTEM.
 * 
 * Enumerations "Shtdn" and "NotRdy" are undefined for BatteryState.
 * 
 * <p>Java-Klasse für BatteryState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="BatteryState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentState">
 *       &lt;sequence>
 *         &lt;element name="CapacityRemaining" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="Voltage" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="Current" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="Temperature" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="RemainingBatteryTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ChargeStatus">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Ful"/>
 *             &lt;enumeration value="ChB"/>
 *             &lt;enumeration value="DisChB"/>
 *             &lt;enumeration value="DEB"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="ChargeCycles" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatteryState", propOrder = {
    "capacityRemaining",
    "voltage",
    "current",
    "temperature",
    "remainingBatteryTime"
})
public class BatteryState
    extends AbstractDeviceComponentState
{

    @XmlElement(name = "CapacityRemaining")
    protected Measurement capacityRemaining;
    @XmlElement(name = "Voltage")
    protected Measurement voltage;
    @XmlElement(name = "Current")
    protected Measurement current;
    @XmlElement(name = "Temperature")
    protected Measurement temperature;
    @XmlElement(name = "RemainingBatteryTime")
    protected Measurement remainingBatteryTime;
    @XmlAttribute(name = "ChargeStatus")
    protected String chargeStatus;
    @XmlAttribute(name = "ChargeCycles")
    @XmlSchemaType(name = "unsignedInt")
    protected Long chargeCycles;

    /**
     * Ruft den Wert der capacityRemaining-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getCapacityRemaining() {
        return capacityRemaining;
    }

    /**
     * Legt den Wert der capacityRemaining-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setCapacityRemaining(Measurement value) {
        this.capacityRemaining = value;
    }

    /**
     * Ruft den Wert der voltage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getVoltage() {
        return voltage;
    }

    /**
     * Legt den Wert der voltage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setVoltage(Measurement value) {
        this.voltage = value;
    }

    /**
     * Ruft den Wert der current-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getCurrent() {
        return current;
    }

    /**
     * Legt den Wert der current-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setCurrent(Measurement value) {
        this.current = value;
    }

    /**
     * Ruft den Wert der temperature-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getTemperature() {
        return temperature;
    }

    /**
     * Legt den Wert der temperature-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setTemperature(Measurement value) {
        this.temperature = value;
    }

    /**
     * Ruft den Wert der remainingBatteryTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getRemainingBatteryTime() {
        return remainingBatteryTime;
    }

    /**
     * Legt den Wert der remainingBatteryTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setRemainingBatteryTime(Measurement value) {
        this.remainingBatteryTime = value;
    }

    /**
     * Ruft den Wert der chargeStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeStatus() {
        return chargeStatus;
    }

    /**
     * Legt den Wert der chargeStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeStatus(String value) {
        this.chargeStatus = value;
    }

    /**
     * Ruft den Wert der chargeCycles-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getChargeCycles() {
        return chargeCycles;
    }

    /**
     * Legt den Wert der chargeCycles-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setChargeCycles(Long value) {
        this.chargeCycles = value;
    }

}
