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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Definition of the state of an pm:MdsDescriptor.
 * 
 * <p>Java-Klasse für MdsState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="MdsState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractComplexDeviceComponentState">
 *       &lt;sequence>
 *         &lt;element name="OperatingJurisdiction" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperatingJurisdiction" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Lang" type="{http://www.w3.org/2001/XMLSchema}language" />
 *       &lt;attribute name="OperatingMode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MdsOperatingMode" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdsState", propOrder = {
    "operatingJurisdiction"
})
public class MdsState
    extends AbstractComplexDeviceComponentState
{

    @XmlElement(name = "OperatingJurisdiction")
    protected OperatingJurisdiction operatingJurisdiction;
    @XmlAttribute(name = "Lang")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;
    @XmlAttribute(name = "OperatingMode")
    protected MdsOperatingMode operatingMode;

    /**
     * Ruft den Wert der operatingJurisdiction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OperatingJurisdiction }
     *     
     */
    public OperatingJurisdiction getOperatingJurisdiction() {
        return operatingJurisdiction;
    }

    /**
     * Legt den Wert der operatingJurisdiction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatingJurisdiction }
     *     
     */
    public void setOperatingJurisdiction(OperatingJurisdiction value) {
        this.operatingJurisdiction = value;
    }

    /**
     * Ruft den Wert der lang-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Legt den Wert der lang-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Ruft den Wert der operatingMode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MdsOperatingMode }
     *     
     */
    public MdsOperatingMode getOperatingMode() {
        return operatingMode;
    }

    /**
     * Legt den Wert der operatingMode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MdsOperatingMode }
     *     
     */
    public void setOperatingMode(MdsOperatingMode value) {
        this.operatingMode = value;
    }

}
