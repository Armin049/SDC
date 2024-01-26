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
 * <p>Java-Klasse für ComponentActivation.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="ComponentActivation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="On"/>
 *     &lt;enumeration value="NotRdy"/>
 *     &lt;enumeration value="StndBy"/>
 *     &lt;enumeration value="Off"/>
 *     &lt;enumeration value="Shtdn"/>
 *     &lt;enumeration value="Fail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ComponentActivation")
@XmlEnum
public enum ComponentActivation {


    /**
     * The component is operating.
     * 
     */
    @XmlEnumValue("On")
    ON("On"),

    /**
     * NotRdy = Not Ready. The component is not ready to be operated and not operating, but initialization is ongoing.
     * 
     */
    @XmlEnumValue("NotRdy")
    NOT_RDY("NotRdy"),

    /**
     * StndBy = Stand By. The component is ready to be operated, but not currently operating.
     * 
     */
    @XmlEnumValue("StndBy")
    STND_BY("StndBy"),

    /**
     * The component is inactive.
     * 
     */
    @XmlEnumValue("Off")
    OFF("Off"),

    /**
     * Shtdn = Shutdown. The component is ceasing from being ready to be operated or operating, but not yet inactive.
     * 
     */
    @XmlEnumValue("Shtdn")
    SHTDN("Shtdn"),

    /**
     * Fail = Failure. The component has detected a failure and is not ready to be operated.
     * 
     */
    @XmlEnumValue("Fail")
    FAIL("Fail");
    private final String value;

    ComponentActivation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComponentActivation fromValue(String v) {
        for (ComponentActivation c: ComponentActivation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
