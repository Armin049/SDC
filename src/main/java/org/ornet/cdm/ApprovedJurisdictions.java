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
 * List of regions in which a DEVICE COMPONENT is approved to be operated. If the list does not contain any entries, then the DEVICE COMPONENT is not approved for any region.
 * 
 * <p>Java-Klasse für ApprovedJurisdictions complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ApprovedJurisdictions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApprovedJurisdiction" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApprovedJurisdictions", propOrder = {
    "approvedJurisdiction"
})
public class ApprovedJurisdictions {

    @XmlElement(name = "ApprovedJurisdiction")
    protected List<InstanceIdentifier> approvedJurisdiction;

    /**
     * Gets the value of the approvedJurisdiction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the approvedJurisdiction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApprovedJurisdiction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceIdentifier }
     * 
     * 
     */
    public List<InstanceIdentifier> getApprovedJurisdiction() {
        if (approvedJurisdiction == null) {
            approvedJurisdiction = new ArrayList<InstanceIdentifier>();
        }
        return this.approvedJurisdiction;
    }

}
