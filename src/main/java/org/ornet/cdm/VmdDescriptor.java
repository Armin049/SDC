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
import javax.xml.bind.annotation.XmlType;


/**
 * VmdDescriptor describes a VMD. A VMD is an abstraction for a module (medical-related subsystem) of an MDS. According to IEEE 11073-10201, an MDS with one VMD is a single purpose POC MEDICAL DEVICE in contrast to an MDS with multiple VMDs that has multiple purposes.
 * 
 * Example of a multiple purpose POC MEDICAL DEVICE: an anesthesia workstation (one MDS) with a ventilation unit (one VMD), a patient monitoring unit (another VMD), and gas delivery/monitor system (another VMD). In the IEEE 11073-10201 a VMD might not be a hardware module, it also can be pure software.
 * 
 * <p>Java-Klasse für VmdDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="VmdDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractComplexDeviceComponentDescriptor">
 *       &lt;sequence>
 *         &lt;element name="ApprovedJurisdictions" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ApprovedJurisdictions" minOccurs="0"/>
 *         &lt;element name="Channel" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ChannelDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VmdDescriptor", propOrder = {
    "approvedJurisdictions",
    "channel"
})
public class VmdDescriptor
    extends AbstractComplexDeviceComponentDescriptor
{

    @XmlElement(name = "ApprovedJurisdictions")
    protected ApprovedJurisdictions approvedJurisdictions;
    @XmlElement(name = "Channel")
    protected List<ChannelDescriptor> channel;

    /**
     * Ruft den Wert der approvedJurisdictions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApprovedJurisdictions }
     *     
     */
    public ApprovedJurisdictions getApprovedJurisdictions() {
        return approvedJurisdictions;
    }

    /**
     * Legt den Wert der approvedJurisdictions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovedJurisdictions }
     *     
     */
    public void setApprovedJurisdictions(ApprovedJurisdictions value) {
        this.approvedJurisdictions = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the channel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChannel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChannelDescriptor }
     * 
     * 
     */
    public List<ChannelDescriptor> getChannel() {
        if (channel == null) {
            channel = new ArrayList<ChannelDescriptor>();
        }
        return this.channel;
    }

}
