package org.ornet.sdclib.test;

import static com.bestingit.async.Task.complete;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ornet.cdm.RealTimeSampleArrayMetricState;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.consumer.SDCConsumer;
import org.ornet.sdclib.consumer.SDCConsumerStateChangedHandler;
import org.ornet.sdclib.provider.SDCProvider;
import org.ornet.sdclib.test.classes.DemoProviderFactory;
import org.ornet.sdclib.test.classes.DemoProviderFactory.DemoStreamStateHandler;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author besting
 */
public class SDCTestMDPWS {

    static SDCProvider provider = null;
    static SDCConsumer consumer = null;

    public SDCTestMDPWS() {
    }

    @BeforeClass
    public static void setUpClass() {
        SDCLib.getInstance().startup();
        SDCLib.getInstance().setSchemaValidationEnabled(false);
        provider = DemoProviderFactory.getDemoProvider("UDI-1234567890");
        provider.setTcpStreaming(true);
        provider.startup();
        // Discover using blocking complete for testing, timeout 10s.
        SDCTestMDPWS.consumer = complete(10000, SDCLib.getInstance().getDefaultServiceManager().discoverEPRAsync("UDI-1234567890"));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        provider.close();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SDCTestMDPWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        SDCLib.getInstance().shutdown();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testStreaming() {
        assertNotNull(consumer);
        if (consumer == null) {
            return;
        }
        final AtomicBoolean changeEventReceived = new AtomicBoolean(false);
        // Receive stream
        consumer.addEventHandler(new SDCConsumerStateChangedHandler<RealTimeSampleArrayMetricState>("handle_stream") {

            @Override
            public void onStateChanged(RealTimeSampleArrayMetricState state) {
                assertNotNull(state.getMetricValue());
                assertNotNull(state.getMetricValue().getSamples());
                List<BigDecimal> values = state.getMetricValue().getSamples();
                StringBuilder sb = new StringBuilder();
                sb.append("Received: ");
                for (BigDecimal next : values) {
                    sb.append(next.doubleValue()).append(" ");
                }
                System.out.println(sb.toString());
                changeEventReceived.set(true);
            }
        });
        // Produce stream
        DemoStreamStateHandler dsh = (DemoStreamStateHandler) provider.getHandler("handle_stream");
        for (int i = 0; i < 5; i++) {
            System.out.println("Sending...");
            dsh.setValueInternal(new double[]{1, 2, 3, 4, 5});
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SDCTestMDPWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        assertEquals(true, changeEventReceived.get());
        consumer.removeAllEventHandlers("handle_stream");
    }

}
