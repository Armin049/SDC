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
 * The context of an MDS that lists the possible relationship of a POC MEDICAL DEVICE into its usage environment by means of context descriptors. Context descriptors do not contain any stateful information. They only assert that the underlying MDS can provide corresponding context state information.
 * 
 * <p>Java-Klasse für SystemContextDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SystemContextDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDeviceComponentDescriptor">
 *       &lt;sequence>
 *         &lt;element name="PatientContext" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}PatientContextDescriptor" minOccurs="0"/>
 *         &lt;element name="LocationContext" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocationContextDescriptor" minOccurs="0"/>
 *         &lt;element name="EnsembleContext" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}EnsembleContextDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OperatorContext" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}OperatorContextDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="WorkflowContext" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}WorkflowContextDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MeansContext" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}MeansContextDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemContextDescriptor", propOrder = {
    "patientContext",
    "locationContext",
    "ensembleContext",
    "operatorContext",
    "workflowContext",
    "meansContext"
})
public class SystemContextDescriptor
    extends AbstractDeviceComponentDescriptor
{

    @XmlElement(name = "PatientContext")
    protected PatientContextDescriptor patientContext;
    @XmlElement(name = "LocationContext")
    protected LocationContextDescriptor locationContext;
    @XmlElement(name = "EnsembleContext")
    protected List<EnsembleContextDescriptor> ensembleContext;
    @XmlElement(name = "OperatorContext")
    protected List<OperatorContextDescriptor> operatorContext;
    @XmlElement(name = "WorkflowContext")
    protected List<WorkflowContextDescriptor> workflowContext;
    @XmlElement(name = "MeansContext")
    protected List<MeansContextDescriptor> meansContext;

    /**
     * Ruft den Wert der patientContext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PatientContextDescriptor }
     *     
     */
    public PatientContextDescriptor getPatientContext() {
        return patientContext;
    }

    /**
     * Legt den Wert der patientContext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientContextDescriptor }
     *     
     */
    public void setPatientContext(PatientContextDescriptor value) {
        this.patientContext = value;
    }

    /**
     * Ruft den Wert der locationContext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LocationContextDescriptor }
     *     
     */
    public LocationContextDescriptor getLocationContext() {
        return locationContext;
    }

    /**
     * Legt den Wert der locationContext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationContextDescriptor }
     *     
     */
    public void setLocationContext(LocationContextDescriptor value) {
        this.locationContext = value;
    }

    /**
     * Gets the value of the ensembleContext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ensembleContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnsembleContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnsembleContextDescriptor }
     * 
     * 
     */
    public List<EnsembleContextDescriptor> getEnsembleContext() {
        if (ensembleContext == null) {
            ensembleContext = new ArrayList<EnsembleContextDescriptor>();
        }
        return this.ensembleContext;
    }

    /**
     * Gets the value of the operatorContext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatorContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperatorContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperatorContextDescriptor }
     * 
     * 
     */
    public List<OperatorContextDescriptor> getOperatorContext() {
        if (operatorContext == null) {
            operatorContext = new ArrayList<OperatorContextDescriptor>();
        }
        return this.operatorContext;
    }

    /**
     * Gets the value of the workflowContext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the workflowContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorkflowContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WorkflowContextDescriptor }
     * 
     * 
     */
    public List<WorkflowContextDescriptor> getWorkflowContext() {
        if (workflowContext == null) {
            workflowContext = new ArrayList<WorkflowContextDescriptor>();
        }
        return this.workflowContext;
    }

    /**
     * Gets the value of the meansContext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meansContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeansContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeansContextDescriptor }
     * 
     * 
     */
    public List<MeansContextDescriptor> getMeansContext() {
        if (meansContext == null) {
            meansContext = new ArrayList<MeansContextDescriptor>();
        }
        return this.meansContext;
    }

}
