/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting
 * @Copyright (C) SurgiTAIX AG
 */
package org.ornet.sdclib.binding.mdpws.impl.ws.message;

import javax.xml.soap.SOAPMessage;

public class ProbeMessage extends AbstractSoapMessage {

    public ProbeMessage() {
        super("/soap/Probe.xml");
    }

    public ProbeMessage(SOAPMessage content) {
        super(content);
    }

    public String getTypes() {
        return getTextNodeContent("Types", true);
    }

}
