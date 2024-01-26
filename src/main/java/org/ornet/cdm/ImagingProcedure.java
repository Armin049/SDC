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
import javax.xml.bind.annotation.XmlType;


/**
 * ImagingProcedure provides identifiers used by the DICOM and HL7 standard to identify the requested imaging procedures resulting from an order in a the hospital. Often these identifiers are created/assigned by the main hospital information system or departmental information systems and are taken over into any medical images by DICOM equipment in the context of this procedure.
 * The listed ELEMENTs have been taken over from the IHE Radiology Technical Framework's RAD-4 transaction ("Procedure Scheduled") and re-uses the identifiers listed for the HL7 Version 2.5.1 IPC segment group of the OBR segment. Therefore, it is recommended to comply to the underlying HL7 and DICOM data types in order to have seamless integration with other clinical IT such as DICOM modalities or image archives (PACS).
 * 
 * In order to comply to the hierarchy behind the given identifiers, the following rules (taken from IHE) SHALL apply: if a Requested Procedure is comprised of multiple Scheduled Procedure Steps and/or if a Scheduled Procedure Step is comprised of multiple Protocol Codes, each applicable Scheduled Procedure Step / Protocol Code combination is included as a separate ProcedureDetails structure, i.e., the complex type "ProcedureDetails" occurs the same amount of times as there are different Scheduled Procedure Step IDs plus the amount of different Scheduled Procedure Step / Protocol Code combinations.
 * 
 * <p>Java-Klasse für ImagingProcedure complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ImagingProcedure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="AccessionIdentifier" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
 *         &lt;element name="RequestedProcedureId" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
 *         &lt;element name="StudyInstanceUid" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
 *         &lt;element name="ScheduledProcedureStepId" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
 *         &lt;element name="Modality" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *         &lt;element name="ProtocolCode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImagingProcedure", propOrder = {
    "extension",
    "accessionIdentifier",
    "requestedProcedureId",
    "studyInstanceUid",
    "scheduledProcedureStepId",
    "modality",
    "protocolCode"
})
public class ImagingProcedure {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "AccessionIdentifier", required = true)
    protected InstanceIdentifier accessionIdentifier;
    @XmlElement(name = "RequestedProcedureId", required = true)
    protected InstanceIdentifier requestedProcedureId;
    @XmlElement(name = "StudyInstanceUid", required = true)
    protected InstanceIdentifier studyInstanceUid;
    @XmlElement(name = "ScheduledProcedureStepId", required = true)
    protected InstanceIdentifier scheduledProcedureStepId;
    @XmlElement(name = "Modality")
    protected CodedValue modality;
    @XmlElement(name = "ProtocolCode")
    protected CodedValue protocolCode;

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
     * Ruft den Wert der accessionIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InstanceIdentifier }
     *     
     */
    public InstanceIdentifier getAccessionIdentifier() {
        return accessionIdentifier;
    }

    /**
     * Legt den Wert der accessionIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InstanceIdentifier }
     *     
     */
    public void setAccessionIdentifier(InstanceIdentifier value) {
        this.accessionIdentifier = value;
    }

    /**
     * Ruft den Wert der requestedProcedureId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InstanceIdentifier }
     *     
     */
    public InstanceIdentifier getRequestedProcedureId() {
        return requestedProcedureId;
    }

    /**
     * Legt den Wert der requestedProcedureId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InstanceIdentifier }
     *     
     */
    public void setRequestedProcedureId(InstanceIdentifier value) {
        this.requestedProcedureId = value;
    }

    /**
     * Ruft den Wert der studyInstanceUid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InstanceIdentifier }
     *     
     */
    public InstanceIdentifier getStudyInstanceUid() {
        return studyInstanceUid;
    }

    /**
     * Legt den Wert der studyInstanceUid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InstanceIdentifier }
     *     
     */
    public void setStudyInstanceUid(InstanceIdentifier value) {
        this.studyInstanceUid = value;
    }

    /**
     * Ruft den Wert der scheduledProcedureStepId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InstanceIdentifier }
     *     
     */
    public InstanceIdentifier getScheduledProcedureStepId() {
        return scheduledProcedureStepId;
    }

    /**
     * Legt den Wert der scheduledProcedureStepId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InstanceIdentifier }
     *     
     */
    public void setScheduledProcedureStepId(InstanceIdentifier value) {
        this.scheduledProcedureStepId = value;
    }

    /**
     * Ruft den Wert der modality-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getModality() {
        return modality;
    }

    /**
     * Legt den Wert der modality-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setModality(CodedValue value) {
        this.modality = value;
    }

    /**
     * Ruft den Wert der protocolCode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CodedValue }
     *     
     */
    public CodedValue getProtocolCode() {
        return protocolCode;
    }

    /**
     * Legt den Wert der protocolCode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CodedValue }
     *     
     */
    public void setProtocolCode(CodedValue value) {
        this.protocolCode = value;
    }

}
