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

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPMessage;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.SOAPManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMetadataMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetMetadataResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.routes.ServiceCallBuilder.ActionKey;

public class ServiceBaseRoute {

    public void completeResponse(RoutingContext rc, AbstractSoapMessage response, boolean chunked) {
        SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing message, Action: {0}", response.getAction());
        rc.response().setChunked(chunked);
        rc.response().putHeader("Content-Type", "application/soap+xml");
        rc.response().end(response.createBuffer());
    }

    public void configure(Router router, DeviceMsgManager msgManager, String id, String[] types, List<String> serviceHosted, String wsdlPath, String wsdlFile, Map<ActionKey, IServiceCall> concreteRoute) {
        router.route("/" + id + "*").handler(BodyHandler.create());

        router.get("/" + id + wsdlPath + "/" + wsdlFile).handler(rc -> {
            serveResource(rc, wsdlPath + "/" + wsdlFile);
        });
        router.get("/" + id + wsdlPath + "/" + WSConstants.BICEPS_MESSAGE_MODELXSD_FILE).handler(rc -> {
            serveResource(rc, WSConstants.BICEPS_MESSAGE_MODELXSD);
        });
        router.get("/" + id + wsdlPath + "/" + WSConstants.BICEPS_PARTICIPANT_MODELXSD_FILE).handler(rc -> {
            serveResource(rc, WSConstants.BICEPS_PARTICIPANT_MODELXSD);
        });
        router.get("/" + id + wsdlPath + "/" + WSConstants.EXTENSION_POINTXSD_FILE).handler(rc -> {
            serveResource(rc, WSConstants.EXTENSION_POINTXSD);
        });
        router.get("/" + id + wsdlPath + "/" + WSConstants.MDPWS_XSD_FILE).handler(rc -> {
            serveResource(rc, WSConstants.MDPWS_XSD);
        });

        router.post("/" + id + "/*").handler(rc
                -> {
            AbstractSoapMessage asm = new AbstractSoapMessage(rc.getBody());
            if (asm.getAction().equals(WSConstants.GET_META_ACTION)) {
                try {
                    SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming message for service {0}, Action: {1}", new Object[]{id, asm.getAction()});
                    GetMetadataResponseMessage response = msgManager.getDiscoManager().handleGetMetadata(new GetMetadataMessage(asm.getContent()));
                    response.setHostedInformation(id, types, filterTargetAddr(serviceHosted, asm.getTo()));
                    response.setWSDLLocation(wsdlPath + "/" + wsdlFile, id, filterTargetAddr(serviceHosted, asm.getTo()));

                    // Add streaming info in case of (biceps) streaming service
                    if (id.equals(BicepsServiceRoute.REALM)) {
                        response.setStreamingSection();
                    }
                    completeResponse(rc, response, false);
                }
                catch (Exception ex) {
                    SDCLib.getInstance().getLogger().log(Level.SEVERE, null, ex);
                }
            } else if (concreteRoute != null) {
                // Iterate concrete service routes, if provided
                concreteRoute.entrySet().forEach((next) -> {
                    try {
                        if (asm.getAction().equals(next.getKey().getAction())) {
                            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming message for service {0}, Action: {1}", new Object[]{id, asm.getAction()});
                            Class clazz = next.getKey().getActionClass();
                            // Use supertype, but generate concrete instance using declared constructor of desired target class
                            AbstractSoapMessage typedMsg = (AbstractSoapMessage) clazz.getDeclaredConstructor(SOAPMessage.class).newInstance(asm.getContent());
                            next.getValue().apply(rc, typedMsg);
                        }
                    } catch (Exception ex) {
                        asm.createFault(ex.toString());
                        completeResponse(rc, asm, false);
                        SDCLib.getInstance().getLogger().log(Level.WARNING, null, ex);
                    }
                });
            }
        });
    }

    public void serveResource(RoutingContext rc, String resource) {
        rc.response().setChunked(true);
        InputStream is = ServiceBaseRoute.class.getResourceAsStream(resource);
        try {
            rc.response().write(Buffer.buffer(SOAPManager.clearCrLf(is.readAllBytes())));
            rc.response().end();
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceBaseRoute.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<String> filterTargetAddr(List<String> serviceHosted, String to) {
        List<String> singleAddr = new LinkedList<>();
        for (String next : serviceHosted) {
            if (to != null && to.contains(next)) {
                singleAddr.add(next);
                break;
            }
        }
        if (singleAddr.isEmpty()) {
            SDCLib.getInstance().getLogger().log(Level.WARNING, "No service target address could be filtered using target address!");
            singleAddr.addAll(serviceHosted);
        }
        return singleAddr;
    }

}
