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
 * <p>Java-Klasse für CalibrationState.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="CalibrationState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="No"/>
 *     &lt;enumeration value="Req"/>
 *     &lt;enumeration value="Run"/>
 *     &lt;enumeration value="Cal"/>
 *     &lt;enumeration value="Oth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CalibrationState")
@XmlEnum
public enum CalibrationState {


    /**
     * No = Not Calibrated. Defines that a component is not calibrated.
     * 
     */
    @XmlEnumValue("No")
    NO("No"),

    /**
     * Req = Calibration Required. Defines that a component requires a calibration.
     * 
     */
    @XmlEnumValue("Req")
    REQ("Req"),

    /**
     * Run = Running. Defines that a calibration for a component is running.
     * 
     */
    @XmlEnumValue("Run")
    RUN("Run"),

    /**
     * Cal = Calibrated. Defines that a component is calibrated.
     * 
     */
    @XmlEnumValue("Cal")
    CAL("Cal"),

    /**
     * Oth = Other. The calibration state is defined by other means.
     * 
     */
    @XmlEnumValue("Oth")
    OTH("Oth");
    private final String value;

    CalibrationState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CalibrationState fromValue(String v) {
        for (CalibrationState c: CalibrationState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
