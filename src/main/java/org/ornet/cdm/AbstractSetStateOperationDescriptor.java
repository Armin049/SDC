//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Abstract description of an operation that is exposed on the SCO and is intended to be used to set a complete state.
 * 
 * <p>Java-Klasse für AbstractSetStateOperationDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractSetStateOperationDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractOperationDescriptor">
 *       &lt;sequence>
 *         &lt;element name="ModifiableData" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSetStateOperationDescriptor", propOrder = {
    "modifiableData"
})
@XmlSeeAlso({
    SetMetricStateOperationDescriptor.class,
    ActivateOperationDescriptor.class,
    SetAlertStateOperationDescriptor.class,
    SetComponentStateOperationDescriptor.class,
    SetContextStateOperationDescriptor.class
})
public class AbstractSetStateOperationDescriptor
    extends AbstractOperationDescriptor
{

    @XmlElement(name = "ModifiableData")
    protected List<String> modifiableData;

    /**
     * Gets the value of the modifiableData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modifiableData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModifiableData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getModifiableData() {
        if (modifiableData == null) {
            modifiableData = new ArrayList<String>();
        }
        return this.modifiableData;
    }

}
