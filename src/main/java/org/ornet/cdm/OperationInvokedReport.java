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
 *                   &lt;element name="InvocationInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}InvocationInfo"/>
 *                   &lt;element name="InvocationSource" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="OperationHandleRef" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
 *                 &lt;attribute name="OperationTarget" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
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
@XmlRootElement(name = "OperationInvokedReport", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class OperationInvokedReport
    extends AbstractReport
{

    @XmlElement(name = "ReportPart", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
    protected List<OperationInvokedReport.ReportPart> reportPart;

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
     * {@link OperationInvokedReport.ReportPart }
     * 
     * 
     */
    public List<OperationInvokedReport.ReportPart> getReportPart() {
        if (reportPart == null) {
            reportPart = new ArrayList<OperationInvokedReport.ReportPart>();
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
     *         &lt;element name="InvocationInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}InvocationInfo"/>
     *         &lt;element name="InvocationSource" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
     *       &lt;/sequence>
     *       &lt;attribute name="OperationHandleRef" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
     *       &lt;attribute name="OperationTarget" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "invocationInfo",
        "invocationSource"
    })
    public static class ReportPart
        extends AbstractReportPart
    {

        @XmlElement(name = "InvocationInfo", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
        protected InvocationInfo invocationInfo;
        @XmlElement(name = "InvocationSource", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
        protected InstanceIdentifier invocationSource;
        @XmlAttribute(name = "OperationHandleRef", required = true)
        protected String operationHandleRef;
        @XmlAttribute(name = "OperationTarget")
        protected String operationTarget;

        /**
         * Ruft den Wert der invocationInfo-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link InvocationInfo }
         *     
         */
        public InvocationInfo getInvocationInfo() {
            return invocationInfo;
        }

        /**
         * Legt den Wert der invocationInfo-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link InvocationInfo }
         *     
         */
        public void setInvocationInfo(InvocationInfo value) {
            this.invocationInfo = value;
        }

        /**
         * Ruft den Wert der invocationSource-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link InstanceIdentifier }
         *     
         */
        public InstanceIdentifier getInvocationSource() {
            return invocationSource;
        }

        /**
         * Legt den Wert der invocationSource-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link InstanceIdentifier }
         *     
         */
        public void setInvocationSource(InstanceIdentifier value) {
            this.invocationSource = value;
        }

        /**
         * Ruft den Wert der operationHandleRef-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperationHandleRef() {
            return operationHandleRef;
        }

        /**
         * Legt den Wert der operationHandleRef-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperationHandleRef(String value) {
            this.operationHandleRef = value;
        }

        /**
         * Ruft den Wert der operationTarget-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperationTarget() {
            return operationTarget;
        }

        /**
         * Legt den Wert der operationTarget-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperationTarget(String value) {
            this.operationTarget = value;
        }

    }

}
