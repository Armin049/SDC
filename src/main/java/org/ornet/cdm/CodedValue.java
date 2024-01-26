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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * In general, in an interoperability format, objects, attributes, and methods are identified by nomenclature codes. CodedValue offers the ability to represent such nomenclature codes.
 * 
 * Two CodedValue objects C1 and C2 are equivalent, if
 * 
 * - C1/@Code equals C2/@Code
 * - C1/@CodingSystem equals C2/@CodingSystem, both with expanded default values
 * - C1/@CodingSystemVersion equals C2/@CodingSystemVersion
 * - If there exists a CodedValue object T1 in C1/pm:Translation and a CodedValue object T2 in C2/pm:Translation such that T1 and T2 are equivalent, C1 and T2 are equivalent, or C2 and T1 are equivalent.
 * 
 * NOTE 1—In case that ./@CodingSystem is not explicitly defined in CodedValue, it is replaced implicitly by a default identifier. The ./@CodingSystem ATTRIBUTE is then called "expanded". 
 * NOTE 2—As prescribed in ./@CodingSystemVersion, a version is set only if a unique version identification by ./@CodingSystem is not possible. Hence, there can be no implicit version mismatch.
 * NOTE 3—Equivalence between CodedValue objects is not necessarily transitive.
 * 			
 * 
 * <p>Java-Klasse für CodedValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CodedValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *         &lt;element name="CodingSystemName" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ConceptDescription" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}LocalizedText" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Translation" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://standards.ieee.org/downloads/11073/11073-10207-2017/extension}Extension" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Code" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodeIdentifier" />
 *                 &lt;attribute name="CodingSystem" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                 &lt;attribute name="CodingSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Code" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodeIdentifier" />
 *       &lt;attribute name="CodingSystem" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="CodingSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SymbolicCodeName" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}SymbolicCodeName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodedValue", propOrder = {
    "extension",
    "codingSystemName",
    "conceptDescription",
    "translation"
})
public class CodedValue {

    @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
    protected ExtensionType extension;
    @XmlElement(name = "CodingSystemName")
    protected List<LocalizedText> codingSystemName;
    @XmlElement(name = "ConceptDescription")
    protected List<LocalizedText> conceptDescription;
    @XmlElement(name = "Translation")
    protected List<CodedValue.Translation> translation;
    @XmlAttribute(name = "Code", required = true)
    protected String code;
    @XmlAttribute(name = "CodingSystem")
    @XmlSchemaType(name = "anyURI")
    protected String codingSystem;
    @XmlAttribute(name = "CodingSystemVersion")
    protected String codingSystemVersion;
    @XmlAttribute(name = "SymbolicCodeName")
    protected String symbolicCodeName;

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
     * Gets the value of the codingSystemName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codingSystemName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodingSystemName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedText }
     * 
     * 
     */
    public List<LocalizedText> getCodingSystemName() {
        if (codingSystemName == null) {
            codingSystemName = new ArrayList<LocalizedText>();
        }
        return this.codingSystemName;
    }

    /**
     * Gets the value of the conceptDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conceptDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConceptDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedText }
     * 
     * 
     */
    public List<LocalizedText> getConceptDescription() {
        if (conceptDescription == null) {
            conceptDescription = new ArrayList<LocalizedText>();
        }
        return this.conceptDescription;
    }

    /**
     * Gets the value of the translation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the translation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTranslation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedValue.Translation }
     * 
     * 
     */
    public List<CodedValue.Translation> getTranslation() {
        if (translation == null) {
            translation = new ArrayList<CodedValue.Translation>();
        }
        return this.translation;
    }

    /**
     * Ruft den Wert der code-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Legt den Wert der code-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Ruft den Wert der codingSystem-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodingSystem() {
        return codingSystem;
    }

    /**
     * Legt den Wert der codingSystem-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodingSystem(String value) {
        this.codingSystem = value;
    }

    /**
     * Ruft den Wert der codingSystemVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodingSystemVersion() {
        return codingSystemVersion;
    }

    /**
     * Legt den Wert der codingSystemVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodingSystemVersion(String value) {
        this.codingSystemVersion = value;
    }

    /**
     * Ruft den Wert der symbolicCodeName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbolicCodeName() {
        return symbolicCodeName;
    }

    /**
     * Legt den Wert der symbolicCodeName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbolicCodeName(String value) {
        this.symbolicCodeName = value;
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
     *       &lt;/sequence>
     *       &lt;attribute name="Code" use="required" type="{http://standards.ieee.org/downloads/11073/11073-10207-2017/participant}CodeIdentifier" />
     *       &lt;attribute name="CodingSystem" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *       &lt;attribute name="CodingSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "extension"
    })
    public static class Translation {

        @XmlElement(name = "Extension", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension")
        protected ExtensionType extension;
        @XmlAttribute(name = "Code", required = true)
        protected String code;
        @XmlAttribute(name = "CodingSystem")
        @XmlSchemaType(name = "anyURI")
        protected String codingSystem;
        @XmlAttribute(name = "CodingSystemVersion")
        protected String codingSystemVersion;

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
         * Ruft den Wert der code-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Legt den Wert der code-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Ruft den Wert der codingSystem-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodingSystem() {
            return codingSystem;
        }

        /**
         * Legt den Wert der codingSystem-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodingSystem(String value) {
            this.codingSystem = value;
        }

        /**
         * Ruft den Wert der codingSystemVersion-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodingSystemVersion() {
            return codingSystemVersion;
        }

        /**
         * Legt den Wert der codingSystemVersion-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodingSystemVersion(String value) {
            this.codingSystemVersion = value;
        }

    }

}
