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
 * AlertSystemState contains the dynamic/volatile information of an ALERT SYSTEM. See pm:AlertSystemDescriptor for static information.
 * 
 * <p>Java-Klasse für AlertSystemState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AlertSystemState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractAlertState">
 *       &lt;sequence>
 *         &lt;element name="SystemSignalActivation" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}SystemSignalActivation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="LastSelfCheck" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *       &lt;attribute name="SelfCheckCount" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="PresentPhysiologicalAlarmConditions" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionReference" />
 *       &lt;attribute name="PresentTechnicalAlarmConditions" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionReference" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertSystemState", propOrder = {
    "systemSignalActivation"
})
public class AlertSystemState
    extends AbstractAlertState
{

    @XmlElement(name = "SystemSignalActivation")
    protected List<SystemSignalActivation> systemSignalActivation;
    @XmlAttribute(name = "LastSelfCheck")
    protected Long lastSelfCheck;
    @XmlAttribute(name = "SelfCheckCount")
    protected Long selfCheckCount;
    @XmlAttribute(name = "PresentPhysiologicalAlarmConditions")
    protected List<String> presentPhysiologicalAlarmConditions;
    @XmlAttribute(name = "PresentTechnicalAlarmConditions")
    protected List<String> presentTechnicalAlarmConditions;

    /**
     * Gets the value of the systemSignalActivation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the systemSignalActivation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSystemSignalActivation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SystemSignalActivation }
     * 
     * 
     */
    public List<SystemSignalActivation> getSystemSignalActivation() {
        if (systemSignalActivation == null) {
            systemSignalActivation = new ArrayList<SystemSignalActivation>();
        }
        return this.systemSignalActivation;
    }

    /**
     * Ruft den Wert der lastSelfCheck-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLastSelfCheck() {
        return lastSelfCheck;
    }

    /**
     * Legt den Wert der lastSelfCheck-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLastSelfCheck(Long value) {
        this.lastSelfCheck = value;
    }

    /**
     * Ruft den Wert der selfCheckCount-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSelfCheckCount() {
        return selfCheckCount;
    }

    /**
     * Legt den Wert der selfCheckCount-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSelfCheckCount(Long value) {
        this.selfCheckCount = value;
    }

    /**
     * Gets the value of the presentPhysiologicalAlarmConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the presentPhysiologicalAlarmConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPresentPhysiologicalAlarmConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPresentPhysiologicalAlarmConditions() {
        if (presentPhysiologicalAlarmConditions == null) {
            presentPhysiologicalAlarmConditions = new ArrayList<String>();
        }
        return this.presentPhysiologicalAlarmConditions;
    }

    /**
     * Gets the value of the presentTechnicalAlarmConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the presentTechnicalAlarmConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPresentTechnicalAlarmConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPresentTechnicalAlarmConditions() {
        if (presentTechnicalAlarmConditions == null) {
            presentTechnicalAlarmConditions = new ArrayList<String>();
        }
        return this.presentTechnicalAlarmConditions;
    }

}
