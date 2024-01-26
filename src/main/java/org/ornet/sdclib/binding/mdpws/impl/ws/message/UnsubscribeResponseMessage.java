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

public class UnsubscribeResponseMessage extends AbstractSoapMessage {

    private UnsubscribeResponseMessage() {
        super("/soap/UnsubscribeResponse.xml");
    }

    public UnsubscribeResponseMessage(SOAPMessage content) {
        super(content);
    }

    public UnsubscribeResponseMessage(UnsubscribeMessage msg) {
        this();
        initResponse(msg);
    }

    private void initResponse(UnsubscribeMessage msg) {
        setRelatesTo(msg.getMessageId());
    }

}
