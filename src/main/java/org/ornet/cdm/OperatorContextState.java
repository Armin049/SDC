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
 * A context state that identifies an operator of an MDS or a part of it.
 * 
 * <p>Java-Klasse für OperatorContextState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="OperatorContextState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractContextState">
 *       &lt;sequence>
 *         &lt;element name="OperatorDetails" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}BaseDemographics" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperatorContextState", propOrder = {
    "operatorDetails"
})
public class OperatorContextState
    extends AbstractContextState
{

    @XmlElement(name = "OperatorDetails")
    protected BaseDemographics operatorDetails;

    /**
     * Ruft den Wert der operatorDetails-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BaseDemographics }
     *     
     */
    public BaseDemographics getOperatorDetails() {
        return operatorDetails;
    }

    /**
     * Legt den Wert der operatorDetails-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseDemographics }
     *     
     */
    public void setOperatorDetails(BaseDemographics value) {
        this.operatorDetails = value;
    }

}
