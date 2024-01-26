package org.ornet.sdclib.test;

import com.bestingit.async.AsyncMethodInvocationException;
import com.bestingit.async.Completer;
import static com.bestingit.async.Task.*;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.SelfSignedCertificate;
import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.ornet.cdm.MdDescription;
import org.ornet.cdm.MdState;
import org.ornet.cdm.Mdib;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.consumer.SDCConsumer;
import org.ornet.sdclib.provider.SDCProvider;
import org.ornet.sdclib.test.classes.DemoProviderFactory;

/**
 *
 * @author besting
 */
public class SDCAsyncTests {

    static SDCProvider provider = null;
    static SDCConsumer consumer = null;

    public SDCAsyncTests() {
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
        provider = DemoProviderFactory.getDemoProvider("UDI-1234567890");
        provider.startup();
        // Discover using blocking complete for testing, timeout 10s.
        SDCAsyncTests.consumer = complete(10000, SDCLib.getInstance().getDefaultServiceManager().discoverEPRAsync("UDI-1234567890"));
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

    /**
     * Test some features provided by
     * https://bitbucket.org/besting-it/asynccomplete
     */
    @Test
    public void testGetRemoteAsync() {
        assertEquals(provider.isRunning(), true);
        assertNotNull(consumer);

        /*
         * Test first async function getting the Mdib, non-blocking, lambda-style.
         * Get Mdib (async), handle exception and success case
         */
        Completer<Mdib> futureMbib = consumer.getMDIBAsync();
        futureMbib.result(res -> {
            try {
                res.unwrap();  // Throws inner exception here, if anything goes wrong
                // In case of success
                Mdib mdib = complete(res);  // without res.unrap() this would throw an AsyncMethodInvocationException
                System.out.println("Found mdib, version: " + mdib.getMdibVersion());
                assertEquals(Long.valueOf(1), mdib.getMdibVersion());
            } catch (Exception ex) {
                // Handle exception here (already converted to original exception)
                System.err.println(ex.getMessage());
            }
        });

        /*
         * Test two more async functions using "feed" and sequential style
         */
        // Get MdDescription and MdSate	(async)
        Completer<MdDescription> futureMdd = consumer.getMDDescriptionAsync();
        Completer<MdState> futureMdState = consumer.getMDStateAsync();

        // Some async output (as soon as results are ready, lambda-style, ignoring exceptions)
        futureMdd.feed(mdd -> {
            System.out.println("(1) MDS count: " + mdd.getMds().size());
        });
        futureMdState.feed(mdState -> {
            System.out.println("(1) State count: " + mdState.getState().size());
        });

        /*
         * Complete / wait for all here to be able to continue below with the actual results.
         * This is a blocking wait!
         */
        all(futureMbib, futureMdd, futureMdState);

        // Example: using "all" with timeout, may throw an AsyncTimeoutException
        //all(3000, futureMbib, futureMdd, futureMdState);
        // Example: wait for first to finish
        //Completer first = any(futureMbib, futureMdd, futureMdState);
        // Continue sequential style		
        MdState mdState = complete(futureMdState); // Already finished (used "all" before), returns at once, may throw exception which is ignored here!
        assertEquals(14, mdState.getState().size());
        System.out.println("(2) State count: " + mdState.getState().size());

        // Another method handling possible exceptions
        try {
            MdDescription mdd = complete(futureMdd); // Already finished (used "all" before), returns at once
            assertEquals(1, mdd.getMds().size());
            System.out.println("(2) MDS count: " + mdd.getMds().size());
        } catch (AsyncMethodInvocationException ex) {
            Exception e = ex.getUnwrappedCause();  // Get original exception, if any
            System.err.println(e.getMessage());
        }
    }

}
