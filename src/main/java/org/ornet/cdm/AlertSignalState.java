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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * AlertSignalState contains the dynamic/volatile information of an ALERT SIGNAL. See pm:AlertSignalDescriptor for static information.
 * 
 * <p>Java-Klasse für AlertSignalState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AlertSignalState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractAlertState">
 *       &lt;attribute name="ActualSignalGenerationDelay" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="Presence" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertSignalPresence" />
 *       &lt;attribute name="Location" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertSignalPrimaryLocation" />
 *       &lt;attribute name="Slot" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlertSignalState")
public class AlertSignalState
    extends AbstractAlertState
{

    @XmlAttribute(name = "ActualSignalGenerationDelay")
    protected Duration actualSignalGenerationDelay;
    @XmlAttribute(name = "Presence")
    protected AlertSignalPresence presence;
    @XmlAttribute(name = "Location")
    protected AlertSignalPrimaryLocation location;
    @XmlAttribute(name = "Slot")
    @XmlSchemaType(name = "unsignedInt")
    protected Long slot;

    /**
     * Ruft den Wert der actualSignalGenerationDelay-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getActualSignalGenerationDelay() {
        return actualSignalGenerationDelay;
    }

    /**
     * Legt den Wert der actualSignalGenerationDelay-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setActualSignalGenerationDelay(Duration value) {
        this.actualSignalGenerationDelay = value;
    }

    /**
     * Ruft den Wert der presence-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertSignalPresence }
     *     
     */
    public AlertSignalPresence getPresence() {
        return presence;
    }

    /**
     * Legt den Wert der presence-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertSignalPresence }
     *     
     */
    public void setPresence(AlertSignalPresence value) {
        this.presence = value;
    }

    /**
     * Ruft den Wert der location-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertSignalPrimaryLocation }
     *     
     */
    public AlertSignalPrimaryLocation getLocation() {
        return location;
    }

    /**
     * Legt den Wert der location-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertSignalPrimaryLocation }
     *     
     */
    public void setLocation(AlertSignalPrimaryLocation value) {
        this.location = value;
    }

    /**
     * Ruft den Wert der slot-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSlot() {
        return slot;
    }

    /**
     * Legt den Wert der slot-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSlot(Long value) {
        this.slot = value;
    }

}
