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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * AbstractMultiState is derived from pm:AbstractState. In contrast to pm:AbstractState, AbstractMultiState possesses a HANDLE name. The HANDLE name uniquely identifies the state, which is required if the relation to a descriptor is ambiguous.
 * 
 * <p>Java-Klasse für AbstractMultiState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractMultiState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState">
 *       &lt;sequence>
 *         &lt;element name="Category" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Handle" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Handle" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMultiState", propOrder = {
    "category"
})
@XmlSeeAlso({
    AbstractContextState.class
})
public class AbstractMultiState
    extends AbstractState
{

    @XmlElement(name = "Category")
    protected CodedValue category;
    @XmlAttribute(name = "Handle", required = true)
    protected String handle;

    /**
     * Ruft den Wert der category-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getCategory() {
        return category;
    }

    /**
     * Legt den Wert der category-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setCategory(CodedValue value) {
        this.category = value;
    }

    /**
     * Ruft den Wert der handle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Legt den Wert der handle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandle(String value) {
        this.handle = value;
    }

}
