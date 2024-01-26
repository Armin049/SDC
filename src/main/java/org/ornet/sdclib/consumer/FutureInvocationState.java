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
package org.ornet.sdclib.consumer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ornet.cdm.InvocationState;

public class FutureInvocationState {

    private final AtomicLong transactionId = new AtomicLong();
    private final AtomicBoolean match = new AtomicBoolean(false);
    @SuppressWarnings("SetReplaceableByEnumSet")
    private final Set<InvocationState> expectedSet = Collections.synchronizedSet(new HashSet<InvocationState>());
    @SuppressWarnings("SetReplaceableByEnumSet")
    private final Set<InvocationState> actualSet = Collections.synchronizedSet(new HashSet<InvocationState>());
    private SDCConsumer consumer;
    private IFutureAsyncState asyncState;

    public boolean waitReceived(InvocationState expected, int timeout) {
        return waitReceived(new InvocationState[] { expected }, timeout);
    }

    public boolean waitReceived(InvocationState[] expected, int timeout) {
        if (consumer == null)  // This can happen in case the commit function in the consumer aborts before actually requesting.
            return false;
        expectedSet.addAll(Arrays.asList(expected));
        checkMatch();
        long startTime = System.currentTimeMillis();
        long elapsed = 0;
        boolean matches = false;
        while (elapsed < timeout && !(matches = match.get())) {
            try {
                long remaining = timeout - elapsed;
                synchronized (this) {
                    wait(remaining);
                }
                long endTime = System.currentTimeMillis();
                elapsed = endTime - startTime;
            } catch (InterruptedException ex) {
                Logger.getLogger(FutureInvocationState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        consumer.unregisterFutureInvocationstate(this);
        actualSet.clear();
        expectedSet.clear();
        return matches;
    }

    public FutureInvocationState receiveAsync(IFutureAsyncState asyncState) {
        this.asyncState = asyncState;
        return this;
    }

    protected void setTransactionId(long transactionId) {
        this.transactionId.set(transactionId);
    }

    public long getTransactionId() {
        return transactionId.get();
    }

    protected void setActual(InvocationState is) {
        if (asyncState != null) {
            asyncState.received(is);
        }
        actualSet.add(is);
        checkMatch();
    }

    private void checkMatch() {
        synchronized (this) {
            if (!expectedSet.isEmpty() && actualSet.stream().anyMatch(a -> expectedSet.contains(a))) {
                match.set(true);
                notifyAll();
            }
        }
    }

    protected void setConsumer(SDCConsumer consumer) {
        this.consumer = consumer;
    }

}
