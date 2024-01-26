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
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="ReportPart" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReportPart">
 *                 &lt;sequence>
 *                   &lt;element name="Descriptor" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="State" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ParentDescriptor" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
 *                 &lt;attribute name="ModificationType" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}DescriptionModificationType" />
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
@XmlType(name = "", propOrder = {
    "reportPart"
})
@XmlRootElement(name = "DescriptionModificationReport", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class DescriptionModificationReport
    extends AbstractReport
{

    @XmlElement(name = "ReportPart", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
    protected List<DescriptionModificationReport.ReportPart> reportPart;

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
     * {@link DescriptionModificationReport.ReportPart }
     * 
     * 
     */
    public List<DescriptionModificationReport.ReportPart> getReportPart() {
        if (reportPart == null) {
            reportPart = new ArrayList<DescriptionModificationReport.ReportPart>();
        }
        return this.reportPart;
    }


    /**
     * List of report containers associated to one MDS.
     * 
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReportPart">
     *       &lt;sequence>
     *         &lt;element name="Descriptor" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDescriptor" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="State" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="ParentDescriptor" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
     *       &lt;attribute name="ModificationType" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}DescriptionModificationType" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "descriptor",
        "state"
    })
    public static class ReportPart
        extends AbstractReportPart
    {

        @XmlElement(name = "Descriptor", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
        protected List<AbstractDescriptor> descriptor;
        @XmlElement(name = "State", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
        protected List<AbstractState> state;
        @XmlAttribute(name = "ParentDescriptor")
        protected String parentDescriptor;
        @XmlAttribute(name = "ModificationType")
        protected DescriptionModificationType modificationType;

        /**
         * Gets the value of the descriptor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the descriptor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDescriptor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AbstractDescriptor }
         * 
         * 
         */
        public List<AbstractDescriptor> getDescriptor() {
            if (descriptor == null) {
                descriptor = new ArrayList<AbstractDescriptor>();
            }
            return this.descriptor;
        }

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
         * {@link AbstractState }
         * 
         * 
         */
        public List<AbstractState> getState() {
            if (state == null) {
                state = new ArrayList<AbstractState>();
            }
            return this.state;
        }

        /**
         * Ruft den Wert der parentDescriptor-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParentDescriptor() {
            return parentDescriptor;
        }

        /**
         * Legt den Wert der parentDescriptor-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParentDescriptor(String value) {
            this.parentDescriptor = value;
        }

        /**
         * Ruft den Wert der modificationType-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link DescriptionModificationType }
         *     
         */
        public DescriptionModificationType getModificationType() {
            return modificationType;
        }

        /**
         * Legt den Wert der modificationType-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link DescriptionModificationType }
         *     
         */
        public void setModificationType(DescriptionModificationType value) {
            this.modificationType = value;
        }

    }

}
