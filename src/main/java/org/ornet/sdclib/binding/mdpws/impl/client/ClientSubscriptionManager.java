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
package org.ornet.sdclib.binding.mdpws.impl.client;

import static com.bestingit.async.Task.scheduleAsync;
import io.vertx.core.Vertx;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeFactory;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.client.IConsumerEventSinkBinding;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.RenewMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.SubscribeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.SubscribeResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.UnsubscribeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.routes.ClientRoute;
import org.w3c.dom.Node;
import static com.bestingit.async.Task.*;

public class ClientSubscriptionManager {

    private final MDPWSClient client;
    private final AtomicBoolean connected;
    private final IConsumerEventSinkBinding eventSinkBinding;
    private final String notifyAddress;
    private final Map<String, String> filterIds = new ConcurrentHashMap<>(); // FilterId => ManagerAddr
    private final Vertx vertx;
    private long subscriptionTimerId;
    private long oirTimerId;
    private boolean requestFailure = false;
    private boolean oirInitialized = false;

    private static final int SUBSCRIBE_TIME = 30000;
    private static final int RENEW_TIME = 15000;

    public ClientSubscriptionManager(IConsumerEventSinkBinding eventSinkBinding, MDPWSClient client, Vertx vertx, AtomicBoolean connected, boolean isSsl) {
        this.client = client;
        this.vertx = vertx;
        this.connected = connected;
        this.eventSinkBinding = eventSinkBinding;
        final String http = isSsl? "https://" : "http://";
        this.notifyAddress = http + client.getBaseAddress() + ":" + client.getBasePort() + "/" + ClientRoute.REALM;
    }

    public IConsumerEventSinkBinding getEventSinkBinding() {
        return eventSinkBinding;
    }

    public void init() {
        initOperationInvoked();
        subscribeAll();
        subscriptionTimerId = scheduleAsync(() -> {
            if (!requestFailure) {
                renewAll();
            } else {
                subscribeAll();
            }
        }, RENEW_TIME, true);
    }

    public void initOperationInvoked() {
        if (oirInitialized) {
            return;
        }
        filterIds.remove(WSConstants.FILTER_OIR);
        subscribe(WSConstants.FILTER_OIR, WSConstants.PORT_TYPE_SET);
        oirTimerId = scheduleAsync(() -> {
            if (!requestFailure) {
                renew(WSConstants.FILTER_OIR, WSConstants.PORT_TYPE_SET);
            } else {
                filterIds.remove(WSConstants.FILTER_OIR);
                subscribe(WSConstants.FILTER_OIR, WSConstants.PORT_TYPE_SET);
            }
        }, RENEW_TIME, true);
        oirInitialized = true;
    }

    private void renewAll() {
        renew(WSConstants.FILTER_EAR, WSConstants.PORT_TYPE_EVT);
        renew(WSConstants.FILTER_ECR, WSConstants.PORT_TYPE_CTX);
        renew(WSConstants.FILTER_EMR, WSConstants.PORT_TYPE_EVT);
        renew(WSConstants.FILTER_PAR, WSConstants.PORT_TYPE_EVT);
        renew(WSConstants.FILTER_PCR, WSConstants.PORT_TYPE_CTX);
        renew(WSConstants.FILTER_PMR, WSConstants.PORT_TYPE_EVT);
        renew(WSConstants.FILTER_WFS, WSConstants.PORT_TYPE_WAV);
    }

    private void subscribeAll() {
        var oirEntry = filterIds.entrySet().stream().filter(entry -> entry.getKey().contains("OperationInvokedReport")).findFirst();
        filterIds.clear();
        if (oirEntry.isPresent())
            filterIds.put(oirEntry.get().getKey(), oirEntry.get().getValue());
        subscribe(WSConstants.FILTER_EAR, WSConstants.PORT_TYPE_EVT);
        subscribe(WSConstants.FILTER_ECR, WSConstants.PORT_TYPE_CTX);
        subscribe(WSConstants.FILTER_EMR, WSConstants.PORT_TYPE_EVT);
        subscribe(WSConstants.FILTER_PAR, WSConstants.PORT_TYPE_EVT);
        subscribe(WSConstants.FILTER_PCR, WSConstants.PORT_TYPE_CTX);
        subscribe(WSConstants.FILTER_PMR, WSConstants.PORT_TYPE_EVT);
        subscribe(WSConstants.FILTER_WFS, WSConstants.PORT_TYPE_WAV);
    }

    private void unsubscribeAll() {
        unsubscribe(WSConstants.FILTER_EAR, WSConstants.PORT_TYPE_EVT);
        unsubscribe(WSConstants.FILTER_ECR, WSConstants.PORT_TYPE_CTX);
        unsubscribe(WSConstants.FILTER_EMR, WSConstants.PORT_TYPE_EVT);
        unsubscribe(WSConstants.FILTER_PAR, WSConstants.PORT_TYPE_EVT);
        unsubscribe(WSConstants.FILTER_PCR, WSConstants.PORT_TYPE_CTX);
        unsubscribe(WSConstants.FILTER_PMR, WSConstants.PORT_TYPE_EVT);
        unsubscribe(WSConstants.FILTER_WFS, WSConstants.PORT_TYPE_WAV);
        unsubscribe(WSConstants.FILTER_OIR, WSConstants.PORT_TYPE_SET);
    }

