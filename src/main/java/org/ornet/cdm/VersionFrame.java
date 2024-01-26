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
import javax.xml.bind.annotation.XmlType;


/**
 * VersionFrame constitutes a version frame by defining ./@Start and ./@End, whereby ./@Start is REQUIRED to be lesser than or equal to ./@End.
 * 
 * <p>Java-Klasse für VersionFrame complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="VersionFrame">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Start" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ReferencedVersion" />
 *       &lt;attribute name="End" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ReferencedVersion" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VersionFrame", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class VersionFrame {

    @XmlAttribute(name = "Start")
    protected Long start;
    @XmlAttribute(name = "End")
    protected Long end;

    /**
     * Ruft den Wert der start-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStart() {
        return start;
    }

    /**
     * Legt den Wert der start-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStart(Long value) {
        this.start = value;
    }

    /**
     * Ruft den Wert der end-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEnd() {
        return end;
    }

    /**
     * Legt den Wert der end-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEnd(Long value) {
        this.end = value;
    }

}
