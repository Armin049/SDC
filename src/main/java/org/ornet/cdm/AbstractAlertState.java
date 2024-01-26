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
 * AbstractAlertState acts as a base class for all alert states that contain dynamic/volatile alert meta information.
 * 
 * <p>Java-Klasse für AbstractAlertState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractAlertState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState">
 *       &lt;attribute name="ActivationState" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AlertActivation" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractAlertState")
@XmlSeeAlso({
    AlertSignalState.class,
    AlertConditionState.class,
    AlertSystemState.class
})
public class AbstractAlertState
    extends AbstractState
{

    @XmlAttribute(name = "ActivationState", required = true)
    protected AlertActivation activationState;

    /**
     * Ruft den Wert der activationState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlertActivation }
     *     
     */
    public AlertActivation getActivationState() {
        return activationState;
    }

    /**
     * Legt den Wert der activationState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertActivation }
     *     
     */
    public void setActivationState(AlertActivation value) {
        this.activationState = value;
    }

}
