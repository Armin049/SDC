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

import java.util.logging.Level;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import org.ornet.sdclib.SDCLib;

public class LogValidationHandler implements ValidationEventHandler {

    @Override
    public boolean handleEvent(ValidationEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("XML schema validation alert");
        sb.append("\nSEVERITY:  ").append(event.getSeverity());
        sb.append("\nMESSAGE:  ").append(event.getMessage());
        sb.append("\nLINKED EXCEPTION:  ").append(event.getLinkedException());
        sb.append("\nLOCATOR");
        try {
            sb.append("\n    LINE NUMBER:  ").append(event.getLocator().getLineNumber());
            sb.append("\n    COLUMN NUMBER:  ").append(event.getLocator().getColumnNumber());
            sb.append("\n    OFFSET:  ").append(event.getLocator().getOffset());
            sb.append("\n    OBJECT:  ").append(event.getLocator().getObject());
            sb.append("\n    NODE:  ").append(event.getLocator().getNode());
            sb.append("\n    URL:  ").append(event.getLocator().getURL());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        SDCLib.getInstance().getLogger().log(Level.SEVERE, sb.toString());
        return false;
    }
}
