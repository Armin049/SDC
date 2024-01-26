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
import org.ornet.sdclib.binding.mdpws.impl.device.MDPWSDevice;

public class ResolveMatchesMessage extends AbstractMatchesMessage {

    public ResolveMatchesMessage(SOAPMessage content) {
        super(content);
    }

    public ResolveMatchesMessage(ResolveMessage resolve, String eprAddr, String xAddrs, MDPWSDevice.AppSequencer appSeq, int metadataVersion) {
        super("/soap/ResolveMatches.xml", resolve, eprAddr, xAddrs, appSeq, metadataVersion);
    }

}
