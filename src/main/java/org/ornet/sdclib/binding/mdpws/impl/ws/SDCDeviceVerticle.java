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
package org.ornet.sdclib.binding.mdpws.impl.ws;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.device.DeviceMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.routes.*;

public class SDCDeviceVerticle extends AbstractVerticle {

    private final DeviceMsgManager msgManager;
    private HttpServer server;
    private final Map<String[], List<String>> serviceHosted = new LinkedHashMap<>();
    private final List<String> serviceAddrs = new ArrayList<>();
    private final HttpServerOptions options;

    private DeviceRoute deviceRoute = new DeviceRoute();
    private GetServiceRoute getServiceRoute = new GetServiceRoute();
    private SetServiceRoute setServiceRoute = new SetServiceRoute();
    private BicepsServiceRoute bicepsServiceRoute = new BicepsServiceRoute();
    private DescServiceRoute descServiceRoute = new DescServiceRoute();
    private String host;
    private int port;
    private Router router;

    public SDCDeviceVerticle(DeviceMsgManager msgManager, HttpServerOptions options) {
        this.msgManager = msgManager;
        this.options = options;
    }

    @Override
    public void start(Promise<Void> fut) {
        router = Router.router(this.vertx);
        host = config().getString("http.host");
        port = config().getInteger("http.port");
        deviceRoute = new DeviceRoute();
        getServiceRoute = new GetServiceRoute();
        setServiceRoute = new SetServiceRoute();
        bicepsServiceRoute = new BicepsServiceRoute();
        descServiceRoute = new DescServiceRoute();

        final String http = options.isSsl() ? "https://" : "http://";
        new NIFManager(host, true, true).applyVisitor((NetworkInterface ni, InetAddress addr, String addrStr) -> {
            final String hostedAdr = http + addrStr + ":" + port;
            SDCLib.getInstance().getLogger().log(Level.FINE, "Configuring hosted service address: {0}", hostedAdr);
            serviceAddrs.add(hostedAdr);
        });

        serviceHosted.put(joinIdTypesArray(GetServiceRoute.REALM, GetServiceRoute.TYPES), serviceAddrs);
        serviceHosted.put(joinIdTypesArray(SetServiceRoute.REALM, SetServiceRoute.TYPES), serviceAddrs);
        serviceHosted.put(joinIdTypesArray(BicepsServiceRoute.REALM, BicepsServiceRoute.TYPES), serviceAddrs);
        serviceHosted.put(joinIdTypesArray(DescServiceRoute.REALM, DescServiceRoute.TYPES), serviceAddrs);

        deviceRoute.configure(router, msgManager, serviceHosted);
        getServiceRoute.configure(router, msgManager, serviceAddrs, WSConstants.WSDL_RESOURCE_BASE_PATH, GetServiceRoute.WSDL);
        setServiceRoute.configure(router, msgManager, serviceAddrs, WSConstants.WSDL_RESOURCE_BASE_PATH, SetServiceRoute.WSDL);
        bicepsServiceRoute.configure(router, msgManager, serviceAddrs, WSConstants.WSDL_RESOURCE_BASE_PATH, BicepsServiceRoute.WSDL);
        descServiceRoute.configure(router, msgManager, serviceAddrs, WSConstants.WSDL_RESOURCE_BASE_PATH, DescServiceRoute.WSDL);

        options.setHost(host);
        options.setPort(port);

        server = this.vertx.createHttpServer(options);
        server.requestHandler(router).listen((AsyncResult<HttpServer> e) -> {
            SDCLib.getInstance().getLogger().log(Level.INFO, "Device HTTP server {0} running: {1}", new Object[]{http + ":" + host + ":" + port, e.succeeded()});
            fut.complete();
        });
    }

    private String[] joinIdTypesArray(String id, String[] types) {
        String[] idTypes = new String[types.length + 1];
        idTypes[0] = id;
        for (int i = 0; i < types.length; i++) {
            idTypes[i + 1] = types[i];
        }
        return idTypes;
    }

    @Override
    public void stop(Promise<Void> fut) throws Exception {
        if (server != null) {
            server.close();
        }
        serviceHosted.clear();
        serviceAddrs.clear();
        deviceRoute = null;
        getServiceRoute = null;
        setServiceRoute = null;
        bicepsServiceRoute = null;
        descServiceRoute = null;
        SDCLib.getInstance().getLogger().log(Level.FINE, "Device HTTP server stopped.");
        fut.complete();
    }

}
