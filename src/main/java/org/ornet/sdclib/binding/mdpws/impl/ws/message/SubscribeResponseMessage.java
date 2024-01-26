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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.Node;

public class SubscribeResponseMessage extends AbstractSoapMessage {

    private SubscribeResponseMessage() {
        super("/soap/SubscribeResponse.xml");
    }

    public SubscribeResponseMessage(SOAPMessage content) {
        super(content);
    }

    public SubscribeResponseMessage(SubscribeMessage msg) {
        this();
        initResponse(msg);
    }

    public SubscribeResponseMessage(Buffer buffer) {
        super(buffer);
    }

    private void initResponse(SubscribeMessage msg) {
        setRelatesTo(msg.getMessageId());
        if(msg.getExpires() == null) {
            setExpires("PT1M");
        }
        else {
            setExpires(msg.getExpires());
        }
    }

    public String getExpires() {
        return getTextNodeContent("Expires", true);
    }

    public void setExpires(String duration) {
        setTextNodeContent("Expires", duration, true);
    }

    public String getManagerAddr() {
        return getTextNodeContent("Address", true);
    }

    public void setManagerAddr(String addr) {
        setTextNodeContent("Address", addr, true);
    }

}
