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
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractGetResponse">
 *       &lt;sequence>
 *         &lt;element name="Mdib" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Mdib"/>
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
    "mdib"
})
@XmlRootElement(name = "GetMdibResponse", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class GetMdibResponse
    extends AbstractGetResponse
{

    @XmlElement(name = "Mdib", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
    protected Mdib mdib;

    /**
     * Ruft den Wert der mdib-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Mdib }
     *     
     */
    public Mdib getMdib() {
        return mdib;
    }

    /**
     * Legt den Wert der mdib-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Mdib }
     *     
     */
    public void setMdib(Mdib value) {
        this.mdib = value;
    }

}
