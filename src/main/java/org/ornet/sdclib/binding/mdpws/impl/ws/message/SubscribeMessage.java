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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SubscribeMessage extends AbstractSoapMessage {

    private SubscribeMessage() {
        super("/soap/Subscribe.xml");
    }

    public SubscribeMessage(SOAPMessage content) {
        super(content);
    }

    public SubscribeMessage(String notifyTo, String filter, String expires) {
        this();
        setTextNodeContent("Address", notifyTo, true, true);
        setTextNodeContent("Filter", filter, true);
        setTextNodeContent("Expires", expires, true);
    }

    public String getNotifyToAddr() {
        return getTextNodeContent("Address", true);
    } 
    
    public List<String> getFilters() {
        List<String> filters = new ArrayList<>();
        final String[] arr = getFilterString().split(",");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
            if (!arr[i].isEmpty()) {
                filters.add(arr[i]);
            }
        }
        return filters;
    }

    public String getFilterString() {
        return getTextNodeContent("Filter", true);
    }

    public String getExpires() {
        return getTextNodeContent("Expires", true);
    }

    public void setExpires(String duration) {
        setTextNodeContent("Expires", duration, true);
    }

}
