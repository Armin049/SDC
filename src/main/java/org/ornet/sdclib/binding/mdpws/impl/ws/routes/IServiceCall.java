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

import io.vertx.ext.web.RoutingContext;
import org.ornet.sdclib.binding.mdpws.impl.ws.message.AbstractSoapMessage;

public interface IServiceCall<T extends AbstractSoapMessage> {

    void apply(RoutingContext rc, T msg) throws Exception;

}
