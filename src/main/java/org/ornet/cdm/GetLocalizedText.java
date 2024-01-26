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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://standards.ieee.org/downloads/11073/11073-10207-2017/message}AbstractGet">
 *       &lt;sequence>
 *         &lt;element name="Ref" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedTextRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Version" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ReferencedVersion" minOccurs="0"/>
 *         &lt;element name="Lang" type="{http://www.w3.org/2001/XMLSchema}language" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TextWidth" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedTextWidth" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="NumberOfLines" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/>
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
    "ref",
    "version",
    "lang",
    "textWidth",
    "numberOfLines"
})
@XmlRootElement(name = "GetLocalizedText", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
public class GetLocalizedText
    extends AbstractGet
{

    @XmlElement(name = "Ref", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    protected List<String> ref;
    @XmlElement(name = "Version", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    @XmlSchemaType(name = "unsignedLong")
    protected Long version;
    @XmlElement(name = "Lang", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected List<String> lang;
    @XmlElement(name = "TextWidth", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    @XmlSchemaType(name = "string")
    protected List<LocalizedTextWidth> textWidth;
    @XmlElement(name = "NumberOfLines", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
    protected List<Long> numberOfLines;

    /**
     * Gets the value of the ref property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ref property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRef() {
        if (ref == null) {
            ref = new ArrayList<String>();
        }
        return this.ref;
    }

    /**
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVersion(Long value) {
        this.version = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lang property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLang().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLang() {
        if (lang == null) {
            lang = new ArrayList<String>();
        }
        return this.lang;
    }

    /**
     * Gets the value of the textWidth property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textWidth property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextWidth().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedTextWidth }
     * 
     * 
     */
    public List<LocalizedTextWidth> getTextWidth() {
        if (textWidth == null) {
            textWidth = new ArrayList<LocalizedTextWidth>();
        }
        return this.textWidth;
    }

    /**
     * Gets the value of the numberOfLines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numberOfLines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumberOfLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getNumberOfLines() {
        if (numberOfLines == null) {
            numberOfLines = new ArrayList<Long>();
        }
        return this.numberOfLines;
    }

}
