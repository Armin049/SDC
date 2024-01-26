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
package org.ornet.sdclib.binding.mdpws.impl.ws.routes;

import java.util.HashMap;
import java.util.Map;

public class ServiceCallBuilder {

    private final Map<ActionKey, IServiceCall> calls = new HashMap<>();

    public class ActionKey {

        private final String action;
        private final Class actionClass;

        public ActionKey(String action, Class actionClass) {
            this.action = action;
            this.actionClass = actionClass;
        }

        public String getAction() {
            return action;
        }

        public Class getActionClass() {
            return actionClass;
        }

    }

    public ServiceCallBuilder() {
    }

    public ServiceCallBuilder addCall(String action, Class target, IServiceCall call) {
        calls.put(new ActionKey(action, target), call);
        return this;
    }

    public Map<ActionKey, IServiceCall> build() {
        return calls;
    }

}
