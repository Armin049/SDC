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

public class ClientMsgManager {

    private ClientStreamingManager streamingManager;
    private ClientSubscriptionManager subscriptionManager;
    private MDPWSConsumerGetOperationBinding getOperationBinding;
    private MDPWSConsumerSetOperationBinding setOperationBinding;

    public ClientMsgManager() {
    }

    public void setStreamingManager(ClientStreamingManager streamingManager) {
        this.streamingManager = streamingManager;
    }

    public ClientStreamingManager getStreamingManager() {
        return streamingManager;
    }

    public void setSubscriptionManager(ClientSubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    public ClientSubscriptionManager getSubscriptionManager() {
        return subscriptionManager;
    }

    public MDPWSConsumerGetOperationBinding getGetOperationBinding() {
        return getOperationBinding;
    }

    public MDPWSConsumerSetOperationBinding getSetOperationBinding() {
        return setOperationBinding;
    }

    public void setGetOperationBinding(MDPWSConsumerGetOperationBinding getOperationBinding) {
        this.getOperationBinding = getOperationBinding;
    }

    public void setSetOperationBinding(MDPWSConsumerSetOperationBinding setOperationBinding) {
        this.setOperationBinding = setOperationBinding;
    }

}
