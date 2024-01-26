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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The patient demographics data as defined in ISO/IEEE 11073-10201:2004 (6.10.1 Patient Demographics object).
 * 			
 * __R5012: If the POC MEDICAL DEVICE itself has patient-related observations (e.g., weight, height, etc.) as in- or output, these SHOULD be modelled as METRICs.__
 * 
 * NOTE—In contrast to PatientDemographicsCoreData, METRICs provide a sophisticated observation description, e.g., regarding quality and time-related attributes.
 * 
 * __R5013: The pm:PatientDemographicsCoreData type is intended to be used for information purposes only. Whenever a value is available, it is considered as valid. Invalid values SHALL not be transmitted.__
 * 
 * <p>Java-Klasse für PatientDemographicsCoreData complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PatientDemographicsCoreData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}BaseDemographics">
 *       &lt;sequence>
 *         &lt;element name="Sex" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Sex" minOccurs="0"/>
 *         &lt;element name="PatientType" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PatientType" minOccurs="0"/>
 *         &lt;element name="DateOfBirth" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union memberTypes=" {http://www.w3.org/2001/XMLSchema}dateTime {http://www.w3.org/2001/XMLSchema}date {http://www.w3.org/2001/XMLSchema}gYearMonth {http://www.w3.org/2001/XMLSchema}gYear">
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Height" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="Weight" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Measurement" minOccurs="0"/>
 *         &lt;element name="Race" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientDemographicsCoreData", propOrder = {
    "sex",
    "patientType",
    "dateOfBirth",
    "height",
    "weight",
    "race"
})
@XmlSeeAlso({
    NeonatalPatientDemographicsCoreData.class
})
public class PatientDemographicsCoreData
    extends BaseDemographics
{

    @XmlElement(name = "Sex")
    @XmlSchemaType(name = "string")
    protected Sex sex;
    @XmlElement(name = "PatientType")
    @XmlSchemaType(name = "string")
    protected PatientType patientType;
    @XmlElement(name = "DateOfBirth")
    protected String dateOfBirth;
    @XmlElement(name = "Height")
    protected Measurement height;
    @XmlElement(name = "Weight")
    protected Measurement weight;
    @XmlElement(name = "Race")
    protected CodedValue race;

    /**
     * Ruft den Wert der sex-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Sex }
     *     
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Legt den Wert der sex-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Sex }
     *     
     */
    public void setSex(Sex value) {
        this.sex = value;
    }

    /**
     * Ruft den Wert der patientType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PatientType }
     *     
     */
    public PatientType getPatientType() {
        return patientType;
    }

    /**
     * Legt den Wert der patientType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientType }
     *     
     */
    public void setPatientType(PatientType value) {
        this.patientType = value;
    }

    /**
     * Ruft den Wert der dateOfBirth-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Legt den Wert der dateOfBirth-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Ruft den Wert der height-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getHeight() {
        return height;
    }

    /**
     * Legt den Wert der height-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setHeight(Measurement value) {
        this.height = value;
    }

    /**
     * Ruft den Wert der weight-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Measurement }
     *     
     */
    public Measurement getWeight() {
        return weight;
    }

    /**
     * Legt den Wert der weight-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Measurement }
     *     
     */
    public void setWeight(Measurement value) {
        this.weight = value;
    }

    /**
     * Ruft den Wert der race-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getRace() {
        return race;
    }

    /**
     * Legt den Wert der race-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setRace(CodedValue value) {
        this.race = value;
    }

}
