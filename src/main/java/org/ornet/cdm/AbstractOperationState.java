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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * State of an operation that is exposed on the SCO.
 * 
 * <p>Java-Klasse für AbstractOperationState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractOperationState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState">
 *       &lt;attribute name="OperatingMode" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperatingMode" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractOperationState")
@XmlSeeAlso({
    SetMetricStateOperationState.class,
    SetComponentStateOperationState.class,
    SetValueOperationState.class,
    SetContextStateOperationState.class,
    ActivateOperationState.class,
    SetStringOperationState.class,
    SetAlertStateOperationState.class
})
public class AbstractOperationState
    extends AbstractState
{

    @XmlAttribute(name = "OperatingMode", required = true)
    protected OperatingMode operatingMode;

    /**
     * Ruft den Wert der operatingMode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OperatingMode }
     *     
     */
    public OperatingMode getOperatingMode() {
        return operatingMode;
    }

    /**
     * Legt den Wert der operatingMode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatingMode }
     *     
     */
    public void setOperatingMode(OperatingMode value) {
        this.operatingMode = value;
    }

}
