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

public abstract class SDCLifecycleHandler {

    private boolean lostTriggered = false;
    private boolean closedTriggered = false;

    protected void triggerLost(SDCConsumer consumer) {
        onConnectionLost(consumer);
        lostTriggered = true;
    }

    protected void triggerReestablished(SDCConsumer consumer) {
        onConnectionReestablished(consumer);
        lostTriggered = false;
    }

    protected boolean isLostTriggered() {
        return lostTriggered;
    }

    protected void triggerClosed(SDCConsumer consumer) {
        onClosed(consumer);
        closedTriggered = true;
    }

    protected void triggerOpened(SDCConsumer consumer) {
        onOpened(consumer);
        closedTriggered = false;
    }

    protected boolean isClosedTriggered() {
        return closedTriggered;
    }

    public abstract void onConnectionLost(SDCConsumer consumer);

    public abstract void onConnectionReestablished(SDCConsumer consumer);

    public abstract void onClosed(SDCConsumer consumer);

    public abstract void onOpened(SDCConsumer consumer);

}
