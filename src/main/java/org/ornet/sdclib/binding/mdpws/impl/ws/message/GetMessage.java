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

public class GetMessage extends AbstractSoapMessage {

    private GetMessage() {
        super("/soap/Get.xml");
    }

    public GetMessage(String toEpr) {
        this();
        setTo(toEpr);
    }

    public GetMessage(SOAPMessage content) {
        super(content);
    }

}
