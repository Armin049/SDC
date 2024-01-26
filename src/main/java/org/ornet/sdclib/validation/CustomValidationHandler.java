/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting, stegemann
 * @Copyright (C) SurgiTAIX AG
 */
package org.ornet.sdclib.validation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

/**
 * Validation handler which allows the registragtion of multiple handlers.
 * Stores a list of handlers which are executed, if a event occures.
 *
 * @author stegemann
 *
 */
public class CustomValidationHandler implements ValidationEventHandler {

    private final List<ValidationEventHandler> handlers;

    public CustomValidationHandler() {
        handlers = new ArrayList<>();
    }

    public void addHandler(ValidationEventHandler handler) {
        if (handler != null) {
            handlers.add(handler);
        }
    }

    public void removeHandler(ValidationEventHandler handler) {
        if (handler != null) {
            handlers.remove(handler);
        }
    }

    @Override
    public boolean handleEvent(ValidationEvent event) {
        for (ValidationEventHandler handler : handlers) {
            handler.handleEvent(event);
        }

        return false;
    }

}
