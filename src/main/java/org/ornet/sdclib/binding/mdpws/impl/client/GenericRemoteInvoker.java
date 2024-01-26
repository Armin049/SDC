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

import com.bestingit.async.Completer;
import com.bestingit.async.EventCompleter;
import java.util.logging.Level;
import org.ornet.cdm.SafetyInfoType;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeMessage;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.InvokeResponseMessage;
import static com.bestingit.async.Task.*;
import java.util.Arrays;

public class GenericRemoteInvoker {

    private static MDPWSTransportLayerConfiguration tlc;

    private static void initTlc() {
        if (tlc == null) {
            tlc = SDCLib.getInstance().getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class);
        }
    }

    public static <U, V> Completer<V> invokeSDCBlocking(MDPWSClient client, String servicePortType, String action, U request, Class<V> clazz) {
        return invokeSDCBlocking(client, servicePortType, action, request, null, clazz);
    }

    public static <U, V> Completer<V> invokeSDCBlocking(MDPWSClient client, String servicePortType, String action, U request, SafetyInfoType safetyInfo, Class<V> clazz) {
        initTlc();
        EventCompleter<V> ec = event(clazz);
        InvokeMessage msg = new InvokeMessage(action);
        msg.setMessageModelContent(request);
        msg.setSafetyInfoHeader(safetyInfo);
        String trace = Arrays.toString(Thread.currentThread().getStackTrace());
        client.postService(servicePortType, msg, res -> {
            if (res.succeeded()) {
                var result = res.result();
                if (result.statusCode() != 200) {
                    SDCLib.getInstance().getLogger().log(Level.WARNING, "HTTP client request failed: {0} (code {1})", new Object[]{action, result.statusCode()});
                    ec.setFinishedFailed(new IllegalStateException("HTTP client request failed"));
                }
                var buf = result.body();
                try {
                    InvokeResponseMessage invRes = new InvokeResponseMessage(buf);
                    ec.setFinishedSuccess(invRes.getMessageModelContent(clazz));
                }
                catch(Exception e) {
                    ec.setFinishedFailed(e);
                }
            } else {
                var exc = res.cause();
                SDCLib.getInstance().getLogger().log(Level.WARNING, "HTTP client request failed: {0} ({1})", new Object[]{action, exc.toString()});
                SDCLib.getInstance().logException(exc, trace);
                ec.setFinishedFailed((Exception) exc);
            }
        });
        return ec;
    }

}
