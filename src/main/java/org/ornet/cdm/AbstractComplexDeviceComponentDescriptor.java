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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * AbstractComplexDeviceComponentDescriptor adds an OPTIONAL pm:AlertSystemDescriptor and pm:ScoDescriptor to pm:AbstractDeviceComponentDescriptor.
 * 
 * <p>Java-Klasse für AbstractComplexDeviceComponentDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractComplexDeviceComponentDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentDescriptor">
 *       &lt;sequence>
 *         &lt;element name="AlertSystem" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertSystemDescriptor" minOccurs="0"/>
 *         &lt;element name="Sco" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ScoDescriptor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractComplexDeviceComponentDescriptor", propOrder = {
    "alertSystem",
    "sco"
})
@XmlSeeAlso({
    VmdDescriptor.class,
    MdsDescriptor.class
})
public class AbstractComplexDeviceComponentDescriptor
    extends AbstractDeviceComponentDescriptor
{

    @XmlElement(name = "AlertSystem")
    protected AlertSystemDescriptor alertSystem;
    @XmlElement(name = "Sco")
    protected ScoDescriptor sco;

    /**
     * Ruft den Wert der alertSystem-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertSystemDescriptor }
     *     
     */
    public AlertSystemDescriptor getAlertSystem() {
        return alertSystem;
    }

    /**
     * Legt den Wert der alertSystem-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertSystemDescriptor }
     *     
     */
    public void setAlertSystem(AlertSystemDescriptor value) {
        this.alertSystem = value;
    }

    /**
     * Ruft den Wert der sco-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ScoDescriptor }
     *     
     */
    public ScoDescriptor getSco() {
        return sco;
    }

    /**
     * Legt den Wert der sco-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ScoDescriptor }
     *     
     */
    public void setSco(ScoDescriptor value) {
        this.sco = value;
    }

}
