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
package org.ornet.sdclib.common;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncEvent {

    private final Object object = new Object();
    private final AtomicBoolean signaled = new AtomicBoolean(false);

    public SyncEvent() {
    }

    public boolean wait(int timeout) {
        if (signaled.get()) {
            // Signaled before wait, reset & return immediately
            signaled.set(false);
            return true;
        }
        synchronized (object) {
            try {
                object.wait(timeout);
            } catch (InterruptedException ex) {
                Logger.getLogger(SyncEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean result = signaled.get();
        // Reset
        signaled.set(false);
        return result;
    }

    public void set() {
        set(true);
    }

    public void set(boolean state) {
        synchronized (object) {
            signaled.set(state);
            object.notifyAll();
        }
    }

}
