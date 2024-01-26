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
import javax.xml.bind.annotation.XmlType;


/**
 * A context state to identify a step in a clinical workflow.
 * 
 * <p>Java-Klasse für WorkflowContextState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="WorkflowContextState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractContextState">
 *       &lt;sequence>
 *         &lt;element name="WorkflowDetail" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                   &lt;element name="Patient" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference"/>
 *                   &lt;element name="AssignedLocation" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocationReference" minOccurs="0"/>
 *                   &lt;element name="VisitNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
 *                   &lt;element name="DangerCode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="RelevantClinicalInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ClinicalInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="RequestedOrderDetail" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OrderDetail">
 *                           &lt;sequence>
 *                             &lt;element name="ReferringPhysician" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference" minOccurs="0"/>
 *                             &lt;element name="RequestingPhysician" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference" minOccurs="0"/>
 *                             &lt;element name="PlacerOrderNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="PerformedOrderDetail" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OrderDetail">
 *                           &lt;sequence>
 *                             &lt;element name="FillerOrderNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
 *                             &lt;element name="ResultingClinicalInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ClinicalInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
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
@XmlType(name = "WorkflowContextState", propOrder = {
    "workflowDetail"
})
public class WorkflowContextState
    extends AbstractContextState
{

    @XmlElement(name = "WorkflowDetail")
    protected WorkflowContextState.WorkflowDetail workflowDetail;

    /**
     * Ruft den Wert der workflowDetail-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowContextState.WorkflowDetail }
     *     
     */
    public WorkflowContextState.WorkflowDetail getWorkflowDetail() {
        return workflowDetail;
    }

    /**
     * Legt den Wert der workflowDetail-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowContextState.WorkflowDetail }
     *     
     */
    public void setWorkflowDetail(WorkflowContextState.WorkflowDetail value) {
        this.workflowDetail = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
     *         &lt;element name="Patient" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference"/>
     *         &lt;element name="AssignedLocation" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocationReference" minOccurs="0"/>
     *         &lt;element name="VisitNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
     *         &lt;element name="DangerCode" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="RelevantClinicalInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ClinicalInfo" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="RequestedOrderDetail" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OrderDetail">
     *                 &lt;sequence>
     *                   &lt;element name="ReferringPhysician" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference" minOccurs="0"/>
     *                   &lt;element name="RequestingPhysician" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference" minOccurs="0"/>
     *                   &lt;element name="PlacerOrderNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
     *                 &lt;/sequence>
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="PerformedOrderDetail" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OrderDetail">
     *                 &lt;sequence>
     *                   &lt;element name="FillerOrderNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
     *                   &lt;element name="ResultingClinicalInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ClinicalInfo" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "extension",
        "patient",
        "assignedLocation",
        "visitNumber",
        "dangerCode",
        "relevantClinicalInfo",
        "requestedOrderDetail",
        "performedOrderDetail"
    })
    public static class WorkflowDetail {

        @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
        protected ExtensionType extension;
        @XmlElement(name = "Patient", required = true)
        protected PersonReference patient;
        @XmlElement(name = "AssignedLocation")
        protected LocationReference assignedLocation;
        @XmlElement(name = "VisitNumber")
        protected InstanceIdentifier visitNumber;
        @XmlElement(name = "DangerCode")
        protected List<CodedValue> dangerCode;
        @XmlElement(name = "RelevantClinicalInfo")
        protected List<ClinicalInfo> relevantClinicalInfo;
        @XmlElement(name = "RequestedOrderDetail")
        protected WorkflowContextState.WorkflowDetail.RequestedOrderDetail requestedOrderDetail;
        @XmlElement(name = "PerformedOrderDetail")
        protected WorkflowContextState.WorkflowDetail.PerformedOrderDetail performedOrderDetail;

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
         * Ruft den Wert der patient-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link PersonReference }
         *     
         */
        public PersonReference getPatient() {
            return patient;
        }

        /**
         * Legt den Wert der patient-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link PersonReference }
         *     
         */
        public void setPatient(PersonReference value) {
            this.patient = value;
        }

        /**
         * Ruft den Wert der assignedLocation-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link LocationReference }
         *     
         */
        public LocationReference getAssignedLocation() {
            return assignedLocation;
        }

        /**
         * Legt den Wert der assignedLocation-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link LocationReference }
         *     
         */
        public void setAssignedLocation(LocationReference value) {
            this.assignedLocation = value;
        }

        /**
         * Ruft den Wert der visitNumber-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link InstanceIdentifier }
         *     
         */
        public InstanceIdentifier getVisitNumber() {
            return visitNumber;
        }

        /**
         * Legt den Wert der visitNumber-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link InstanceIdentifier }
         *     
         */
        public void setVisitNumber(InstanceIdentifier value) {
            this.visitNumber = value;
        }

        /**
         * Gets the value of the dangerCode property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dangerCode property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDangerCode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CodedValue }
         * 
         * 
         */
        public List<CodedValue> getDangerCode() {
            if (dangerCode == null) {
                dangerCode = new ArrayList<CodedValue>();
            }
            return this.dangerCode;
        }

        /**
         * Gets the value of the relevantClinicalInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the relevantClinicalInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRelevantClinicalInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ClinicalInfo }
         * 
         * 
         */
        public List<ClinicalInfo> getRelevantClinicalInfo() {
            if (relevantClinicalInfo == null) {
                relevantClinicalInfo = new ArrayList<ClinicalInfo>();
            }
            return this.relevantClinicalInfo;
        }

        /**
         * Ruft den Wert der requestedOrderDetail-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link WorkflowContextState.WorkflowDetail.RequestedOrderDetail }
         *     
         */
        public WorkflowContextState.WorkflowDetail.RequestedOrderDetail getRequestedOrderDetail() {
            return requestedOrderDetail;
        }

        /**
         * Legt den Wert der requestedOrderDetail-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link WorkflowContextState.WorkflowDetail.RequestedOrderDetail }
         *     
         */
        public void setRequestedOrderDetail(WorkflowContextState.WorkflowDetail.RequestedOrderDetail value) {
            this.requestedOrderDetail = value;
        }

        /**
         * Ruft den Wert der performedOrderDetail-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link WorkflowContextState.WorkflowDetail.PerformedOrderDetail }
         *     
         */
        public WorkflowContextState.WorkflowDetail.PerformedOrderDetail getPerformedOrderDetail() {
            return performedOrderDetail;
        }

        /**
         * Legt den Wert der performedOrderDetail-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link WorkflowContextState.WorkflowDetail.PerformedOrderDetail }
         *     
         */
        public void setPerformedOrderDetail(WorkflowContextState.WorkflowDetail.PerformedOrderDetail value) {
            this.performedOrderDetail = value;
        }


        /**
         * <p>Java-Klasse für anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OrderDetail">
         *       &lt;sequence>
         *         &lt;element name="FillerOrderNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" minOccurs="0"/>
         *         &lt;element name="ResultingClinicalInfo" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ClinicalInfo" maxOccurs="unbounded" minOccurs="0"/>
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
            "fillerOrderNumber",
            "resultingClinicalInfo"
        })
        public static class PerformedOrderDetail
            extends OrderDetail
        {

            @XmlElement(name = "FillerOrderNumber")
            protected InstanceIdentifier fillerOrderNumber;
            @XmlElement(name = "ResultingClinicalInfo")
            protected List<ClinicalInfo> resultingClinicalInfo;

            /**
             * Ruft den Wert der fillerOrderNumber-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link InstanceIdentifier }
             *     
             */
            public InstanceIdentifier getFillerOrderNumber() {
                return fillerOrderNumber;
            }

            /**
             * Legt den Wert der fillerOrderNumber-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link InstanceIdentifier }
             *     
             */
            public void setFillerOrderNumber(InstanceIdentifier value) {
                this.fillerOrderNumber = value;
            }

            /**
             * Gets the value of the resultingClinicalInfo property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the resultingClinicalInfo property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getResultingClinicalInfo().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ClinicalInfo }
             * 
             * 
             */
            public List<ClinicalInfo> getResultingClinicalInfo() {
                if (resultingClinicalInfo == null) {
                    resultingClinicalInfo = new ArrayList<ClinicalInfo>();
                }
                return this.resultingClinicalInfo;
            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OrderDetail">
         *       &lt;sequence>
         *         &lt;element name="ReferringPhysician" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference" minOccurs="0"/>
         *         &lt;element name="RequestingPhysician" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PersonReference" minOccurs="0"/>
         *         &lt;element name="PlacerOrderNumber" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier"/>
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
            "referringPhysician",
            "requestingPhysician",
            "placerOrderNumber"
        })
        public static class RequestedOrderDetail
            extends OrderDetail
        {

            @XmlElement(name = "ReferringPhysician")
            protected PersonReference referringPhysician;
            @XmlElement(name = "RequestingPhysician")
            protected PersonReference requestingPhysician;
            @XmlElement(name = "PlacerOrderNumber", required = true)
            protected InstanceIdentifier placerOrderNumber;

            /**
             * Ruft den Wert der referringPhysician-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link PersonReference }
             *     
             */
            public PersonReference getReferringPhysician() {
                return referringPhysician;
            }

            /**
             * Legt den Wert der referringPhysician-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link PersonReference }
             *     
             */
            public void setReferringPhysician(PersonReference value) {
                this.referringPhysician = value;
            }

            /**
             * Ruft den Wert der requestingPhysician-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link PersonReference }
             *     
             */
            public PersonReference getRequestingPhysician() {
                return requestingPhysician;
            }

            /**
             * Legt den Wert der requestingPhysician-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link PersonReference }
             *     
             */
            public void setRequestingPhysician(PersonReference value) {
                this.requestingPhysician = value;
            }

            /**
             * Ruft den Wert der placerOrderNumber-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link InstanceIdentifier }
             *     
             */
            public InstanceIdentifier getPlacerOrderNumber() {
                return placerOrderNumber;
            }

            /**
             * Legt den Wert der placerOrderNumber-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link InstanceIdentifier }
             *     
             */
            public void setPlacerOrderNumber(InstanceIdentifier value) {
                this.placerOrderNumber = value;
            }

        }

    }

}
