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
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReport">
 *       &lt;sequence>
 *         &lt;element name="State" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}RealTimeSampleArrayMetricState" maxOccurs="unbounded" minOccurs="0"/>
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
    "state"
})
@XmlRootElement(name = "WaveformStream", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class WaveformStream
    extends AbstractReport
{

    @XmlElement(name = "State", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    protected List<RealTimeSampleArrayMetricState> state;

    /**
     * Gets the value of the state property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the state property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RealTimeSampleArrayMetricState }
     * 
     * 
     */
    public List<RealTimeSampleArrayMetricState> getState() {
        if (state == null) {
            state = new ArrayList<RealTimeSampleArrayMetricState>();
        }
        return this.state;
    }

}
