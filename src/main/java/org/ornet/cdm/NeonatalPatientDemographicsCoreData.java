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
 * NeonatalPatientDemographicsCoreData constitutes patient demographics for neonates.
 * 
 * <p>Java-Klasse für NeonatalPatientDemographicsCoreData complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="NeonatalPatientDemographicsCoreData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PatientDemographicsCoreData">
 *       &lt;sequence>
 *         &lt;element name="GestationalAge" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="BirthLength" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="BirthWeight" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="HeadCircumference" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="Mother" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NeonatalPatientDemographicsCoreData", propOrder = {
    "gestationalAge",
    "birthLength",
    "birthWeight",
    "headCircumference",
    "mother"
})
public class NeonatalPatientDemographicsCoreData
    extends PatientDemographicsCoreData
{

    @XmlElement(name = "GestationalAge")
    protected Measurement gestationalAge;
    @XmlElement(name = "BirthLength")
    protected Measurement birthLength;
    @XmlElement(name = "BirthWeight")
    protected Measurement birthWeight;
    @XmlElement(name = "HeadCircumference")
    protected Measurement headCircumference;
    @XmlElement(name = "Mother")
    protected PersonReference mother;

    /**
     * Ruft den Wert der gestationalAge-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getGestationalAge() {
        return gestationalAge;
    }

    /**
     * Legt den Wert der gestationalAge-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setGestationalAge(Measurement value) {
        this.gestationalAge = value;
    }

    /**
     * Ruft den Wert der birthLength-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getBirthLength() {
        return birthLength;
    }

    /**
     * Legt den Wert der birthLength-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setBirthLength(Measurement value) {
        this.birthLength = value;
    }

    /**
     * Ruft den Wert der birthWeight-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getBirthWeight() {
        return birthWeight;
    }

    /**
     * Legt den Wert der birthWeight-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setBirthWeight(Measurement value) {
        this.birthWeight = value;
    }

    /**
     * Ruft den Wert der headCircumference-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getHeadCircumference() {
        return headCircumference;
    }

    /**
     * Legt den Wert der headCircumference-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setHeadCircumference(Measurement value) {
        this.headCircumference = value;
    }

    /**
     * Ruft den Wert der mother-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonReference }
     *     
     */
    public PersonReference getMother() {
        return mother;
    }

    /**
     * Legt den Wert der mother-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonReference }
     *     
     */
    public void setMother(PersonReference value) {
        this.mother = value;
    }

}
