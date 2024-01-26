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
 *         &lt;element name="ReportPart" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractReportPart">
 *                 &lt;sequence>
 *                   &lt;element name="ErrorCode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *                   &lt;element name="ErrorInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "reportPart"
})
@XmlRootElement(name = "SystemErrorReport", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class SystemErrorReport
    extends AbstractReport
{

    @XmlElement(name = "ReportPart", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
    protected List<SystemErrorReport.ReportPart> reportPart;

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
     * {@link SystemErrorReport.ReportPart }
     * 
     * 
     */
    public List<SystemErrorReport.ReportPart> getReportPart() {
        if (reportPart == null) {
            reportPart = new ArrayList<SystemErrorReport.ReportPart>();
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
     *         &lt;element name="ErrorCode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
     *         &lt;element name="ErrorInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" minOccurs="0"/>
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
        "errorCode",
        "errorInfo"
    })
    public static class ReportPart
        extends AbstractReportPart
    {

        @XmlElement(name = "ErrorCode", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", required = true)
        protected CodedValue errorCode;
        @XmlElement(name = "ErrorInfo", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
        protected LocalizedText errorInfo;

        /**
         * Ruft den Wert der errorCode-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link CodedValue }
         *     
         */
        public CodedValue getErrorCode() {
            return errorCode;
        }

        /**
         * Legt den Wert der errorCode-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link CodedValue }
         *     
         */
        public void setErrorCode(CodedValue value) {
            this.errorCode = value;
        }

        /**
         * Ruft den Wert der errorInfo-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link LocalizedText }
         *     
         */
        public LocalizedText getErrorInfo() {
            return errorInfo;
        }

        /**
         * Legt den Wert der errorInfo-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link LocalizedText }
         *     
         */
        public void setErrorInfo(LocalizedText value) {
            this.errorInfo = value;
        }

    }

}
