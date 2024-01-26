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

import org.w3c.dom.Node;

import javax.xml.soap.SOAPMessage;

public class GetStatusMessage extends AbstractSoapMessage {

    private GetStatusMessage() {
        super("/soap/GetStatus.xml");
    }

    public GetStatusMessage(Object identifier) {
        this();
        if (identifier instanceof Node)
            appendToSoapHeader((Node)identifier);
        else if (identifier instanceof String)
            setTo((String)identifier);
    }

    public GetStatusMessage(SOAPMessage content) {
        super(content);
    }

}
