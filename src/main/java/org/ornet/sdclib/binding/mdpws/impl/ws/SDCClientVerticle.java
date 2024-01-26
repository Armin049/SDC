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
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.impl.client.ClientMsgManager;
import org.ornet.sdclib.binding.mdpws.impl.ws.routes.ClientRoute;

public class SDCClientVerticle extends AbstractVerticle {

    private final ClientMsgManager msgManager;
    private HttpServer server;
    private final HttpServerOptions options;

    public SDCClientVerticle(ClientMsgManager msgManager, HttpServerOptions options) {
        this.msgManager = msgManager;
        this.options = options;
    }

    @Override
    public void start(Promise<Void> fut) {
        Router router = Router.router(this.vertx);
        String host = config().getString("http.host");
        int port = config().getInteger("http.port");

        ClientRoute.configure(router, msgManager);
        options.setHost(host);

        server = this.vertx.createHttpServer(options);
        server.requestHandler(router).listen(port, (AsyncResult<HttpServer> e) -> {
            SDCLib.getInstance().getLogger().log(Level.FINE, "Client HTTP server {0} running: {1}", new Object[]{host + ":" + port, e.succeeded()});
            fut.complete();
        });
    }

    @Override
    public void stop(Promise<Void> fut) throws Exception {
        if (server != null) {
            server.close();
        }
        SDCLib.getInstance().getLogger().log(Level.FINE, "Client HTTP server stopped.");
        fut.complete();
    }

}
