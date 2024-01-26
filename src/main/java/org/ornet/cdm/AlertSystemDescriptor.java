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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * AlertSystemDescriptor describes an ALERT SYSTEM to detect ALERT CONDITIONs and generate ALERT SIGNALs, which belong to specific ALERT CONDITIONs.
 * 
 * ALERT CONDITIONs are represented by a list of pm:AlertConditionDescriptor ELEMENTs and ALERT SIGNALs are represented by a list of pm:AlertSignalDescriptor ELEMENTs.
 * 
 * <p>Java-Klasse für AlertSystemDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AlertSystemDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractAlertDescriptor">
 *       &lt;sequence>
 *         &lt;element name="AlertCondition" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AlertSignal" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertSignalDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MaxPhysiologicalParallelAlarms" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="MaxTechnicalParallelAlarms" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="SelfCheckPeriod" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertSystemDescriptor", propOrder = {
    "alertCondition",
    "alertSignal"
})
public class AlertSystemDescriptor
    extends AbstractAlertDescriptor
{

    @XmlElement(name = "AlertCondition")
    protected List<AlertConditionDescriptor> alertCondition;
    @XmlElement(name = "AlertSignal")
    protected List<AlertSignalDescriptor> alertSignal;
    @XmlAttribute(name = "MaxPhysiologicalParallelAlarms")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxPhysiologicalParallelAlarms;
    @XmlAttribute(name = "MaxTechnicalParallelAlarms")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxTechnicalParallelAlarms;
    @XmlAttribute(name = "SelfCheckPeriod")
    protected Duration selfCheckPeriod;

    /**
     * Gets the value of the alertCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alertCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlertCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlertConditionDescriptor }
     * 
     * 
     */
    public List<AlertConditionDescriptor> getAlertCondition() {
        if (alertCondition == null) {
            alertCondition = new ArrayList<AlertConditionDescriptor>();
        }
        return this.alertCondition;
    }

    /**
     * Gets the value of the alertSignal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alertSignal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlertSignal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlertSignalDescriptor }
     * 
     * 
     */
    public List<AlertSignalDescriptor> getAlertSignal() {
        if (alertSignal == null) {
            alertSignal = new ArrayList<AlertSignalDescriptor>();
        }
        return this.alertSignal;
    }

    /**
     * Ruft den Wert der maxPhysiologicalParallelAlarms-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxPhysiologicalParallelAlarms() {
        return maxPhysiologicalParallelAlarms;
    }

    /**
     * Legt den Wert der maxPhysiologicalParallelAlarms-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxPhysiologicalParallelAlarms(Long value) {
        this.maxPhysiologicalParallelAlarms = value;
    }

    /**
     * Ruft den Wert der maxTechnicalParallelAlarms-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxTechnicalParallelAlarms() {
        return maxTechnicalParallelAlarms;
    }

    /**
     * Legt den Wert der maxTechnicalParallelAlarms-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxTechnicalParallelAlarms(Long value) {
        this.maxTechnicalParallelAlarms = value;
    }

    /**
     * Ruft den Wert der selfCheckPeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getSelfCheckPeriod() {
        return selfCheckPeriod;
    }

    /**
     * Legt den Wert der selfCheckPeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setSelfCheckPeriod(Duration value) {
        this.selfCheckPeriod = value;
    }

}
