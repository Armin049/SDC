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

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import io.vertx.ext.web.client.WebClient;
import org.ornet.cdm.AbstractReport;
import org.ornet.cdm.WaveformStream;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetStatusMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.GetStatusResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.RenewMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.RenewResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.SubscribeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.SubscribeResponseMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.UnsubscribeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.UnsubscribeResponseMessage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DeviceSubscriptionManager {

    class SubscriptionInfo {

        private final String managerAddr;
        private final String notifyTo;
        private final Duration duration;
        private long timerId, timeStamp;

        public SubscriptionInfo(String notifyTo, String managerAddr, Duration duration, long timerId, long timeStamp) {
            this.notifyTo = notifyTo;
            this.managerAddr = managerAddr;
            this.duration = duration;
            this.timerId = timerId;
            this.timeStamp = timeStamp;
        }

        public String getNotifyTo() {
            return notifyTo;
        }

        public Duration getDuration() {
            return duration;
        }

        public long getTimerId() {
            return timerId;
        }

        public void setTimerId(long timerId) {
            this.timerId = timerId;
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getManagerAddr() {
            return managerAddr;
        }

        public Duration getRemainingDuration() {
            try {
                long endTime = timeStamp + duration.getTimeInMillis(new Date());
                long remaining = endTime - new Date().getTime();
                return DatatypeFactory.newInstance().newDuration(remaining);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(DeviceSubscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

    }

    class SubscriptionInfoContainer {

        private final SubscriptionInfo info;
        private final String filters;

        public SubscriptionInfoContainer(SubscriptionInfo info, String filterString) {
            this.info = info;
            this.filters = filterString;
        }

        public SubscriptionInfo getInfo() {
            return info;
        }

        public String getFilterString() {
            return filters;
        }

    }

    // Map of filter actions -> map of device identifiers and the subscription info (contains client identifier)
    private final Map<String, Map<String, SubscriptionInfo>> subscriptions = new ConcurrentHashMap<>();

    private final String xaddrs;
    private final Vertx vertx;
    private WebClient client;
    private final MDPWSTransportLayerConfiguration tlc;

    public DeviceSubscriptionManager(String xaddrs, Vertx vertx, MDPWSTransportLayerConfiguration tlc) {
        this.xaddrs = xaddrs;
        this.vertx = vertx;
        this.tlc = tlc;
        init();
    }

    public final void init() {
        this.client = WebClient.wrap(vertx.createHttpClient(tlc.getConfigurationDetail().getClientOptions()));
    }

    public final void close() {
        client.close();
    }

    public void sendReport(AbstractReport report, String filterAction) {
        sendObject(report, filterAction, "event");
    }

    public void sendStream(WaveformStream stream, String filterAction) {
        sendObject(stream, filterAction, "stream");
    }

    public void sendObject(Object obj, String filterAction, String messageType) {
        Map<String, SubscriptionInfo> map = retrieveSubscriptionMap(filterAction);
        if (map == null) {
            // No subscriptions available, skipping
            return;
        }
        String trace = Arrays.toString(Thread.currentThread().getStackTrace());
        map.values().forEach((si) -> {
            InvokeMessage msg = new InvokeMessage(filterAction);
            msg.setTo(si.getNotifyTo());
            msg.setMessageModelContent(obj);
            SDCLib.getInstance().getLogger().log(Level.FINER, "Outgoing {0}, Action: {1} to {2}",
                    new Object[]{messageType, filterAction, si.getNotifyTo()});
            final Buffer buffer = msg.createBuffer();
            client.postAbs(si.getNotifyTo())
                .timeout(tlc.getConfigurationDetail().getCommTimeout())
                .putHeader("Content-Type", "application/soap+xml")
                .sendBuffer(buffer, h -> {
                    if (h.succeeded()) {
                        var res = h.result();
                        if (res.statusCode() > 204) {
                            SDCLib.getInstance().getLogger().log(Level.WARNING, "Invoke status {0}: {1} (code {2})",
                                    new Object[]{messageType, res.statusMessage(), res.statusCode()});
                        }
                    } else {
                        var exc = h.cause();
                        SDCLib.getInstance().logException(exc, trace);
                    }
                });
        });
    }

    private Map<String, SubscriptionInfo> retrieveSubscriptionMap(String filter) {
        // Check single subscriptions
        if (subscriptions.containsKey(filter)) {
            return subscriptions.get(filter);
        }
        // No info found
        return null;
    }

    private Map<String, SubscriptionInfo> createSubscriptionMap(String filter) {
        Map<String, SubscriptionInfo> map = new ConcurrentHashMap<>();
        subscriptions.put(filter, map);
        return map;
    }

    private SubscriptionInfoContainer getSubscriptionInfo(String managerAddr) {
        for (Map.Entry<String, Map<String, SubscriptionInfo>> next : subscriptions.entrySet()) {
            if (next.getValue().containsKey(managerAddr)) {
                return new SubscriptionInfoContainer(next.getValue().get(managerAddr), next.getKey());
            }
        }
        return null;
    }

    public SubscribeResponseMessage handleSubscribe(SubscribeMessage subscribe, String serviceAddr) {
        SubscribeResponseMessage response = new SubscribeResponseMessage(subscribe);
        String managerAddr = serviceAddr + "/" + UUID.randomUUID().toString();
        final String filterString = subscribe.getFilterString();
        response.setManagerAddr(managerAddr);
        try {
            Duration dur = subscribe.getExpires() != null
                    ? DatatypeFactory.newInstance().newDuration(subscribe.getExpires())
                    : DatatypeFactory.newInstance().newDuration(Long.MAX_VALUE);

            String filterList[] = filterString.split(" ");
            for(String filter : filterList) {
                if(!filter.isBlank() && !filter.isEmpty()) {
                    Map<String, SubscriptionInfo> identMap = retrieveSubscriptionMap(filter);
                    if (identMap == null) {
                        identMap = createSubscriptionMap(filter);
                    }
                    long timerID = configureExpireTimer(dur, managerAddr, filter);
                    identMap.put(managerAddr, new SubscriptionInfo(subscribe.getNotifyToAddr(), managerAddr, dur, timerID, new Date().getTime()));
                }
            }
        } catch (DatatypeConfigurationException | InvalidParameterException ex) {
            response.createFault(ex.toString());
            Logger.getLogger(DeviceSubscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    private long configureExpireTimer(Duration dur, String managerAddr, final String filter) {
        long timerID = vertx.setTimer(dur.getTimeInMillis(new Date()), id -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Subscription expired: {0}", managerAddr);
            Map<String, SubscriptionInfo> identMap = retrieveSubscriptionMap(filter);
            if (identMap == null) {
                throw new InvalidParameterException();
            }
            identMap.remove(managerAddr);
        });
        return timerID;
    }

    public RenewResponseMessage handleRenew(RenewMessage renew) {
        RenewResponseMessage response = new RenewResponseMessage(renew);
        try {
            Duration dur = DatatypeFactory.newInstance().newDuration(renew.getExpires());
            SubscriptionInfoContainer sic = getSubscriptionInfo(renew.getTo());
            if (sic == null) {
                response.createFault("Subscription not found for identifier: " + renew.getTo());
                return response;
            }
            if (!vertx.cancelTimer(sic.getInfo().getTimerId())) {
                SDCLib.getInstance().getLogger().log(Level.WARNING, "Subscription expiration timer not canceled: {0}", sic.getInfo().getTimerId());
            }
            try {
                long timerID = configureExpireTimer(dur, renew.getTo(), sic.getFilterString());
                sic.getInfo().setTimerId(timerID);
                sic.getInfo().setTimeStamp(new Date().getTime());
            } catch (InvalidParameterException e) {
                response.createFault("Subscription not found for identifier: " + renew.getTo());
                return response;
            }
        } catch (Exception ex) {
            response.createFault(ex.toString());
            Logger.getLogger(DeviceSubscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public GetStatusResponseMessage handleGetStatus(GetStatusMessage status) {
        GetStatusResponseMessage response = new GetStatusResponseMessage(status);
        try {
            SubscriptionInfoContainer sic = getSubscriptionInfo(status.getTo());
            if (sic == null) {
                response.createFault("Subscription not found for identifier: " + status.getTo());
                return response;
            }
            response.setExpires(sic.getInfo().getRemainingDuration().toString());
        } catch (Exception ex) {
            response.createFault(ex.toString());
            Logger.getLogger(DeviceSubscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public UnsubscribeResponseMessage handleUnsubscribe(UnsubscribeMessage unsub) {
        UnsubscribeResponseMessage response = new UnsubscribeResponseMessage(unsub);
        try {
            SubscriptionInfoContainer sic = getSubscriptionInfo(unsub.getTo());
            if (sic == null) {
                response.createFault("Subscription not found for identifier: " + unsub.getTo());
                return response;
            }
            vertx.cancelTimer(sic.getInfo().getTimerId());
            Map<String, SubscriptionInfo> identMap = retrieveSubscriptionMap(sic.getFilterString());
            if (identMap == null) {
                response.createFault("Subscription not found for identifier: " + unsub.getTo());
                return response;
            }
            identMap.remove(unsub.getTo());
        } catch (Exception ex) {
            response.createFault(ex.toString());
            Logger.getLogger(DeviceSubscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

}
