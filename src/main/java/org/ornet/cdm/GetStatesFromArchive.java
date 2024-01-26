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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractGet">
 *       &lt;sequence>
 *         &lt;element name="StateRevisions" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}VersionFrame" minOccurs="0"/>
 *         &lt;element name="TimeFrame" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}TimeFrame" minOccurs="0"/>
 *         &lt;element name="Handle" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stateRevisions",
    "timeFrame",
    "handle"
})
@XmlRootElement(name = "GetStatesFromArchive", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class GetStatesFromArchive
    extends AbstractGet
{

    @XmlElement(name = "StateRevisions", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    protected VersionFrame stateRevisions;
    @XmlElement(name = "TimeFrame", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    protected TimeFrame timeFrame;
    @XmlElement(name = "Handle", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    protected List<String> handle;

    /**
     * Ruft den Wert der stateRevisions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VersionFrame }
     *     
     */
    public VersionFrame getStateRevisions() {
        return stateRevisions;
    }

    /**
     * Legt den Wert der stateRevisions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionFrame }
     *     
     */
    public void setStateRevisions(VersionFrame value) {
        this.stateRevisions = value;
    }

    /**
     * Ruft den Wert der timeFrame-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TimeFrame }
     *     
     */
    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    /**
     * Legt den Wert der timeFrame-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeFrame }
     *     
     */
    public void setTimeFrame(TimeFrame value) {
        this.timeFrame = value;
    }

    /**
     * Gets the value of the handle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the handle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHandle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getHandle() {
        if (handle == null) {
            handle = new ArrayList<String>();
        }
        return this.handle;
    }

}
