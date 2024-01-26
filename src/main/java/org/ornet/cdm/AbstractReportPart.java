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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Any report that is delivered using msg:AbstractReport MAY consist of multiple report parts. AbstractReport is the building block for a single report part in a msg:AbstractReport.
 * 
 * <p>Java-Klasse für AbstractReportPart complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractReportPart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="SourceMds" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractReportPart", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message", propOrder = {
    "extension",
    "sourceMds"
})
@XmlSeeAlso({
    org.ornet.cdm.AbstractOperationalStateReport.ReportPart.class,
    org.ornet.cdm.SystemErrorReport.ReportPart.class,
    org.ornet.cdm.AbstractComponentReport.ReportPart.class,
    org.ornet.cdm.DescriptionModificationReport.ReportPart.class,
    org.ornet.cdm.AbstractAlertReport.ReportPart.class,
    org.ornet.cdm.OperationInvokedReport.ReportPart.class,
    org.ornet.cdm.AbstractMetricReport.ReportPart.class,
    org.ornet.cdm.AbstractContextReport.ReportPart.class
})
public class AbstractReportPart {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "SourceMds")
    protected String sourceMds;

    /**
     * Ruft den Wert der extension-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Legt den Wert der extension-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

    /**
     * Ruft den Wert der sourceMds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceMds() {
        return sourceMds;
    }

    /**
     * Legt den Wert der sourceMds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceMds(String value) {
        this.sourceMds = value;
    }

}
