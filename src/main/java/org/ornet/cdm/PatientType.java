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
 * <p>Java-Klasse für PatientType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="PatientType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unspec"/>
 *     &lt;enumeration value="Ad"/>
 *     &lt;enumeration value="Ado"/>
 *     &lt;enumeration value="Ped"/>
 *     &lt;enumeration value="Inf"/>
 *     &lt;enumeration value="Neo"/>
 *     &lt;enumeration value="Oth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PatientType")
@XmlEnum
public enum PatientType {


    /**
     * Unspec = Unspecified. Unspecified type.
     * 
     */
    @XmlEnumValue("Unspec")
    UNSPEC("Unspec"),

    /**
     * Ad = Adult. Indicates an adult patient.
     * 
     */
    @XmlEnumValue("Ad")
    AD("Ad"),

    /**
     * Ado = Adolescent. Indicates an adolescent patient with approximate age range of 12 years to 21 years.
     * 
     */
    @XmlEnumValue("Ado")
    ADO("Ado"),

    /**
     * Ped = Pediatric. Indicates a pediatric patient with approximate age range of 2 years to 12 years.
     * 
     */
    @XmlEnumValue("Ped")
    PED("Ped"),

    /**
     * Inf = Infant. Indicates an infant patient with approximate age range of 1 month to 2 years.
     * 
     */
    @XmlEnumValue("Inf")
    INF("Inf"),

    /**
     * Neo = Neonatal. Indicates a neonatal patient with approximate age range of birth to 1 month.
     * 
     */
    @XmlEnumValue("Neo")
    NEO("Neo"),

    /**
     * Oth = Other. The patient type is designated by some other means.
     * 
     */
    @XmlEnumValue("Oth")
    OTH("Oth");
    private final String value;

    PatientType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientType fromValue(String v) {
        for (PatientType c: PatientType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
