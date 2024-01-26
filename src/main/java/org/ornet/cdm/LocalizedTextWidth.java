//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für LocalizedTextWidth.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="LocalizedTextWidth">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="xs"/>
 *     &lt;enumeration value="s"/>
 *     &lt;enumeration value="m"/>
 *     &lt;enumeration value="l"/>
 *     &lt;enumeration value="xl"/>
 *     &lt;enumeration value="xxl"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LocalizedTextWidth")
@XmlEnum
public enum LocalizedTextWidth {


    /**
     * A line has 4 or less fullwidth characters.
     * 
     */
    @XmlEnumValue("xs")
    XS("xs"),

    /**
     * A line has 8 or less fullwidth characters.
     * 
     */
    @XmlEnumValue("s")
    S("s"),

    /**
     * A line has 12 or less fullwidth characters.
     * 
     */
    @XmlEnumValue("m")
    M("m"),

    /**
     * A line has 16 or less fullwidth characters.
     * 
     */
    @XmlEnumValue("l")
    L("l"),

    /**
     * A line has 20 or less fullwidth characters.
     * 
     */
    @XmlEnumValue("xl")
    XL("xl"),

    /**
     * A line has 21 or more fullwidth characters.
     * 
     */
    @XmlEnumValue("xxl")
    XXL("xxl");
    private final String value;

    LocalizedTextWidth(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LocalizedTextWidth fromValue(String v) {
        for (LocalizedTextWidth c: LocalizedTextWidth.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
