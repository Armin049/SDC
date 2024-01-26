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
 * <p>Java-Klasse für GenerationMode.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="GenerationMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Real"/>
 *     &lt;enumeration value="Test"/>
 *     &lt;enumeration value="Demo"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GenerationMode")
@XmlEnum
public enum GenerationMode {


    /**
     * Real = Real Data. A value that is generated under real conditions.
     * 
     */
    @XmlEnumValue("Real")
    REAL("Real"),

    /**
     * Test = Test Data. A value that is arbitrary and is for testing purposes only.
     * 
     */
    @XmlEnumValue("Test")
    TEST("Test"),

    /**
     * Demo = Demo Data. A value that is arbitrary and is for demonstration purposes only.
     * 
     */
    @XmlEnumValue("Demo")
    DEMO("Demo");
    private final String value;

    GenerationMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GenerationMode fromValue(String v) {
        for (GenerationMode c: GenerationMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
