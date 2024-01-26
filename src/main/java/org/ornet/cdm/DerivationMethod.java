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
 * <p>Java-Klasse für DerivationMethod.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="DerivationMethod">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Auto"/>
 *     &lt;enumeration value="Man"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DerivationMethod")
@XmlEnum
public enum DerivationMethod {


    /**
     * Auto = Automatic derivation. The METRIC value is derived by an automatic mechanism (e.g., electronically measured).
     * 
     */
    @XmlEnumValue("Auto")
    AUTO("Auto"),

    /**
     * Man = Manual derivation. The METRIC is derived manually by a clinican/human.
     * 
     */
    @XmlEnumValue("Man")
    MAN("Man");
    private final String value;

    DerivationMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DerivationMethod fromValue(String v) {
        for (DerivationMethod c: DerivationMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
