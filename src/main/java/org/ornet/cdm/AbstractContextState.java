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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Base type of a context state. Every context state can be identified as valid by a validator instance. Moreover, a context state's lifecycle is determined by a start and end. AbstractContextState bundles these information.
 * 
 * <p>Java-Klasse für AbstractContextState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractContextState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractMultiState">
 *       &lt;sequence>
 *         &lt;element name="Validator" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Identification" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}InstanceIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ContextAssociation" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ContextAssociation" />
 *       &lt;attribute name="BindingMdibVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ReferencedVersion" />
 *       &lt;attribute name="UnbindingMdibVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ReferencedVersion" />
 *       &lt;attribute name="BindingStartTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *       &lt;attribute name="BindingEndTime" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}Timestamp" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractContextState", propOrder = {
    "validator",
    "identification"
})
@XmlSeeAlso({
    LocationContextState.class,
    EnsembleContextState.class,
    OperatorContextState.class,
    MeansContextState.class,
    PatientContextState.class,
    WorkflowContextState.class
})
public class AbstractContextState
    extends AbstractMultiState
{

    @XmlElement(name = "Validator")
    protected List<InstanceIdentifier> validator;
    @XmlElement(name = "Identification")
    protected List<InstanceIdentifier> identification;
    @XmlAttribute(name = "ContextAssociation")
    protected ContextAssociation contextAssociation;
    @XmlAttribute(name = "BindingMdibVersion")
    protected Long bindingMdibVersion;
    @XmlAttribute(name = "UnbindingMdibVersion")
    protected Long unbindingMdibVersion;
    @XmlAttribute(name = "BindingStartTime")
    protected Long bindingStartTime;
    @XmlAttribute(name = "BindingEndTime")
    protected Long bindingEndTime;

    /**
     * Gets the value of the validator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceIdentifier }
     * 
     * 
     */
    public List<InstanceIdentifier> getValidator() {
        if (validator == null) {
            validator = new ArrayList<InstanceIdentifier>();
        }
        return this.validator;
    }

    /**
     * Gets the value of the identification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceIdentifier }
     * 
     * 
     */
    public List<InstanceIdentifier> getIdentification() {
        if (identification == null) {
            identification = new ArrayList<InstanceIdentifier>();
        }
        return this.identification;
    }

    /**
     * Ruft den Wert der contextAssociation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ContextAssociation }
     *     
     */
    public ContextAssociation getContextAssociation() {
        return contextAssociation;
    }

    /**
     * Legt den Wert der contextAssociation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ContextAssociation }
     *     
     */
    public void setContextAssociation(ContextAssociation value) {
        this.contextAssociation = value;
    }

    /**
     * Ruft den Wert der bindingMdibVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBindingMdibVersion() {
        return bindingMdibVersion;
    }

    /**
     * Legt den Wert der bindingMdibVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBindingMdibVersion(Long value) {
        this.bindingMdibVersion = value;
    }

    /**
     * Ruft den Wert der unbindingMdibVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUnbindingMdibVersion() {
        return unbindingMdibVersion;
    }

    /**
     * Legt den Wert der unbindingMdibVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUnbindingMdibVersion(Long value) {
        this.unbindingMdibVersion = value;
    }

    /**
     * Ruft den Wert der bindingStartTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBindingStartTime() {
        return bindingStartTime;
    }

    /**
     * Legt den Wert der bindingStartTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBindingStartTime(Long value) {
        this.bindingStartTime = value;
    }

    /**
     * Ruft den Wert der bindingEndTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBindingEndTime() {
        return bindingEndTime;
    }

    /**
     * Legt den Wert der bindingEndTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBindingEndTime(Long value) {
        this.bindingEndTime = value;
    }

}
