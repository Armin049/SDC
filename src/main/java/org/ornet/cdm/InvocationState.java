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
 * <p>Java-Klasse für InvocationState.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="InvocationState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Wait"/>
 *     &lt;enumeration value="Start"/>
 *     &lt;enumeration value="Cnclld"/>
 *     &lt;enumeration value="CnclldMan"/>
 *     &lt;enumeration value="Fin"/>
 *     &lt;enumeration value="FinMod"/>
 *     &lt;enumeration value="Fail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvocationState", namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/message")
@XmlEnum
public enum InvocationState {


    /**
     * Wait = Waiting. The operation has been queued and waits for execution.
     * 
     */
    @XmlEnumValue("Wait")
    WAIT("Wait"),

    /**
     * Start = Started. The execution of the operation has been started.
     * 
     */
    @XmlEnumValue("Start")
    START("Start"),

    /**
     * Cnclld = Cancelled. The execution has been cancelled by the SERVICE PROVIDER.
     * 
     */
    @XmlEnumValue("Cnclld")
    CNCLLD("Cnclld"),

    /**
     * CnclldMan = Cancelled Manually. The execution has been cancelled by the operator.
     * 
     */
    @XmlEnumValue("CnclldMan")
    CNCLLD_MAN("CnclldMan"),

    /**
     * Fin = Finished. The execution has been finished.
     * 
     */
    @XmlEnumValue("Fin")
    FIN("Fin"),

    /**
     * FinMod = Finished with modification. As the requested target value could not be reached, the next best value has been chosen and used as target value.
     * 
     */
    @XmlEnumValue("FinMod")
    FIN_MOD("FinMod"),

    /**
     * The execution has been failed.
     * 
     */
    @XmlEnumValue("Fail")
    FAIL("Fail");
    private final String value;

    InvocationState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvocationState fromValue(String v) {
        for (InvocationState c: InvocationState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
