/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting
 * @Copyright (C) Andreas Besting
 */
package org.ornet.sdclib.restapi;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.ornet.cdm.AbstractState;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.MdDescription;
import org.ornet.cdm.MdState;
import org.ornet.sdclib.SDCFluent;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.common.MaxHashMap;
import org.ornet.sdclib.consumer.FutureInvocationState;
import org.ornet.sdclib.restapi.model.InvocationResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class RestApiRoute {

    public static final String REALM = "sdcapi/v1/";
    public static final String PATH_PROVIDER_GET_DESCRIPTION = "provider/:epr/get/description";
    public static final String PATH_PROVIDER_GET_STATES = "provider/:epr/get/states";
    public static final String PATH_PROVIDER_GET_INVOCATIONSTATES = "provider/get/invocationstates/:epr/:id";
    public static final String PATH_PROVIDER_SET_STATE = "provider/:epr/set/state";
    public static final String PATH_PROVIDER_GET_VALUE = "provider/:epr/get/value/:handle";
    public static final String PATH_PROVIDER_SET_VALUE = "provider/:epr/set/value/:handle";
    public static final String PATH_DISCOVERY = "discovery";
    
    private static final DocumentBuilderFactory DOM_FACTORY = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder domBuilder = null;    
    
    public static final Map<String, List<InvocationState>> INVOCATION_STATES = Collections.synchronizedMap(new MaxHashMap<>(256));
    private static int DefaultTimeout = 3000;

    public void configure(Router router) {
        if (domBuilder == null) {
            try {
                DOM_FACTORY.setNamespaceAware(true);
                domBuilder = DOM_FACTORY.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        router.route("/" + REALM + "*").handler(BodyHandler.create());
        
        router.get("/" + REALM + PATH_DISCOVERY).handler(rc -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming REST API call: " + PATH_DISCOVERY);
            successResponseJsonObject(rc, SDCFluent.GetMembers());       
        });
        
        router.get("/" + REALM + PATH_PROVIDER_GET_STATES).handler(rc -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming REST API call: " + PATH_PROVIDER_GET_STATES);
            var epr = rc.request().params().get("epr");
            var handle = rc.request().params().get("handle");
            var handles = new ArrayList<String>();
            if (handle != null)
                handles.add(handle);
            MdState stateContainer = null;
            if (epr == null || (stateContainer = SDCFluent.GetStates(epr, handles)) == null) {
                errorResponse(rc, 464, "Invalid or missing EPR, provider not found.");
                return;
            }
            successResponseXmlObject(rc, stateContainer);             
        });     
        
        router.get("/" + REALM + PATH_PROVIDER_GET_DESCRIPTION).handler(rc -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming REST API call: " + PATH_PROVIDER_GET_DESCRIPTION);
            var epr = rc.request().params().get("epr");
            MdDescription description = null;
            try {
                description = SDCFluent.GetDescription(epr);
            }
            catch (IllegalStateException ex) {
                errorResponse(rc, 464, "Invalid EPR, provider not found: " + epr);
                return;               
            }            
            successResponseXmlObject(rc, description);   
        });   
        
        router.get("/" + REALM + PATH_PROVIDER_GET_INVOCATIONSTATES).handler(rc -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming REST API call: " + PATH_PROVIDER_GET_INVOCATIONSTATES);
            long transactionId = Long.MIN_VALUE;
            try 
            {
                transactionId = Long.parseLong(rc.request().params().get("id"));                
            }
            catch (NumberFormatException e) {
                 SDCLib.getInstance().getLogger().log(Level.SEVERE, "Incoming REST API call error: {0}", e.getMessage());               
            }
            if (transactionId == Long.MIN_VALUE) {
                errorResponse(rc, 400, "Bad parameter provided");
                return;               
            }
            var epr = rc.request().params().get("epr");
            if (!INVOCATION_STATES.containsKey(epr + transactionId)) {
                errorResponse(rc, 464, "Invalid transaction id");
                return;               
            }
            successResponseJsonObject(rc, INVOCATION_STATES.get(epr + transactionId));
        });             
        
        router.post("/" + REALM + PATH_PROVIDER_SET_STATE).handler(rc -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming REST API call: " + PATH_PROVIDER_SET_STATE);
            var bodyString = rc.getBodyAsString();
            Document bodyNode;
            AbstractState state;
            try {
                bodyNode = domBuilder.parse(new ByteArrayInputStream(bodyString.getBytes("utf-8") ));
                state = JAXBUtil.getInstance().nodeToObject(bodyNode, AbstractState.class);
            } catch (IOException | JAXBException | SAXException ex) {
                Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, ex);
                errorResponse(rc, 400, "Bad data provided: " + ex.getMessage());
                return;
            } 
            var epr = rc.request().params().get("epr");
            InvocationState directState;
            var fis = new FutureInvocationState();
            try {
                directState = SDCFluent.SetState(epr, state, fis);       
            }
            catch (IllegalStateException ex) {
                errorResponse(rc, 464, "Invalid EPR, provider not found: " + epr);
                return;               
            }
            final long transactionId = fis.getTransactionId();
            var list = new CopyOnWriteArrayList<InvocationState>();
            list.add(directState);
            fis.receiveAsync((InvocationState is) -> {
                // Add other incoming states to map for later polling
                var invocationList = INVOCATION_STATES.get(epr + transactionId);
                if (invocationList != null && !invocationList.contains(is)) {
                    invocationList.add(is);
                }
            });
            INVOCATION_STATES.put(epr + transactionId, list);
            var result = new InvocationResult();
            result.setTransactionId(transactionId);
            result.setInvocationState(InvocationResult.InvocationStateEnum.fromValue(directState.value()));
            successResponseJsonObject(rc, result);                  
        });

        router.get("/" + REALM + PATH_PROVIDER_GET_VALUE).handler(rc -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming REST API call: " + PATH_PROVIDER_GET_VALUE);
            var epr = rc.request().params().get("epr");
            var handle = rc.request().params().get("handle");
            String value = null;
            try {
                value = SDCFluent.GetSimpleValue(epr, handle);
            }
            catch (IllegalStateException ex) {
                errorResponse(rc, 464, "Invalid EPR, provider not found: " + epr);
                return;
            }
            catch (Exception e) {
                errorResponse(rc, 465, "Invalid handle, state not found: " + handle);
                return;
            }
            successResponseTextPlain(rc, value);
        });

        router.post("/" + REALM + PATH_PROVIDER_SET_VALUE).handler(rc -> {
            SDCLib.getInstance().getLogger().log(Level.FINER, "Incoming REST API call: " + PATH_PROVIDER_SET_VALUE);
            var epr = rc.request().params().get("epr");
            var handle = rc.request().params().get("handle");
            var value = rc.request().params().get("value");
            int timeout = DefaultTimeout;
            if (rc.request().params().contains("timeout"))
            {
                try {
                    timeout = Integer.parseInt(rc.request().params().get("timeout"));
                }
                catch (NumberFormatException e) {
                    errorResponse(rc, 400, "Bad data provided: " + e.getMessage());
                }
            }
            double [] number = new double[1];
            if (isNumeric(value, number)) {
                try {
                    if (!SDCFluent.SetSimpleNumberValue(epr, handle, number[0], timeout)) {
                        errorResponse(rc, 465, "Error modifying state: " + handle);
                    }
                    else {
                        successResponse(rc, 200);
                    }
                }
                catch (IllegalStateException ex) {
                    errorResponse(rc, 464, "Invalid EPR, provider not found: " + epr);
                    return;
                }
                catch(Exception e) {
                    Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, e);
                    errorResponse(rc, 465, "Error modifying state: " + handle);
                    return;
                }
            } else {
                try {
                    if (!SDCFluent.SetSimpleStringValue(epr, handle, value, timeout)) {
                        errorResponse(rc, 465, "Error modifying state: " + handle);
                    }
                    else {
                        successResponse(rc, 200);
                    }
                }
                catch (IllegalStateException ex) {
                    errorResponse(rc, 464, "Invalid EPR, provider not found: " + epr);
                    return;
                }
                catch(Exception e) {
                    Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, e);
                    errorResponse(rc, 465, "Error modifying state: " + handle);
                    return;
                }
            }
        });
    }

    public static boolean isNumeric(String str, double [] out) {
        try {
            out[0] = Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void successResponseXmlObject(RoutingContext rc, Object object) {
        Document doc = domBuilder.newDocument();
        try {
            JAXBUtil.getInstance().noneRootObjectToDOMDoc(object, doc, false);
            var xmlString = JAXBUtil.getInstance().nodeToString(doc);
            var responseBuffer = Buffer.buffer(xmlString);
            rc.response().putHeader("Content-Type", "application/xml");
            rc.response().end(responseBuffer);
        } catch (JAXBException ex) {
            Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, ex);
            errorResponse(rc, 500, ex.getMessage());              
        }
    }
    
    private void successResponseJsonObject(RoutingContext rc, Object obj) {
        try {
            var encoded = Json.encode(obj);
            var responseBuffer = Buffer.buffer(encoded);
            rc.response().putHeader("Content-Type", "application/json");
            rc.response().end(responseBuffer);
        }
        catch(Exception e) {
            Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, e);
            errorResponse(rc, 500, e.getMessage());
        }
    }

    private void successResponseTextPlain(RoutingContext rc, String str) {
        try {
            var responseBuffer = Buffer.buffer(str);
            rc.response().putHeader("Content-Type", "text/plain");
            rc.response().end(responseBuffer);
        }
        catch(Exception e) {
            Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, e);
            errorResponse(rc, 500, e.getMessage());
        }
    }

    private void errorResponse(RoutingContext rc, int code, String text) {
        try {
            rc.response().putHeader("Content-Type", "text/plain");
            rc.response().setStatusCode(code);
            rc.response().end(Buffer.buffer(text));
        }
        catch(Exception e) {
            Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, e);
            errorResponse(rc, 500, e.getMessage());
        }
    }

    private void successResponse(RoutingContext rc, int code) {
        try {
            rc.response().setStatusCode(code);
            rc.response().end();
        }
        catch(Exception e) {
            Logger.getLogger(RestApiRoute.class.getName()).log(Level.SEVERE, null, e);
            errorResponse(rc, 500, e.getMessage());
        }
    }

}