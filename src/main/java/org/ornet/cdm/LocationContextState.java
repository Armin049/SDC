//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A context state that identifies a location in a hospital.
 * 
 * <p>Java-Klasse für LocationContextState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="LocationContextState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractContextState">
 *       &lt;sequence>
 *         &lt;element name="LocationDetail" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocationDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationContextState", propOrder = {
    "locationDetail"
})
public class LocationContextState
    extends AbstractContextState
{

    @XmlElement(name = "LocationDetail")
    protected LocationDetail locationDetail;

    /**
     * Ruft den Wert der locationDetail-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LocationDetail }
     *     
     */
    public LocationDetail getLocationDetail() {
        return locationDetail;
    }

    /**
     * Legt den Wert der locationDetail-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationDetail }
     *     
     */
    public void setLocationDetail(LocationDetail value) {
        this.locationDetail = value;
    }

}
