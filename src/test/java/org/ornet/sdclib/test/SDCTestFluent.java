package org.ornet.sdclib.test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.ornet.cdm.AbstractState;
import org.ornet.cdm.InvocationState;
import org.ornet.cdm.LocationContextState;
import org.ornet.sdclib.SDCFluent;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.provider.SDCFluentStateChangeRequestContext;
import org.ornet.sdclib.test.classes.DemoProviderFactory;
import org.ornet.sdclib.test.classes.DemoProviderFactory.DemoAlertSignalStateHandler;
import org.ornet.sdclib.test.classes.DemoProviderFactory.DemoNumericStateHandler;
import org.ornet.sdclib.test.classes.DemoProviderFactory.DemoContextStateHandler;
import org.ornet.sdclib.test.classes.DemoProviderFactory.DemoLimitAlertConditionStateHandler;
import org.ornet.sdclib.test.classes.DemoProviderFactory.DemoStreamStateHandler;

/**
 *
 * @author besting
 */
public class SDCTestFluent {

    private BlockingDeque<AbstractState> dequeConsumer;
    private static BlockingDeque<SDCFluentStateChangeRequestContext> dequeProvider;

    public SDCTestFluent() {
    }

    @BeforeClass
    public static void setUpClass() {
        SDCFluent.JoinSDCNetwork();
        SDCLib.getInstance().setLogLevel(Level.FINE);
        Set<AbstractState> states = new HashSet<>();
        // Create initial states for descriptors
        states.add(DemoNumericStateHandler.createState(DemoNumericStateHandler.HANDLE_METRIC, 0));
        states.add(DemoContextStateHandler.createState(DemoProviderFactory.HANDLE_CONTEXT, "Test", LocationContextState.class));
        states.add(DemoLimitAlertConditionStateHandler.createState(DemoLimitAlertConditionStateHandler.HANDLE_LIMIT_ALERT_CONDITION, 0, 2));
        states.add(DemoAlertSignalStateHandler.createState(DemoAlertSignalStateHandler.HANDLE_ALERT_SIGNAL_LATCHING));
        states.add(DemoStreamStateHandler.createState(DemoStreamStateHandler.HANDLE_STREAM, null));
        // Create device member by providing MDS (descriptors) and states
        dequeProvider = SDCFluent.CreateLocalMember("UDI-1234567890", DemoProviderFactory.getDemoMds(), states);
    }

    @AfterClass
    public static void tearDownClass() {
        SDCFluent.LeaveSDCNetwork();
    }

    @Before
    public void setUp() {
        // Enable reception of events works even when device has not yet been discovered
        dequeConsumer = SDCFluent.EnableEventing("UDI-1234567890");
        // Wait a while to let fluent discover our member
        sleep(8000);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(SDCTestFluent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFluentDiscovery() {
        assertTrue(SDCFluent.GetMembers().contains("UDI-1234567890"));
    }

    @Test
    public void testSetGetFluent() {
        AtomicBoolean eventReceived = new AtomicBoolean(false);
        // Events provider -> consumer
        SDCFluent.HandleAsyncEvents(dequeConsumer, (AbstractState state) -> {
            eventReceived.set(true);
        });
        // Provider handler (just return FIN)
        SDCFluent.HandleAsyncRequests(dequeProvider, (SDCFluentStateChangeRequestContext state) -> {
            state.endNotify(InvocationState.FIN);
        });
        double value = 8;
        assertTrue(SDCFluent.SetSimpleNumberValue("UDI-1234567890", DemoProviderFactory.DemoNumericStateHandler.HANDLE_METRIC, value, 2000));
        value = 5;
        // Set, Get, Event test
        assertTrue(SDCFluent.SetSimpleNumberValue("UDI-1234567890", DemoProviderFactory.DemoNumericStateHandler.HANDLE_METRIC, value, 2000));
        assertEquals(value, SDCFluent.GetSimpleNumberValue("UDI-1234567890", DemoProviderFactory.DemoNumericStateHandler.HANDLE_METRIC), 0);
        // Sleep a while before checking events
        sleep(1000);
        assertEquals(true, eventReceived.get());
    }

}
