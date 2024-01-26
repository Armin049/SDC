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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractSet">
 *       &lt;sequence>
 *         &lt;element name="ProposedAlertState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractAlertState"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "proposedAlertState"
})
@XmlRootElement(name = "SetAlertState", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class SetAlertState
    extends AbstractSet
{

    @XmlElement(name = "ProposedAlertState", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
    protected AbstractAlertState proposedAlertState;

    /**
     * Ruft den Wert der proposedAlertState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AbstractAlertState }
     *     
     */
    public AbstractAlertState getProposedAlertState() {
        return proposedAlertState;
    }

    /**
     * Legt den Wert der proposedAlertState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractAlertState }
     *     
     */
    public void setProposedAlertState(AbstractAlertState value) {
        this.proposedAlertState = value;
    }

}
