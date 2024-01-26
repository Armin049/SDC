package org.ornet.sdclib.test;

import static com.bestingit.async.Task.complete;
import static com.bestingit.async.Task.sleep;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.SelfSignedCertificate;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.ornet.cdm.*;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.consumer.FutureInvocationState;
import org.ornet.sdclib.consumer.SDCConsumer;
import org.ornet.sdclib.consumer.SDCConsumerEventHandler;
import org.ornet.sdclib.consumer.SDCConsumerOperationInvokedHandler;
import org.ornet.sdclib.consumer.SDCConsumerStateChangedHandler;
import org.ornet.sdclib.provider.SDCProvider;
import org.ornet.sdclib.provider.OperationInvocationContext;
import org.ornet.sdclib.test.classes.DemoProviderFactory;

/**
 *
 * @author besting
 */
public class SDCTest {

    static SDCProvider provider = null;
    static SDCConsumer consumer = null;

    public SDCTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        final SDCLib instance = SDCLib.getInstance();
        instance.startup();
        instance.setLogLevel(Level.INFO);
        instance.setSchemaValidationEnabled(true);
        MDPWSTransportLayerConfiguration tlc = instance.getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class);
        HttpClientOptions co = tlc.getConfigurationDetail().getClientOptions();
        co.setSsl(true).setTrustAll(true).setVerifyHost(false);
        HttpServerOptions so = tlc.getConfigurationDetail().getServerOptions();
        SelfSignedCertificate certificate = SelfSignedCertificate.create();
        so.setSsl(true).setKeyCertOptions(certificate.keyCertOptions()).setTrustOptions(certificate.trustOptions());
        provider = DemoProviderFactory.getDemoProvider("UDI-1234567890", true, null);
        provider.startup();
        // Discover using blocking complete for testing, timeout 10s.
        SDCTest.consumer = complete(10000, SDCLib.getInstance().getDefaultServiceManager().discoverEPRAsync("UDI-1234567890"));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        provider.close();
        SDCLib.getInstance().shutdown();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMDIB() {
        assertEquals(provider.isRunning(), true);
        assertNotNull(consumer);
        if (consumer == null) {
            return;
        }
        long startTime = System.currentTimeMillis();
        assertEquals(true, consumer.getMDIB() != null);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time Get MDIB: " + elapsedTime);
    }

    @Test
    public void testGetSetMetric() {
        assertNotNull(consumer);
        if (consumer == null) {
            return;
        }
        final AtomicBoolean changeEventReceived = new AtomicBoolean(false);
        consumer.addEventHandler(new SDCConsumerEventHandler<NumericMetricState>("handle_metric") {

            @Override
            public void onOperationInvoked(OperationInvocationContext oic, InvocationState is) {
                System.out.println("Received OIR, handle = " + getDescriptorHandle() + ", IS = " + is.name());
            }

            @Override
            public void onStateChanged(NumericMetricState state) {
                changeEventReceived.set(true);
            }
        });
        // Get metric
        NumericMetricState nms = consumer.requestState("handle_metric", NumericMetricState.class);
        assertNotNull(nms);
        assertNotNull(nms.getMetricValue());
        assertNotNull(nms.getMetricValue().getValue());
        // Set metric state
        FutureInvocationState fis = new FutureInvocationState();
        nms.getMetricValue().setValue(BigDecimal.valueOf(2));
        long startTime = System.currentTimeMillis();
        assertEquals(InvocationState.WAIT, consumer.commitState(nms, fis));
        long stopTime = System.currentTimeMillis();
        assertEquals(true, fis.waitReceived(new InvocationState[] { InvocationState.FIN, InvocationState.FIN_MOD }, 2000));
        long elapsedTime = stopTime - startTime;
        System.out.println("Time Set numeric state: " + elapsedTime);
        // Set value using async style
        AtomicBoolean asyncSetValueResult = new AtomicBoolean(false);
        assertEquals(InvocationState.WAIT, consumer.commitValue(nms.getDescriptorHandle(),
                nms.getMetricValue(),
                new FutureInvocationState().receiveAsync((InvocationState state) -> {
                    if (state == InvocationState.FIN || state == InvocationState.FIN_MOD) {
                        asyncSetValueResult.set(true);
                    }
                })));
        // Get again and compare
        startTime = System.currentTimeMillis();
        nms = consumer.requestState("handle_metric", NumericMetricState.class);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Time Get Value: " + elapsedTime);
        assertNotNull(nms);
        assertNotNull(nms.getMetricValue());
        assertNotNull(nms.getMetricValue().getValue());
        assertEquals(BigDecimal.valueOf(2), nms.getMetricValue().getValue());
        sleep(1000);
        assertEquals(true, changeEventReceived.get());
        assertEquals(true, asyncSetValueResult.get());
        // Test get / set value
        fis = new FutureInvocationState();
        assertEquals(InvocationState.WAIT, consumer.commitValue("handle_metric", nms.getMetricValue(), fis));
        assertEquals(true, fis.waitReceived(new InvocationState[] { InvocationState.FIN, InvocationState.FIN_MOD }, 2000));
        consumer.removeAllEventHandlers("handle_metric");
        // Let's be sure to clear all event handlers and see if we can still invoke (OIR should still be enabled)
        consumer.clearEventHandlers();
        fis = new FutureInvocationState();
        nms.getMetricValue().setValue(BigDecimal.valueOf(3));
        SafetyInfoType safetyInfo = new SafetyInfoType();
        SafetyContextType safetyContext = new SafetyContextType();
        final CtxtValueType ctxtValue = new CtxtValueType();
        ctxtValue.setReferencedSelector("id_handle_metric");
        ctxtValue.setValue("2");
        safetyContext.getCtxtValue().add(ctxtValue);
        safetyInfo.setSafetyContext(safetyContext);
        assertEquals(InvocationState.WAIT, consumer.commitState(nms, fis, safetyInfo));
        assertEquals(true, fis.waitReceived(new InvocationState[] { InvocationState.FIN, InvocationState.FIN_MOD }, 2000));
    }
    
    @Test
    public void testPeriodicEvents() {
        assertNotNull(consumer);
        if (consumer == null) {
            return;
        }
        final AtomicBoolean periodicEventReceived = new AtomicBoolean(false);
        consumer.addEventHandler(new SDCConsumerStateChangedHandler<NumericMetricState>("handle_metric") {

            @Override
            public void onStateChanged(NumericMetricState state) {
                periodicEventReceived.set(true);
                System.out.println("Received periodic report, handle = " + getDescriptorHandle());
            }
        });
        // Switch on periodic events
        provider.enablePeriodicEvents(500);
        sleep(3000);
        assertEquals(true, periodicEventReceived.get());       
        provider.disablePeriodicEvents();
    }    

    @Test
    public void testGetSetContext() {
        assertNotNull(consumer);
        if (consumer == null) {
            return;
        }
        final AtomicBoolean changeEventReceived = new AtomicBoolean(false);
        final AtomicBoolean asyncFISreceived = new AtomicBoolean(false);
        consumer.addEventHandler(new SDCConsumerEventHandler<LocationContextState>("handle_context") {

            @Override
            public void onOperationInvoked(OperationInvocationContext oic, InvocationState is) {
                System.out.println("Received OIR, handle = " + getDescriptorHandle() + ", IS = " + is.name());
            }

            @Override
            public void onStateChanged(LocationContextState state) {
                changeEventReceived.set(true);
            }

        });
        // Get context
        LocationContextState lcs = consumer.requestState("handle_context", LocationContextState.class);
        assertNotNull(lcs);
        assertEquals(true, lcs.getIdentification().size() == 1);
        // Set context
        InstanceIdentifier instId = new InstanceIdentifier();
        instId.setRoot("root");
        instId.setExtension("TestSet");
        lcs.getIdentification().set(0, instId);
        // Test async FIS receiver
        consumer.commitState(lcs, new FutureInvocationState().receiveAsync((InvocationState state) -> {
            if (state == InvocationState.FIN) {
                asyncFISreceived.set(true);
            }
        }));
        sleep(1000);
        // Get again and compare
        lcs = consumer.requestState("handle_context", LocationContextState.class);
        assertNotNull(lcs);
        assertEquals(true, lcs.getIdentification().get(0).getExtension().equals("TestSet"));
        assertEquals(true, changeEventReceived.get());
        assertEquals(true, asyncFISreceived.get());
        consumer.removeAllEventHandlers("handle_context");
    }

    @Test
    public void testActivate() {
        assertNotNull(consumer);
        if (consumer == null) {
            return;
        }
        final AtomicBoolean invokeEventReceived = new AtomicBoolean(false);
        consumer.addEventHandler(new SDCConsumerOperationInvokedHandler("handle_cmd") {

            @Override
            public void onOperationInvoked(OperationInvocationContext oic, InvocationState is) {
                System.out.println("Received OIR, handle = " + getDescriptorHandle() + ", IS = " + is.name());
                invokeEventReceived.set(true);
            }

        });
        FutureInvocationState fis = new FutureInvocationState();
        assertEquals(InvocationState.WAIT, consumer.activate("handle_cmd", fis));
        assertEquals(true, fis.waitReceived(new InvocationState[]{InvocationState.START, InvocationState.FIN}, 2000));
        sleep(1000);
        assertEquals(true, invokeEventReceived.get());
        consumer.removeAllEventHandlers("handle_cmd");
    }

    @Test
    public void testAlerts() {
        // register for alarm signal events
        final AtomicBoolean onReceived = new AtomicBoolean(false);
        final AtomicBoolean latchReceived = new AtomicBoolean(false);
        consumer.addEventHandler(new SDCConsumerStateChangedHandler<AlertSignalState>("handle_alert_signal_latching") {

            @Override
            public void onStateChanged(AlertSignalState state) {
                if (state.getPresence() == AlertSignalPresence.ON) {
                    onReceived.set(true);
                }
                if (state.getPresence() == AlertSignalPresence.LATCH) {
                    latchReceived.set(true);
                }
            }

        });
        // Get metric
        NumericMetricState nms = consumer.requestState("handle_metric", NumericMetricState.class);
        assertNotNull(nms);
        assertNotNull(nms.getMetricValue());
        // Set metric to value above upper limit (0, 2), alert condition triggers alert signal ON
        FutureInvocationState fis = new FutureInvocationState();
        nms.getMetricValue().setValue(BigDecimal.valueOf(3));
        assertEquals(InvocationState.WAIT, consumer.commitState(nms, fis));
        assertEquals(true, fis.waitReceived(InvocationState.FIN, 2000));
        // Set metric to value within bounds (0, 2), alert condition triggers alert signal LATCHED
        fis = new FutureInvocationState();
        nms.getMetricValue().setValue(BigDecimal.valueOf(1));
        assertEquals(InvocationState.WAIT, consumer.commitState(nms, fis));
        assertEquals(true, fis.waitReceived(InvocationState.FIN, 2000));
        // Check if events have been received
        sleep(1000);
        assertEquals(true, onReceived.get());
        assertEquals(true, latchReceived.get());
        // Test get/set alert signal
        AlertSignalState cas = consumer.requestState("handle_alert_signal_latching", AlertSignalState.class);
        assertNotNull(cas);
        assertNotSame(cas.getPresence(), AlertSignalPresence.OFF);
        cas.setPresence(AlertSignalPresence.OFF);
        fis = new FutureInvocationState();
        assertEquals(InvocationState.WAIT, consumer.commitState(cas, fis));
        assertEquals(true, fis.waitReceived(InvocationState.FIN, 2000));
        // Compare
        cas = consumer.requestState("handle_alert_signal_latching", AlertSignalState.class);
        assertEquals(cas.getPresence(), AlertSignalPresence.OFF);
    }

}
