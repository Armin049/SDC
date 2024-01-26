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

import io.vertx.core.buffer.Buffer;
import javax.xml.soap.SOAPMessage;

public class InvokeResponseMessage extends AbstractInvokeMessage {

    private InvokeResponseMessage() {
        super("/soap/InvokeResponse.xml");
    }

    public InvokeResponseMessage(SOAPMessage content) {
        super(content);
    }

    public InvokeResponseMessage(Buffer buf) {
        super(buf);
    }

    public InvokeResponseMessage(InvokeMessage msg, String action) {
        this();
        initResponse(msg, action);
    }

    private void initResponse(InvokeMessage msg, String action) {
        setAction(action);
        setRelatesTo(msg.getMessageId());
    }

}
