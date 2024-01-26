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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * An ALERT CONDITION contains the information about a potentially or actually HAZARDOUS SITUATION. 
 * 
 * Examples: a physiological alarm limit has been exceeded or a sensor has been unplugged.
 * 
 * <p>Java-Klasse für AlertConditionDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AlertConditionDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractAlertDescriptor">
 *       &lt;sequence>
 *         &lt;element name="Source" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CauseInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CauseInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Kind" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionKind" />
 *       &lt;attribute name="Priority" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionPriority" />
 *       &lt;attribute name="DefaultConditionGenerationDelay" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="CanEscalate">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionPriority">
 *             &lt;enumeration value="Lo"/>
 *             &lt;enumeration value="Me"/>
 *             &lt;enumeration value="Hi"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="CanDeescalate">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionPriority">
 *             &lt;enumeration value="Me"/>
 *             &lt;enumeration value="Lo"/>
 *             &lt;enumeration value="None"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertConditionDescriptor", propOrder = {
    "source",
    "causeInfo"
})
@XmlSeeAlso({
    LimitAlertConditionDescriptor.class
})
public class AlertConditionDescriptor
    extends AbstractAlertDescriptor
{

    @XmlElement(name = "Source")
    protected List<String> source;
    @XmlElement(name = "CauseInfo")
    protected List<CauseInfo> causeInfo;
    @XmlAttribute(name = "Kind", required = true)
    protected AlertConditionKind kind;
    @XmlAttribute(name = "Priority", required = true)
    protected AlertConditionPriority priority;
    @XmlAttribute(name = "DefaultConditionGenerationDelay")
    protected Duration defaultConditionGenerationDelay;
    @XmlAttribute(name = "CanEscalate")
    protected AlertConditionPriority canEscalate;
    @XmlAttribute(name = "CanDeescalate")
    protected AlertConditionPriority canDeescalate;

    /**
     * Gets the value of the source property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the source property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSource() {
        if (source == null) {
            source = new ArrayList<String>();
        }
        return this.source;
    }

    /**
     * Gets the value of the causeInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the causeInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCauseInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CauseInfo }
     * 
     * 
     */
    public List<CauseInfo> getCauseInfo() {
        if (causeInfo == null) {
            causeInfo = new ArrayList<CauseInfo>();
        }
        return this.causeInfo;
    }

    /**
     * Ruft den Wert der kind-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertConditionKind }
     *     
     */
    public AlertConditionKind getKind() {
        return kind;
    }

    /**
     * Legt den Wert der kind-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertConditionKind }
     *     
     */
    public void setKind(AlertConditionKind value) {
        this.kind = value;
    }

    /**
     * Ruft den Wert der priority-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertConditionPriority }
     *     
     */
    public AlertConditionPriority getPriority() {
        return priority;
    }

    /**
     * Legt den Wert der priority-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertConditionPriority }
     *     
     */
    public void setPriority(AlertConditionPriority value) {
        this.priority = value;
    }

    /**
     * Ruft den Wert der defaultConditionGenerationDelay-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDefaultConditionGenerationDelay() {
        return defaultConditionGenerationDelay;
    }

    /**
     * Legt den Wert der defaultConditionGenerationDelay-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDefaultConditionGenerationDelay(Duration value) {
        this.defaultConditionGenerationDelay = value;
    }

    /**
     * Ruft den Wert der canEscalate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertConditionPriority }
     *     
     */
    public AlertConditionPriority getCanEscalate() {
        return canEscalate;
    }

    /**
     * Legt den Wert der canEscalate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertConditionPriority }
     *     
     */
    public void setCanEscalate(AlertConditionPriority value) {
        this.canEscalate = value;
    }

    /**
     * Ruft den Wert der canDeescalate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertConditionPriority }
     *     
     */
    public AlertConditionPriority getCanDeescalate() {
        return canDeescalate;
    }

    /**
     * Legt den Wert der canDeescalate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertConditionPriority }
     *     
     */
    public void setCanDeescalate(AlertConditionPriority value) {
        this.canDeescalate = value;
    }

}
