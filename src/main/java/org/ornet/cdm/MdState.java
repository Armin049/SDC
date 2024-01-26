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
import javax.xml.bind.annotation.XmlType;


/**
 * MdState is the root container to represent the state part of the MDIB. The state part describes the values provided by a POC MEDICAL DEVICE, e.g., which measurement or alert values as well as patient demographics it provides. As the state part most often changes very frequently, it is well-known as the dynamic part of the MDIB. The MdState's counterpart is pm:MdDescription.
 * 
 * <p>Java-Klasse für MdState complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="MdState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="State" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}AbstractState" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StateVersion" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}VersionCounter" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdState", propOrder = {
    "extension",
    "state"
})
public class MdState {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "State")
    protected List<AbstractState> state;
    @XmlAttribute(name = "StateVersion")
    protected Long stateVersion;

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
     * Ruft den Wert der stateVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStateVersion() {
        return stateVersion;
    }

    /**
     * Legt den Wert der stateVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStateVersion(Long value) {
        this.stateVersion = value;
    }

}
