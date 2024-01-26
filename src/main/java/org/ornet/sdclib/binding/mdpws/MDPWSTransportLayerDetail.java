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
package org.ornet.sdclib.binding.mdpws;

import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServerOptions;
import java.util.concurrent.atomic.AtomicInteger;

public class MDPWSTransportLayerDetail {

    private final AtomicInteger portStart = new AtomicInteger(6463);
    private String bindInterface = "0.0.0.0";
    private final HttpServerOptions serverOptions;
    private final HttpClientOptions clientOptions;
    private int communicationTimeout = 5000;
    private int timeToLive = 5;

    public MDPWSTransportLayerDetail() {
        serverOptions = new HttpServerOptions();
        serverOptions.setTcpKeepAlive(true);
        clientOptions = new HttpClientOptions();
        clientOptions.setKeepAlive(true);
    }

    public int extractNextPort() {
        return portStart.incrementAndGet();
    }

    public void setPortStart(int portStart) {
        this.portStart.set(portStart);
    }

    public void setBindInterface(String bindInterface) {
        this.bindInterface = bindInterface;
    }

    public String getBindInterface() {
        return bindInterface;
    }

    public int getCommTimeout() {
        return communicationTimeout;
    }

    public void setCommTimeout(int communicationTimeout) {
        this.communicationTimeout = communicationTimeout;
    }

    public HttpServerOptions getServerOptions() {
        return serverOptions;
    }

    public HttpClientOptions getClientOptions() {
        return clientOptions;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

}
