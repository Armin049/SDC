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

public class SDCConsumerHandler {

    private final String descriptorHandle;

    public SDCConsumerHandler(String descriptorHandle) {
        this.descriptorHandle = descriptorHandle;
    }

    public String getDescriptorHandle() {
        return descriptorHandle;
    }

}
