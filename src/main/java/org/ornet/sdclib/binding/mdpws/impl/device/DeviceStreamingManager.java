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
package org.ornet.sdclib.binding.mdpws.impl.device;

import java.util.logging.Level;
import org.ornet.cdm.WaveformStream;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPOverUDPManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.StreamMessage;

public class DeviceStreamingManager {

    public static final String TARGET_NAMESPACE = WSConstants.BINDING_NAMESPACE + "/WaveformService";
    public static final String ACTION_URI = WSConstants.BINDING_NAMESPACE + "/WaveformService/WaveformStream";

    public static final String STREAM_TYPE = "http://docs.oasis-open.org/ws-dd/soapoverudp/1.1/os/wsdd-soapoverudp-1.1-spec-os.html";

    public static final String MCAST_IP = "239.239.239.235";
    public static final int MCAST_PORT = 5555;
    public static final String PROTOCOL_PREFIX = "soap.udp://";

    private final SOAPOverUDPManager udpManager;
    private final String bindInterface;
    private final MDPWSDevice.AppSequencer appSeq;

    public DeviceStreamingManager(SOAPOverUDPManager udpManager, String bindInterface, MDPWSDevice.AppSequencer appSeq) {
        this.udpManager = udpManager;
        this.bindInterface = bindInterface;
        this.appSeq = appSeq;
    }

    public void sendStream(WaveformStream stream) {
        StreamMessage msg = new StreamMessage(stream, MCAST_IP, Integer.toString(MCAST_PORT), appSeq.getInstanceId(), appSeq.getNextMsgNo());
        udpManager.send(bindInterface, MCAST_IP, MCAST_PORT, msg, null);
        SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing stream packet, Action: {0}", msg.getAction());
    }

}
