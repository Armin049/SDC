//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


/**
 * Abstract description of an operation that is exposed on the SCO.
 * 
 * <p>Java-Klasse für AbstractOperationDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractOperationDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractDescriptor">
 *       &lt;attribute name="OperationTarget" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}HandleRef" />
 *       &lt;attribute name="MaxTimeToFinish" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="InvocationEffectiveTimeout" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="Retriggerable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AccessLevel">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Usr"/>
 *             &lt;enumeration value="CSUsr"/>
 *             &lt;enumeration value="RO"/>
 *             &lt;enumeration value="SP"/>
 *             &lt;enumeration value="Oth"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractOperationDescriptor")
@XmlSeeAlso({
    SetValueOperationDescriptor.class,
    SetStringOperationDescriptor.class,
    AbstractSetStateOperationDescriptor.class
})
public class AbstractOperationDescriptor
    extends AbstractDescriptor
{

    @XmlAttribute(name = "OperationTarget", required = true)
    protected String operationTarget;
    @XmlAttribute(name = "MaxTimeToFinish")
    protected Duration maxTimeToFinish;
    @XmlAttribute(name = "InvocationEffectiveTimeout")
    protected Duration invocationEffectiveTimeout;
    @XmlAttribute(name = "Retriggerable")
    protected Boolean retriggerable;
    @XmlAttribute(name = "AccessLevel")
    protected String accessLevel;

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

    /**
     * Ruft den Wert der maxTimeToFinish-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMaxTimeToFinish() {
        return maxTimeToFinish;
    }

    /**
     * Legt den Wert der maxTimeToFinish-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMaxTimeToFinish(Duration value) {
        this.maxTimeToFinish = value;
    }

    /**
     * Ruft den Wert der invocationEffectiveTimeout-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getInvocationEffectiveTimeout() {
        return invocationEffectiveTimeout;
    }

    /**
     * Legt den Wert der invocationEffectiveTimeout-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setInvocationEffectiveTimeout(Duration value) {
        this.invocationEffectiveTimeout = value;
    }

    /**
     * Ruft den Wert der retriggerable-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRetriggerable() {
        return retriggerable;
    }

    /**
     * Legt den Wert der retriggerable-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRetriggerable(Boolean value) {
        this.retriggerable = value;
    }

    /**
     * Ruft den Wert der accessLevel-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * Legt den Wert der accessLevel-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessLevel(String value) {
        this.accessLevel = value;
    }

}
