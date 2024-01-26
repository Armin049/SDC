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
 * AbstractComponentReport is a change report that contains updated pm:AbstractComponentState instances.
 * 
 * <p>Java-Klasse für AbstractComponentReport complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractComponentReport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReport">
 *       &lt;sequence>
 *         &lt;element name="ReportPart" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReportPart">
 *                 &lt;sequence>
 *                   &lt;element name="ComponentState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentState" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractComponentReport", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", propOrder = {
    "reportPart"
})
@XmlSeeAlso({
    EpisodicComponentReport.class,
    PeriodicComponentReport.class
})
public class AbstractComponentReport
    extends AbstractReport
{

    @XmlElement(name = "ReportPart")
    protected List<AbstractComponentReport.ReportPart> reportPart;

    /**
     * Gets the value of the reportPart property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportPart property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportPart().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractComponentReport.ReportPart }
     * 
     * 
     */
    public List<AbstractComponentReport.ReportPart> getReportPart() {
        if (reportPart == null) {
            reportPart = new ArrayList<AbstractComponentReport.ReportPart>();
        }
        return this.reportPart;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReportPart">
     *       &lt;sequence>
     *         &lt;element name="ComponentState" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentState" maxOccurs="unbounded" minOccurs="0"/>
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
        "componentState"
    })
    public static class ReportPart
        extends AbstractReportPart
    {

        @XmlElement(name = "ComponentState", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
        protected List<AbstractDeviceComponentState> componentState;

        /**
         * Gets the value of the componentState property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the componentState property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComponentState().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AbstractDeviceComponentState }
         * 
         * 
         */
        public List<AbstractDeviceComponentState> getComponentState() {
            if (componentState == null) {
                componentState = new ArrayList<AbstractDeviceComponentState>();
            }
            return this.componentState;
        }

    }

}
