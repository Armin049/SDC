/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting
 * @Copyright (C) Andreas Besting
 */
package org.ornet.sdclib.restapi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;

public class RestApiVerticle extends AbstractVerticle {

    private HttpServer server;
    private final HttpServerOptions options;

    private RestApiRoute restApiRoute = new RestApiRoute();
    private String host;
    private int port;
    private Router router;

    public RestApiVerticle(HttpServerOptions options) {
        this.options = options;
    }

    @Override
    public void start(Promise<Void> fut) {
        router = Router.router(this.vertx);
        host = config().getString("http.host");
        port = config().getInteger("http.port");

        final String http = options.isSsl() ? "https://" : "http://";
        restApiRoute.configure(router);
        
        options.setHost(host);
        options.setPort(port);

        server = this.vertx.createHttpServer(options);
        server.requestHandler(router).listen((AsyncResult<HttpServer> e) -> {
            SDCLib.getInstance().getLogger().log(Level.INFO, "SDC REST API server {0} running: {1}", new Object[]{http + ":" + host + ":" + port, e.succeeded()});
            fut.complete();
        });
    }

    @Override
    public void stop(Promise<Void> fut) throws Exception {
        if (server != null) {
            server.close();
        }
        restApiRoute = null;
        SDCLib.getInstance().getLogger().log(Level.FINE, "REST API server stopped.");
        fut.complete();
    }

}
