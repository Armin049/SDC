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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * LocalizedText is a bundled ELEMENT to reference texts in different languages or to provide a text in a specific language.
 * 
 * The goal of text references is to shrink the overall size of the MDIB by only providing a single reference to a text file that translates a text into multiple languages instead of flooding the MDIB with all translated texts. Referenced texts can be requested by the LOCALIZATION SERVICE. If no LOCALIZATION SERVICE exist, the application can make use of LocalizedText to represent a text in a single language.
 * 
 * __R5047: If ./@Lang and ./@Ref are present, then the text SHALL be only available in the language specified by ./@Lang.__
 * 
 * __R5048: If ./@Lang is present and ./@Ref is not present, then ./@Lang SHALL specify the language of the LocalizedText's content. The Text is not available through the LOCALIZATION SERVICE.__
 * 
 * __R5049: If ./@Lang is not present and ./@Ref is present, then the text SHALL be available through the LOCALIZATION SERVICE.__
 * 
 * __R5050: If ./@Lang and ./@Ref are not present, then the language of the LocalizedText's content is unknown. The text SHALL NOT be available through the LOCALIZATION SERVICE.__
 * 
 * <p>Java-Klasse für LocalizedText complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="LocalizedText">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://standards.ieee.org/downloads/11073/11073-10207-2017/participant>LocalizedTextContent">
 *       &lt;attribute name="Ref" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedTextRef" />
 *       &lt;attribute name="Lang" type="{http://www.w3.org/2001/XMLSchema}language" />
 *       &lt;attribute name="Version" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}ReferencedVersion" />
 *       &lt;attribute name="TextWidth" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedTextWidth" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocalizedText", propOrder = {
    "value"
})
public class LocalizedText {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Ref")
    protected String ref;
    @XmlAttribute(name = "Lang")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;
    @XmlAttribute(name = "Version")
    protected Long version;
    @XmlAttribute(name = "TextWidth")
    protected LocalizedTextWidth textWidth;

    /**
     * Content restriction for pm:LocalizedText ELEMENTs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Legt den Wert der value-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Ruft den Wert der ref-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
        return ref;
    }

    /**
     * Legt den Wert der ref-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
        this.ref = value;
    }

    /**
     * Ruft den Wert der lang-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Legt den Wert der lang-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
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
     * Ruft den Wert der textWidth-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedTextWidth }
     *     
     */
    public LocalizedTextWidth getTextWidth() {
        return textWidth;
    }

    /**
     * Legt den Wert der textWidth-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedTextWidth }
     *     
     */
    public void setTextWidth(LocalizedTextWidth value) {
        this.textWidth = value;
    }

}
