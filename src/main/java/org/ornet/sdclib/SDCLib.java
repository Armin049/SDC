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
package org.ornet.sdclib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.consumer.SDCServiceManager;
import org.ornet.sdclib.validation.CustomValidationHandler;
import org.ornet.sdclib.validation.LogValidationHandler;
import org.xml.sax.SAXException;
import org.ornet.sdclib.binding.JAXBUtil;
import org.ornet.sdclib.binding.mdpws.impl.ws.WSConstants;

public class SDCLib {

    public static final String MDPWS_TRANSPORT_KEY = "MDPWS";

    private static SDCLib instance;
    private static final String VERSION = "10.3.0c";
    private static final Object INSTANCE_LOCK = new Object();
    private JAXBUtil jaxbUtil;
    private final AtomicBoolean schemaValidation = new AtomicBoolean(true);
    private String lastSchemaValidationLog = new String();
    private final Map<String, ISDCTransportLayerConfiguration> transportLayerConfigMap = new ConcurrentHashMap<>();

    private final CustomValidationHandler customValidationHandler;
    private String transportLayerKey = MDPWS_TRANSPORT_KEY;
    private ConsoleHandler handler;
    private final AtomicBoolean initialized = new AtomicBoolean();
    private final AtomicBoolean logStacktrace = new AtomicBoolean(false);
    private Level logLevel = Level.INFO;
    
    private static final int REST_API_PORT = 8064;
    
    public static void main(String[] args) throws Exception {
        SDCLib.getInstance().setLogLevel(Level.FINE);
        SDCFluent.JoinSDCNetwork();
        SDCFluent.StartSDCRestApi(REST_API_PORT);
        System.out.println("Input anything to exit.");
        System.in.read();       
        SDCFluent.LeaveSDCNetwork();
        System.exit(0);
    }    

    private SDCLib() {
        customValidationHandler = new CustomValidationHandler();
    }

    public void logException(Throwable throwable, String enclosingStackTrace) {
        if (logStacktrace.get()) {
            getLogger().log(Level.FINEST, "Exception caught: " + enclosingStackTrace, throwable);
        }
    }

    public void setLogStackTrace(boolean logStacktrace) {
        this.logStacktrace.set(logStacktrace);
    }

    public Logger getLogger() {
        final Logger logger = Logger.getLogger("SDCLib");
        if (logger.getHandlers().length == 0) {
            configureLogger(logLevel);
        }
        return logger;
    }

    private void configureLogger(Level level) {
        Logger l = Logger.getLogger("SDCLib");
        if (handler == null || l.getHandlers().length == 0) {
            handler = new ConsoleHandler();
            l.addHandler(handler);
        }
        l.setLevel(level);
        handler.setLevel(level);
        l.setUseParentHandlers(false);
    }

    public void setLogLevel(Level level) {
        logLevel = level;
        configureLogger(logLevel);
    }

    private void log(Level level, String str) {
        getLogger().log(level, str);
    }

