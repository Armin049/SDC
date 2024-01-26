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
import javax.xml.bind.annotation.XmlType;


/**
 * A state of a limit ALERT CONDITION.
 * 
 * <p>Java-Klasse für LimitAlertConditionState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="LimitAlertConditionState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionState">
 *       &lt;sequence>
 *         &lt;element name="Limits" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Range"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MonitoredAlertLimits" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionMonitoredLimits" />
 *       &lt;attribute name="AutoLimitActivationState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertActivation" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitAlertConditionState", propOrder = {
    "limits"
})
public class LimitAlertConditionState
    extends AlertConditionState
{

    @XmlElement(name = "Limits", required = true)
    protected Range limits;
    @XmlAttribute(name = "MonitoredAlertLimits", required = true)
    protected AlertConditionMonitoredLimits monitoredAlertLimits;
    @XmlAttribute(name = "AutoLimitActivationState")
    protected AlertActivation autoLimitActivationState;

    /**
     * Ruft den Wert der limits-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Range }
     *     
     */
    public Range getLimits() {
        return limits;
    }

    /**
     * Legt den Wert der limits-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Range }
     *     
     */
    public void setLimits(Range value) {
        this.limits = value;
    }

    /**
     * Ruft den Wert der monitoredAlertLimits-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertConditionMonitoredLimits }
     *     
     */
    public AlertConditionMonitoredLimits getMonitoredAlertLimits() {
        return monitoredAlertLimits;
    }

    /**
     * Legt den Wert der monitoredAlertLimits-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertConditionMonitoredLimits }
     *     
     */
    public void setMonitoredAlertLimits(AlertConditionMonitoredLimits value) {
        this.monitoredAlertLimits = value;
    }

    /**
     * Ruft den Wert der autoLimitActivationState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertActivation }
     *     
     */
    public AlertActivation getAutoLimitActivationState() {
        return autoLimitActivationState;
    }

    /**
     * Legt den Wert der autoLimitActivationState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertActivation }
     *     
     */
    public void setAutoLimitActivationState(AlertActivation value) {
        this.autoLimitActivationState = value;
    }

}
