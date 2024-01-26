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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * AlertConditionState contains the dynamic/volatile information of an ALERT CONDITION. See pm:AlertConditionDescriptor for static information.
 * 
 * <p>Java-Klasse für AlertConditionState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AlertConditionState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractAlertState">
 *       &lt;attribute name="ActualConditionGenerationDelay" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="ActualPriority" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertConditionPriority" />
 *       &lt;attribute name="Rank" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Presence" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DeterminationTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertConditionState")
@XmlSeeAlso({
    LimitAlertConditionState.class
})
public class AlertConditionState
    extends AbstractAlertState
{

    @XmlAttribute(name = "ActualConditionGenerationDelay")
    protected Duration actualConditionGenerationDelay;
    @XmlAttribute(name = "ActualPriority")
    protected AlertConditionPriority actualPriority;
    @XmlAttribute(name = "Rank")
    protected Integer rank;
    @XmlAttribute(name = "Presence")
    protected Boolean presence;
    @XmlAttribute(name = "DeterminationTime")
    protected Long determinationTime;

    /**
     * Ruft den Wert der actualConditionGenerationDelay-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getActualConditionGenerationDelay() {
        return actualConditionGenerationDelay;
    }

    /**
     * Legt den Wert der actualConditionGenerationDelay-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setActualConditionGenerationDelay(Duration value) {
        this.actualConditionGenerationDelay = value;
    }

    /**
     * Ruft den Wert der actualPriority-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertConditionPriority }
     *     
     */
    public AlertConditionPriority getActualPriority() {
        return actualPriority;
    }

    /**
     * Legt den Wert der actualPriority-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertConditionPriority }
     *     
     */
    public void setActualPriority(AlertConditionPriority value) {
        this.actualPriority = value;
    }

    /**
     * Ruft den Wert der rank-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Legt den Wert der rank-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRank(Integer value) {
        this.rank = value;
    }

    /**
     * Ruft den Wert der presence-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPresence() {
        return presence;
    }

    /**
     * Legt den Wert der presence-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPresence(Boolean value) {
        this.presence = value;
    }

    /**
     * Ruft den Wert der determinationTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDeterminationTime() {
        return determinationTime;
    }

    /**
     * Legt den Wert der determinationTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDeterminationTime(Long value) {
        this.determinationTime = value;
    }

}
