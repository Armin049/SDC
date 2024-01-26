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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes a string set operation for a specific object state in the MDIB that is exposed on the SCO.
 * 
 * <p>Java-Klasse für SetStringOperationDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SetStringOperationDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractOperationDescriptor">
 *       &lt;attribute name="MaxLength" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetStringOperationDescriptor")
public class SetStringOperationDescriptor
    extends AbstractOperationDescriptor
{

    @XmlAttribute(name = "MaxLength")
    @XmlSchemaType(name = "unsignedLong")
    protected Long maxLength;

    /**
     * Ruft den Wert der maxLength-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxLength() {
        return maxLength;
    }

    /**
     * Legt den Wert der maxLength-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxLength(Long value) {
        this.maxLength = value;
    }

}
