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
 *         &lt;element name="ProposedMetricState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractMetricState" maxOccurs="unbounded"/>
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
    "proposedMetricState"
})
@XmlRootElement(name = "SetMetricState", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class SetMetricState
    extends AbstractSet
{

    @XmlElement(name = "ProposedMetricState", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
    protected List<AbstractMetricState> proposedMetricState;

    /**
     * Gets the value of the proposedMetricState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proposedMetricState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProposedMetricState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractMetricState }
     * 
     * 
     */
    public List<AbstractMetricState> getProposedMetricState() {
        if (proposedMetricState == null) {
            proposedMetricState = new ArrayList<AbstractMetricState>();
        }
        return this.proposedMetricState;
    }

}
