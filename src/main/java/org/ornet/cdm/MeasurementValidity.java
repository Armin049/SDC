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
 * <p>Java-Klasse für MeasurementValidity.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="MeasurementValidity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Vld"/>
 *     &lt;enumeration value="Vldated"/>
 *     &lt;enumeration value="Ong"/>
 *     &lt;enumeration value="Qst"/>
 *     &lt;enumeration value="Calib"/>
 *     &lt;enumeration value="Inv"/>
 *     &lt;enumeration value="Oflw"/>
 *     &lt;enumeration value="Uflw"/>
 *     &lt;enumeration value="NA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MeasurementValidity")
@XmlEnum
public enum MeasurementValidity {


    /**
     * Vld = Valid. A measured value that is correct from the perspective of the measuring device.
     * 
     */
    @XmlEnumValue("Vld")
    VLD("Vld"),

    /**
     * Vldated = Validated Data. A measured value where the validity has been confirmed by an external actor, e.g., an operator, other than the POC MEDICAL DEVICE.
     * 
     */
    @XmlEnumValue("Vldated")
    VLDATED("Vldated"),

    /**
     * Ong = Measurement Ongoing. Indicates that a new measurement is just being taken and therefore measured value is not available.
     * 
     */
    @XmlEnumValue("Ong")
    ONG("Ong"),

    /**
     * Qst = Questionable. A measured value where correctness can not be guaranteed.
     * 
     */
    @XmlEnumValue("Qst")
    QST("Qst"),

    /**
     * Calib = Calibration Ongoing. A measured value where correctness can not be guaranteed, because a calibration is currently going on.
     * 
     */
    @XmlEnumValue("Calib")
    CALIB("Calib"),

    /**
     * Inv = Invalid. A measured value that is incorrect from the perspective of the measuring device.
     * 
     */
    @XmlEnumValue("Inv")
    INV("Inv"),

    /**
     * Oflw = Overflow. A measured value where correctness cannot be guaranteed as it is above all defined technical ranges.
     * 
     */
    @XmlEnumValue("Oflw")
    OFLW("Oflw"),

    /**
     * Uflw = Underflow. A measured value where correctness cannot be guaranteed as it is below all defined technical ranges.
     * 
     */
    @XmlEnumValue("Uflw")
    UFLW("Uflw"),

    /**
     * NA = Not Available. No value can be derived, e.g., if a sensor is not placed correctly.
     * 
     */
    NA("NA");
    private final String value;

    MeasurementValidity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeasurementValidity fromValue(String v) {
        for (MeasurementValidity c: MeasurementValidity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
