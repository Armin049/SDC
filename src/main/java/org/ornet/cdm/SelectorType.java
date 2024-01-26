//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;


/**
 * XPath expression that points to a specific text value or attribute in an XML structure. The following rules SHALL apply:
 * 
 *  1. {Path} must be a valid XPath expression, as defined in XPath [http://www.w3.org/TR/1999/REC-xpath-19991116].
 *  2. {Path} must conform to the following extended BNF:
 * 
 * [1] Path       ::=  ( '/' Step )* '/' ( '@' Name | 'text()' )
 * [2] Step       ::=  Name ('[' Expr ']')*
 * [3] Expr       ::=  '@' Name '=' ( Number | Literal | ConcatCall) | Number
 * [4] Name       ::=  QName | NCName
 * [5] Literal    ::=  '"' [^"]* '"' | "'" [^']* "'" 
 * [6] ConcatCall ::=  concat '(' ( Literal ( ',' Literal )* )? ')'	
 * [7] Number     ::=  Digits ( '.' Digits? )?
 * [8] Digits     ::=  [0-9]+
 * 
 * where
 * - QName is defined in: http://www.w3.org/TR/REC-xml-names/#NT-QName
 * - NCName is defined in: http://www.w3.org/TR/REC-xml-names/#NT-NCName
 * 
 * Samples including allowed features:
 * /ns:Foo[@FooAttr='sample']/Bar[21]/text()
 * /Foo[@FooAttr="sample"]/ns:Bar/@BarAttr
 * 
 * <p>Java-Klasse für SelectorType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SelectorType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute processContents='skip' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectorType", namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", propOrder = {
    "value"
})
public class SelectorType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Id", required = true)
    protected String id;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Ruft den Wert der value-Eigenschaft ab.
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
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
