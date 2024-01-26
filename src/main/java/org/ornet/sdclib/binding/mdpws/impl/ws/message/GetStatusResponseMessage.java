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

public class GetStatusResponseMessage extends AbstractSoapMessage {

    private GetStatusResponseMessage() {
        super("/soap/GetStatusResponse.xml");
    }

    public GetStatusResponseMessage(SOAPMessage content) {
        super(content);
    }

    public GetStatusResponseMessage(GetStatusMessage msg) {
        this();
        initResponse(msg);
    }

    private void initResponse(GetStatusMessage msg) {
        setRelatesTo(msg.getMessageId());
    }

    public String getExpires() {
        return getTextNodeContent("Expires", true);
    }

    public void setExpires(String duration) {
        setTextNodeContent("Expires", duration, true);
    }

}
