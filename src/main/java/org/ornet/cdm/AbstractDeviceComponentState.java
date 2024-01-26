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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * State of a component that is part of an MDS.
 * 
 * <p>Java-Klasse für AbstractDeviceComponentState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractDeviceComponentState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState">
 *       &lt;sequence>
 *         &lt;element name="CalibrationInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CalibrationInfo" minOccurs="0"/>
 *         &lt;element name="NextCalibration" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CalibrationInfo" minOccurs="0"/>
 *         &lt;element name="PhysicalConnector" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PhysicalConnectorInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ActivationState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ComponentActivation" />
 *       &lt;attribute name="OperatingHours" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="OperatingCycles" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractDeviceComponentState", propOrder = {
    "calibrationInfo",
    "nextCalibration",
    "physicalConnector"
})
@XmlSeeAlso({
    ChannelState.class,
    ClockState.class,
    SystemContextState.class,
    ScoState.class,
    BatteryState.class,
    AbstractComplexDeviceComponentState.class
})
public class AbstractDeviceComponentState
    extends AbstractState
{

    @XmlElement(name = "CalibrationInfo")
    protected CalibrationInfo calibrationInfo;
    @XmlElement(name = "NextCalibration")
    protected CalibrationInfo nextCalibration;
    @XmlElement(name = "PhysicalConnector")
    protected PhysicalConnectorInfo physicalConnector;
    @XmlAttribute(name = "ActivationState")
    protected ComponentActivation activationState;
    @XmlAttribute(name = "OperatingHours")
    @XmlSchemaType(name = "unsignedInt")
    protected Long operatingHours;
    @XmlAttribute(name = "OperatingCycles")
    protected Integer operatingCycles;

    /**
     * Ruft den Wert der calibrationInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationInfo }
     *     
     */
    public CalibrationInfo getCalibrationInfo() {
        return calibrationInfo;
    }

    /**
     * Legt den Wert der calibrationInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationInfo }
     *     
     */
    public void setCalibrationInfo(CalibrationInfo value) {
        this.calibrationInfo = value;
    }

    /**
     * Ruft den Wert der nextCalibration-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationInfo }
     *     
     */
    public CalibrationInfo getNextCalibration() {
        return nextCalibration;
    }

    /**
     * Legt den Wert der nextCalibration-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationInfo }
     *     
     */
    public void setNextCalibration(CalibrationInfo value) {
        this.nextCalibration = value;
    }

    /**
     * Ruft den Wert der physicalConnector-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PhysicalConnectorInfo }
     *     
     */
    public PhysicalConnectorInfo getPhysicalConnector() {
        return physicalConnector;
    }

    /**
     * Legt den Wert der physicalConnector-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PhysicalConnectorInfo }
     *     
     */
    public void setPhysicalConnector(PhysicalConnectorInfo value) {
        this.physicalConnector = value;
    }

    /**
     * Ruft den Wert der activationState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ComponentActivation }
     *     
     */
    public ComponentActivation getActivationState() {
        return activationState;
    }

    /**
     * Legt den Wert der activationState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponentActivation }
     *     
     */
    public void setActivationState(ComponentActivation value) {
        this.activationState = value;
    }

    /**
     * Ruft den Wert der operatingHours-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOperatingHours() {
        return operatingHours;
    }

    /**
     * Legt den Wert der operatingHours-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOperatingHours(Long value) {
        this.operatingHours = value;
    }

    /**
     * Ruft den Wert der operatingCycles-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOperatingCycles() {
        return operatingCycles;
    }

    /**
     * Legt den Wert der operatingCycles-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOperatingCycles(Integer value) {
        this.operatingCycles = value;
    }

}
