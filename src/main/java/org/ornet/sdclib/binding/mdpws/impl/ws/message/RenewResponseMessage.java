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

public class RenewResponseMessage extends AbstractSoapMessage {

    private RenewResponseMessage() {
        super("/soap/RenewResponse.xml");
    }

    public RenewResponseMessage(SOAPMessage content) {
        super(content);
    }

    public RenewResponseMessage(RenewMessage msg) {
        this();
        initResponse(msg);
    }

    private void initResponse(RenewMessage msg) {
        setRelatesTo(msg.getMessageId());
        setTextNodeContent("Expires", msg.getExpires(), true);
    }

    public String getExpires() {
        return getTextNodeContent("Expires", true);
    }

}
