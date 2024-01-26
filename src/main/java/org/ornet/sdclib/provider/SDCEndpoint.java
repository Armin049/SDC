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
package org.ornet.sdclib.provider;

import java.util.List;
import org.ornet.cdm.MdDescription;
import org.ornet.cdm.Mdib;
import org.ornet.cdm.MdState;

/**
 *
 * @author besting
 */
public interface SDCEndpoint {

    /**
     * Get deep clone of MD description.
     *
     * @return The description.
     */
    MdDescription getMDDescription();

    /**
     * Create an MDIB container object. Modifications of this structure will not
     * be reflected into the internal MDIB representation.
     *
     * @return The MDIB.
     */
    Mdib getMDIB();

    /**
     * Get deep clone of all MD states.
     *
     * @return The states.
     */
    MdState getMDState();

    /**
     * Get deep clone of MD states.
     *
     * @param handles List of handles to match.
     * @return The states.
     */
    MdState getMDState(List<String> handles);

    /**
     * Get ID of endpoint
     *
     * @return the ID.
     */
    String getEndpointReference();

}
