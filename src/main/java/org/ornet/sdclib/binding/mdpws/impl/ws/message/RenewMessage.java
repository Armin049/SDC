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
import org.w3c.dom.Node;

public class RenewMessage extends AbstractSoapMessage {

    private RenewMessage() {
        super("/soap/Renew.xml");
    }

    public RenewMessage(String managerAddrTo, String expires) {
        this();
        setTo(managerAddrTo);
        setTextNodeContent("Expires", expires, true);
    }

    public RenewMessage(SOAPMessage content) {
        super(content);
    }

    public String getExpires() {
        return getTextNodeContent("Expires", true);
    }

}
