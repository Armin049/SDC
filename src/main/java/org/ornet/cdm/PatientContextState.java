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
 * Observed information about a patient, e.g., demographics.
 * 
 * NOTE—PatientContextState contains information that is typical for a header in an anamnesis questionnaire.
 * 
 * <p>Java-Klasse für PatientContextState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PatientContextState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractContextState">
 *       &lt;sequence>
 *         &lt;element name="CoreData" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PatientDemographicsCoreData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientContextState", propOrder = {
    "coreData"
})
public class PatientContextState
    extends AbstractContextState
{

    @XmlElement(name = "CoreData")
    protected PatientDemographicsCoreData coreData;

    /**
     * Ruft den Wert der coreData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PatientDemographicsCoreData }
     *     
     */
    public PatientDemographicsCoreData getCoreData() {
        return coreData;
    }

    /**
     * Legt den Wert der coreData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDemographicsCoreData }
     *     
     */
    public void setCoreData(PatientDemographicsCoreData value) {
        this.coreData = value;
    }

}
