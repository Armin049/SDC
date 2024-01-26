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
 * <p>Java-Klasse für InvocationError.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="InvocationError">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unspec"/>
 *     &lt;enumeration value="Unkn"/>
 *     &lt;enumeration value="Inv"/>
 *     &lt;enumeration value="Oth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvocationError", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
@XmlEnum
public enum InvocationError {


    /**
     * Unspec = Unspecified. An unspecified error has occurred. No more information about the error is available.
     * 
     */
    @XmlEnumValue("Unspec")
    UNSPEC("Unspec"),

    /**
     * Unkn = Unknown Operation. The HANDLE to the operation object is not known.
     * 
     */
    @XmlEnumValue("Unkn")
    UNKN("Unkn"),

    /**
     * Inv = Invalid Value. The HANDLE to the operation object does not match the invocation request MESSAGE.
     * 
     * Example: if a msg:SetString MESSAGE is received, in which the msg:SetString/msg:OperationHandleRef points to a msg:SetValue MESSAGE, the receiver replies with InvocationError "Inv".
     * 
     */
    @XmlEnumValue("Inv")
    INV("Inv"),

    /**
     * Oth = Other. Another type of error has occurred. More information on the error MAY be available.
     * 
     */
    @XmlEnumValue("Oth")
    OTH("Oth");
    private final String value;

    InvocationError(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvocationError fromValue(String v) {
        for (InvocationError c: InvocationError.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