    public static SDCLib getInstance() {
        synchronized (INSTANCE_LOCK) {
            if (instance == null) {
                System.setProperty("java.net.preferIPv4Stack", "true");
                instance = new SDCLib();
                System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
                instance.log(Level.INFO, "SDCLib/J " + VERSION + " IEEE 11073 SDC Family Stack (C) SurgiTAIX AG");
                instance.log(Level.INFO, "SDCLib/J contribution fork maintained by Andreas Besting, info@besting-it.de");
                System.setProperty("com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl.fastBoot", "true");
                instance.log(Level.INFO, "Initializing class loader context...");
                try {
                    instance.jaxbUtil = JAXBUtil.getInstance();
                    instance.jaxbUtil.setStaticContext(JAXBContext.newInstance("org.ornet.cdm"));
                    if (instance.schemaValidation.get()) {
                        instance.enableSchemaValidation();
                    } else {
                        instance.disableSchemaValidation();
                    }
                } catch (JAXBException ex) {
                    Logger.getLogger(SDCLib.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
                // Init default MDPWS transport layer
                instance.transportLayerConfigMap.put(MDPWS_TRANSPORT_KEY, new MDPWSTransportLayerConfiguration());
            }
            return instance;
        }
    }

    /**
     * Get the default transport layer key.
     *
     * @return The key
     */
    public String getDefaultTransportLayerKey() {
        return transportLayerKey;
    }

    /**
     * Set the default transport layer key. SDCLib will then use the associated
     * layer as default. The default key is MDPWS.
     *
     * @param transportLayerKey The key
     */
    public void setDefaultTransportLayerKey(String transportLayerKey) {
        this.transportLayerKey = transportLayerKey;
    }

    /**
     * Remove a transport layer
     *
     * @param key The key.
     */
    public void removeTransportLayer(String key) {
        transportLayerConfigMap.remove(key);
    }

    /**
     * Add a new transport layer configuration.
     *
     * @param key The key
     * @param config The config
     */
    public void addTransportLayerConfig(String key, ISDCTransportLayerConfiguration config) {
        transportLayerConfigMap.put(key, config);
    }

    /**
     * Get a transport layer config.
     *
     * @param <T> Transport layer config template type
     * @param key The key
     * @param target Target class
     * @return The config
     */
    public <T> T getTransportLayerConfig(String key, Class<T> target) {
        return target.cast(transportLayerConfigMap.get(key));
    }

    /**
     * Get the transport layer config currently used in SDCLib. Default is MDPWS
     *
     * @param <T> Transport layer config template type
     * @param target Target class
     * @return The config
     */
    public <T> T getDefaultTransportLayerConfig(Class<T> target) {
        return getTransportLayerConfig(transportLayerKey, target);
    }

    /**
     * Get the default service manager. Default is MDPWS service manager.
     *
     * @return The service manager
     */
    public SDCServiceManager getDefaultServiceManager() {
        return getDefaultTransportLayerConfig(ISDCTransportLayerConfiguration.class).getServiceManager();
    }

    public CustomValidationHandler getCustomValidationHandler() {
        return customValidationHandler;
    }

    private void enableSchemaValidation() {
        try {
            Schema schema = getSchema();
            jaxbUtil.setValidationEventHandler(customValidationHandler, schema);
            // To enable logging, add an instance of LogValidationHandler
            customValidationHandler.addHandler(new LogValidationHandler());
            final String svl = "Schema validation is enabled.";
            if (!svl.equals(lastSchemaValidationLog)) {
                log(Level.INFO, lastSchemaValidationLog = svl);
            }
        } catch (Exception ex) {
            Logger.getLogger(SDCLib.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Schema getSchema() throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        List<Source> sources = new ArrayList<>();
        sources.add(new StreamSource(this.getClass().getResourceAsStream(WSConstants.MDPWS_XSD)));
        sources.add(new StreamSource(this.getClass().getResourceAsStream(WSConstants.EXTENSION_POINTXSD)));
        sources.add(new StreamSource(this.getClass().getResourceAsStream(WSConstants.BICEPS_PARTICIPANT_MODELXSD)));
        sources.add(new StreamSource(this.getClass().getResourceAsStream(WSConstants.BICEPS_MESSAGE_MODELXSD)));
        Schema schema = sf.newSchema(sources.toArray(new Source[0]));
        return schema;
    }

    private void disableSchemaValidation() {
        final String svl = "Schema validation is disabled.";
        if (!svl.equals(lastSchemaValidationLog)) {
            log(Level.INFO, lastSchemaValidationLog = svl);
        }
        jaxbUtil.setValidationEventHandler(null, null);
    }

    public void setSchemaValidationEnabled(boolean validate) {
        schemaValidation.set(validate);
        if (jaxbUtil != null) {
            if (validate) {
                enableSchemaValidation();
            } else {
                disableSchemaValidation();
            }
        }
    }

    public boolean isSchemaValidationEnabled() {
        return schemaValidation.get();
    }

    public void startup() {
        if (initialized.get()) {
            throw new IllegalStateException("Already initialized.");
        }
        startup(true);
    }

    public void startup(boolean initDiscovery) {
        if (initialized.get()) {
            throw new IllegalStateException("Already initialized.");
        }
        log(Level.INFO, "SDCLib startup...");
        final ISDCTransportLayerConfiguration defaultTransportLayerConfig = getDefaultTransportLayerConfig(ISDCTransportLayerConfiguration.class);
        log(Level.INFO, "Initializing transport layer: " + getDefaultTransportLayerKey());
        defaultTransportLayerConfig.startup();
        if (initDiscovery) {
            defaultTransportLayerConfig.getServiceManager().init();
        }
        initialized.set(true);
    }

    public void shutdown() {
        if (!initialized.get()) {
            throw new IllegalStateException("Not initialized.");
        }
        log(Level.INFO, "SDCLib shutdown...");
        final ISDCTransportLayerConfiguration defaultTransportLayerConfig = getDefaultTransportLayerConfig(ISDCTransportLayerConfiguration.class);
        defaultTransportLayerConfig.getServiceManager().close();
        log(Level.INFO, "Shutting down transport layer: " + getDefaultTransportLayerKey());
        defaultTransportLayerConfig.shutdown();
    }

}
