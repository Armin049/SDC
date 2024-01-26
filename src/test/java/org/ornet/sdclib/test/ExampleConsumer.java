package org.ornet.sdclib.test;

import static com.bestingit.async.Task.complete;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.ornet.cdm.CtxtValueType;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.NumericMetricState;
import org.ornet.cdm.NumericMetricValue;
import org.ornet.cdm.RealTimeSampleArrayMetricState;
import org.ornet.cdm.SafetyContextType;
import org.ornet.cdm.SafetyInfoType;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerDetail;
import org.ornet.sdclib.consumer.FutureInvocationState;
import org.ornet.sdclib.consumer.SDCConsumer;
import org.ornet.sdclib.consumer.SDCConsumerEventHandler;
import org.ornet.sdclib.consumer.SDCConsumerStateChangedHandler;
import org.ornet.sdclib.consumer.SDCLifecycleHandler;
import org.ornet.sdclib.provider.OperationInvocationContext;

/**
 *
 * @author besting
 */
public class ExampleConsumer {

    static SDCConsumer consumer = null;

    public ExampleConsumer() {
    }

    @BeforeClass
    public static void setUpClass() {
    	
        final SDCLib instance = SDCLib.getInstance();
        instance.setLogLevel(Level.FINE);
        instance.startup();
        instance.setSchemaValidationEnabled(true);
        MDPWSTransportLayerDetail configuration = SDCLib.getInstance().getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class).getConfigurationDetail();
        configuration.setPortStart(30000);
        // Discover using blocking complete for testing, timeout 10s.
        consumer = complete(10000, SDCLib.getInstance().getDefaultServiceManager().discoverEPRAsync("UDI-1234567890"));
        consumer.addEventHandler(new SDCConsumerEventHandler<NumericMetricState>("handle_metric") {

            @Override
            public void onOperationInvoked(OperationInvocationContext oic, InvocationState is) {
                System.out.println("Received operation invoked (handle CUR): " + is.name());
            }

            @Override
            public void onStateChanged(NumericMetricState state) {
                System.out.println("Received Heart Rate Value: " + ((NumericMetricState) state).getMetricValue().getValue().doubleValue() + " bpm");
            }
        });
        consumer.addEventHandler(new SDCConsumerStateChangedHandler<RealTimeSampleArrayMetricState>("handle_stream") {

            @Override
            public void onStateChanged(RealTimeSampleArrayMetricState state) {
                assertNotNull(state.getMetricValue());
                assertNotNull(state.getMetricValue().getSamples());
                List<BigDecimal> values = state.getMetricValue().getSamples();
                StringBuilder sb = new StringBuilder();
                sb.append("Received ECG values: ");
                int i=0;
                for (BigDecimal next : values) {
                	i++;
                    sb.append("channel "+ i +": "+ next.doubleValue() + " mV").append(" ");
                }
                System.out.println(sb.toString());
            }
        });
        consumer.addLifecycleHandler(new SDCLifecycleHandler() {
            @Override
            public void onConnectionLost(SDCConsumer consumer) {
                System.out.println("Connection lost!");
            }

            @Override
            public void onConnectionReestablished(SDCConsumer consumer) {
                System.out.println("Connection reestablished!");
            }

            @Override
            public void onClosed(SDCConsumer consumer) {
                System.out.println("Connection closed!");
            }

            @Override
            public void onOpened(SDCConsumer consumer) {
                System.out.println("Connection opened!");
            }
        });
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ExampleConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        SDCLib.getInstance().shutdown();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            Logger.getLogger(ExampleConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetSet() {
        assertNotNull(consumer);
        if (consumer == null) {
            return;
        }
        // Get
        NumericMetricState nms = consumer.requestState("handle_metric", NumericMetricState.class);
        assertNotNull(nms);
        assertNotNull(nms.getMetricValue());
        assertNotNull(nms.getMetricValue().getValue());
        // SafetyInfo
        SafetyInfoType si = new SafetyInfoType();
        SafetyContextType sc = new SafetyContextType();
        si.setSafetyContext(sc);
        CtxtValueType cv = new CtxtValueType();
        cv.setReferencedSelector("id_handle_metric");
        sc.getCtxtValue().add(cv);
        // get / set
        for (int i = 0; i < 10; i++) {
            FutureInvocationState fis = new FutureInvocationState();
            // Set safety context value to expected value (just read out current value before setting)
            final NumericMetricValue metricValue = consumer.requestState("handle_metric", NumericMetricState.class).getMetricValue();
            cv.setValue(Double.toString(metricValue.getValue().doubleValue()));
            long startTime = System.currentTimeMillis();
            // Set current value +0.1 again
            metricValue.setValue(BigDecimal.valueOf(metricValue.getValue().doubleValue() + 0.1));
            assertEquals(InvocationState.WAIT, consumer.commitState(nms, fis, si));
            long stopTime = System.currentTimeMillis();
            assertEquals(true, fis.waitReceived(InvocationState.FIN, 5000));
            long elapsedTime = stopTime - startTime;
            System.out.println("Time Set Value (including safety info): " + elapsedTime);
        }
    }

}
