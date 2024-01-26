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
import javax.xml.namespace.QName;


/**
 * Describes an activate operation that is exposed on the SCO. Activate operations are any parameterized operations that trigger an arbitrary action. The action that is triggered SHALL be defined by the pm:AbstractDescriptor/pm:Type ELEMENT.
 * 
 * <p>Java-Klasse für ActivateOperationDescriptor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ActivateOperationDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractSetStateOperationDescriptor">
 *       &lt;sequence>
 *         &lt;element name="Argument" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ArgName" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
 *                   &lt;element name="Arg" type="{http://www.w3.org/2001/XMLSchema}QName"/>
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
@XmlType(name = "ActivateOperationDescriptor", propOrder = {
    "argument"
})
public class ActivateOperationDescriptor
    extends AbstractSetStateOperationDescriptor
{

    @XmlElement(name = "Argument")
    protected List<ActivateOperationDescriptor.Argument> argument;

    /**
     * Gets the value of the argument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the argument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArgument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActivateOperationDescriptor.Argument }
     * 
     * 
     */
    public List<ActivateOperationDescriptor.Argument> getArgument() {
        if (argument == null) {
            argument = new ArrayList<ActivateOperationDescriptor.Argument>();
        }
        return this.argument;
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
     *         &lt;element name="ArgName" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodedValue"/>
     *         &lt;element name="Arg" type="{http://www.w3.org/2001/XMLSchema}QName"/>
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
        "argName",
        "arg"
    })
    public static class Argument {

        @XmlElement(name = "ArgName", required = true)
        protected CodedValue argName;
        @XmlElement(name = "Arg", required = true)
        protected QName arg;

        /**
         * Ruft den Wert der argName-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link CodedValue }
         *     
         */
        public CodedValue getArgName() {
            return argName;
        }

        /**
         * Legt den Wert der argName-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link CodedValue }
         *     
         */
        public void setArgName(CodedValue value) {
            this.argName = value;
        }

        /**
         * Ruft den Wert der arg-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getArg() {
            return arg;
        }

        /**
         * Legt den Wert der arg-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setArg(QName value) {
            this.arg = value;
        }

    }

}