    public void subscribe(String filter, String servicePortType) {
        if (!connected.get()) {
            requestFailure = true;
            return;
        }
        try {
            String expires = DatatypeFactory.newInstance().newDuration(SUBSCRIBE_TIME).toString();
            SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing subscribe request, Filter action: {0}", filter);
            String trace = Arrays.toString(Thread.currentThread().getStackTrace());
            client.postService(servicePortType, new SubscribeMessage(notifyAddress, filter, expires), res -> {
                if (res.succeeded()) {
                    var result = res.result();
                    if (result.statusCode() != 200) {
                        SDCLib.getInstance().getLogger().log(Level.WARNING, "Subscribe request failed: {0}", result.statusMessage());
                        requestFailure = true;
                        return;
                    }
                    var buf = result.body();
                    AbstractSoapMessage msg = new AbstractSoapMessage(buf);
                    if (msg.getAction().equals(WSConstants.SOAP_FAULT_ACTION)) {
                        SDCLib.getInstance().getLogger().log(Level.WARNING, "Subscribe request failed: soap fault.");
                        requestFailure = true;
                        return;
                    }
                    try {
                        SubscribeResponseMessage response = new SubscribeResponseMessage(msg.getContent());
                        var managerAddr = response.getManagerAddr();
                        if (managerAddr == null) {
                            SDCLib.getInstance().getLogger().log(Level.WARNING, "Subscribe request failed, no address found.");
                            requestFailure = true;
                            return;
                        }
                        filterIds.put(filter, managerAddr);
                        requestFailure = false;
                    }
                    catch (Exception e) {
                        SDCLib.getInstance().getLogger().log(Level.WARNING, "Subscribe request failed: " + e.getMessage());
                        requestFailure = true;
                    }
                } else {
                    var exc = res.cause();
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "Subscribe request failed: {0} ({1})", new Object[]{filter, exc.toString()});
                    SDCLib.getInstance().logException(exc, trace);
                    requestFailure = true;
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(ClientSubscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void renew(String filter, String servicePortType) {
        if (!connected.get()) {
            requestFailure = true;
            return;
        }
        var managerAddr = filterIds.get(filter);
        try {
            String expires = DatatypeFactory.newInstance().newDuration(SUBSCRIBE_TIME).toString();
            if (managerAddr == null) {
                SDCLib.getInstance().getLogger().log(Level.WARNING, "Renew failed due to missing manager addr, filter: {0}", filter);
                requestFailure = true;
                return;
            }            
            SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing renew request, filter: {0}", filter);
            String trace = Arrays.toString(Thread.currentThread().getStackTrace());
            client.post(managerAddr, new RenewMessage(managerAddr, expires), res -> {
                if (res.succeeded()) {
                    var result = res.result();
                    if (result.statusCode() != 200) {
                        SDCLib.getInstance().getLogger().log(Level.WARNING, "Renew request failed: {0}", result.statusMessage());
                        requestFailure = true;
                        return;
                    }
                    var buf = result.body();
                    AbstractSoapMessage msg = new AbstractSoapMessage(buf);
                    if (msg.getAction().equals(WSConstants.SOAP_FAULT_ACTION)) {
                        SDCLib.getInstance().getLogger().log(Level.WARNING, "Renew request failed: soap fault.");
                        requestFailure = true;
                    }
                } else {
                    var exc = res.cause();
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "Renew request failed: {0} ({1})", new Object[]{filter, exc.toString()});
                    SDCLib.getInstance().logException(exc, trace);
                    requestFailure = true;
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(ClientSubscriptionManager.class.getName()).log(Level.SEVERE,
                    "Client base address: " + client.getCurrentDeviceXAddr(), ex);
        }
    }

    public void unsubscribe(String filter, String servicePortType) {
        if (!connected.get()) {
            requestFailure = true;
            return;
        }
        var managerAddr = filterIds.get(filter);
        if (managerAddr == null) {
            SDCLib.getInstance().getLogger().log(Level.WARNING, "Unsubscribe failed due to missing manager addr, filter: {0}", filter);
            requestFailure = true;
            return;
        }
        SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing unsubscribe request, filter: {0}", filter);
        String trace = Arrays.toString(Thread.currentThread().getStackTrace());

        client.postService(servicePortType, new UnsubscribeMessage(managerAddr), res -> {
            if (res.succeeded()) {
                var result = res.result();
                if (result.statusCode() != 200) {
                    SDCLib.getInstance().getLogger().log(Level.FINER, "Unsubscribe request failed: {0}", result.statusMessage());
                } else {
                    filterIds.remove(filter);
                }
            } else {
                var exc = res.cause();
                SDCLib.getInstance().getLogger().log(Level.FINER, "Unsubscribe request failed: {0} ({1})", new Object[]{filter, exc.toString()});
                SDCLib.getInstance().logException(exc, trace);
                // No request failure needed because of unsubscribe
            }
        });
    }

    public void close() {
        if (oirTimerId != -1) {
            cancel(oirTimerId);
            oirTimerId = -1;
            oirInitialized = false;
        }
        if (subscriptionTimerId != -1) {
            cancel(subscriptionTimerId);
            subscriptionTimerId = -1;
            unsubscribeAll();
        }
    }

    public void deInit() {
        // We leave OIR eventing running until closed, but unsubscribe everything else
        if (subscriptionTimerId != -1) {
            cancel(subscriptionTimerId);
            subscriptionTimerId = -1;
            unsubscribe(WSConstants.FILTER_EAR, WSConstants.PORT_TYPE_EVT);
            unsubscribe(WSConstants.FILTER_ECR, WSConstants.PORT_TYPE_CTX);
            unsubscribe(WSConstants.FILTER_EMR, WSConstants.PORT_TYPE_EVT);
            unsubscribe(WSConstants.FILTER_PAR, WSConstants.PORT_TYPE_EVT);
            unsubscribe(WSConstants.FILTER_PCR, WSConstants.PORT_TYPE_CTX);
            unsubscribe(WSConstants.FILTER_PMR, WSConstants.PORT_TYPE_EVT);
            unsubscribe(WSConstants.FILTER_WFS, WSConstants.PORT_TYPE_WAV);
        }
    }

}
