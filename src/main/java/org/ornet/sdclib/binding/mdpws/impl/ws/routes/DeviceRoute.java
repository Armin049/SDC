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
package org.ornet.sdclib.binding.mdpws.impl.ws.routes;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMatchesMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ProbeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ResolveMatchesMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.ResolveMessage;

public class DeviceRoute {

    public static final String REALM = "Device";

    public void configure(Router router, DeviceMsgManager msgManager, Map<String[], List<String>> serviceAddrs) {
        router.route("/" + REALM + "*").handler(BodyHandler.create());
        router.post("/" + REALM).handler(rc -> {
            AbstractSoapMessage asm = new AbstractSoapMessage(rc.getBody());
            String epr = msgManager.getDiscoManager().getEprAddr();
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming message for device {0}, Action: {1}", new Object[]{epr, asm.getAction()});
            switch (asm.getAction()) {
                case WSConstants.GET_ACTION: {
                    GetResponseMessage response = msgManager.getDiscoManager().handleGet(new GetMessage(asm.getContent()));
                    response.setHostedInformation(serviceAddrs);
                    rc.response().putHeader("Content-Type", "application/soap+xml");
                    rc.response().end(response.createBuffer());
                    break;
                }
                case WSConstants.PROBE_ACTION: {
                    ProbeMatchesMessage response = msgManager.getDiscoManager().handleProbe(new ProbeMessage(asm.getContent()));
                    if (response == null) {
                        return;
                    }
                    rc.response().putHeader("Content-Type", "application/soap+xml");
                    rc.response().end(response.createBuffer());
                    break;
                }
                case WSConstants.RESOLVE_ACTION: {
                    ResolveMatchesMessage response = msgManager.getDiscoManager().handleResolve(new ResolveMessage(asm.getContent()));
                    if (response == null) {
                        return;
                    }
                    rc.response().putHeader("Content-Type", "application/soap+xml");
                    rc.response().end(response.createBuffer());
                    break;
                }
            }
        });
    }

}
