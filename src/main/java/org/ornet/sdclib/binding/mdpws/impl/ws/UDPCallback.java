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

import java.util.UUID;

import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;

public class UDPCallback {

    public interface UDPCallbackSupplier {

        void apply(String senderHost, int senderPort, AbstractSoapMessage msg);

    }

    private final UDPCallbackSupplier supplier;
    private final String id;

    public UDPCallback(UDPCallbackSupplier supplier) {
        this.supplier = supplier;
        id = UUID.randomUUID().toString();
    }

    public UDPCallbackSupplier getSupplier() {
        return supplier;
    }

    public String getId() {
        return id;
    }

}
