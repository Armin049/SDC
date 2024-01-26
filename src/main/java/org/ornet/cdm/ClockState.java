//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * State of a clock of an MDS.
 * 
 * <p>Java-Klasse für ClockState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ClockState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentState">
 *       &lt;sequence>
 *         &lt;element name="ActiveSyncProtocol" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *         &lt;element name="ReferenceSource" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DateAndTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *       &lt;attribute name="RemoteSync" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Accuracy" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="LastSet" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *       &lt;attribute name="TimeZone" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}TimeZone" />
 *       &lt;attribute name="CriticalUse" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClockState", propOrder = {
    "activeSyncProtocol",
    "referenceSource"
})
public class ClockState
    extends AbstractDeviceComponentState
{

    @XmlElement(name = "ActiveSyncProtocol")
    protected CodedValue activeSyncProtocol;
    @XmlElement(name = "ReferenceSource")
    protected List<String> referenceSource;
    @XmlAttribute(name = "DateAndTime")
    protected Long dateAndTime;
    @XmlAttribute(name = "RemoteSync", required = true)
    protected boolean remoteSync;
    @XmlAttribute(name = "Accuracy")
    protected BigDecimal accuracy;
    @XmlAttribute(name = "LastSet")
    protected Long lastSet;
    @XmlAttribute(name = "TimeZone")
    protected String timeZone;
    @XmlAttribute(name = "CriticalUse")
    protected Boolean criticalUse;

    /**
     * Ruft den Wert der activeSyncProtocol-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getActiveSyncProtocol() {
        return activeSyncProtocol;
    }

    /**
     * Legt den Wert der activeSyncProtocol-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setActiveSyncProtocol(CodedValue value) {
        this.activeSyncProtocol = value;
    }

    /**
     * Gets the value of the referenceSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getReferenceSource() {
        if (referenceSource == null) {
            referenceSource = new ArrayList<String>();
        }
        return this.referenceSource;
    }

    /**
     * Ruft den Wert der dateAndTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Legt den Wert der dateAndTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDateAndTime(Long value) {
        this.dateAndTime = value;
    }

    /**
     * Ruft den Wert der remoteSync-Eigenschaft ab.
     * 
     */
    public boolean isRemoteSync() {
        return remoteSync;
    }

    /**
     * Legt den Wert der remoteSync-Eigenschaft fest.
     * 
     */
    public void setRemoteSync(boolean value) {
        this.remoteSync = value;
    }

    /**
     * Ruft den Wert der accuracy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAccuracy() {
        return accuracy;
    }

    /**
     * Legt den Wert der accuracy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAccuracy(BigDecimal value) {
        this.accuracy = value;
    }

    /**
     * Ruft den Wert der lastSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLastSet() {
        return lastSet;
    }

    /**
     * Legt den Wert der lastSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLastSet(Long value) {
        this.lastSet = value;
    }

    /**
     * Ruft den Wert der timeZone-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Legt den Wert der timeZone-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    /**
     * Ruft den Wert der criticalUse-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCriticalUse() {
        return criticalUse;
    }

    /**
     * Legt den Wert der criticalUse-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCriticalUse(Boolean value) {
        this.criticalUse = value;
    }

}
