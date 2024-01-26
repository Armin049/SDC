package org.ornet.sdclib.test;

import static com.bestingit.async.Task.sleep;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import org.ornet.sdclib.SDCLib;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerConfiguration;
import org.ornet.sdclib.binding.mdpws.MDPWSTransportLayerDetail;
import org.ornet.sdclib.provider.SDCProvider;
import org.ornet.sdclib.test.classes.DemoProviderFactory;

import org.ornet.sdclib.test.BLEHeartRate;

/**
 *
 * @author besting
 */
public class ExampleProvider {

    static SDCProvider provider = null;

    public ExampleProvider() {
    }

    public static void main(String[] args) throws Exception {
    	
        final SDCLib instance = SDCLib.getInstance();
        instance.setLogLevel(Level.FINE);
        SDCLib.getInstance().startup(false);
        SDCLib.getInstance().setSchemaValidationEnabled(true);
        final MDPWSTransportLayerConfiguration tlc = SDCLib.getInstance().getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class);
        MDPWSTransportLayerDetail configuration = tlc.getConfigurationDetail();
        configuration.setBindInterface("0.0.0.0");
        //provider = DemoProviderFactory.getDemoProvider("UDI-1234567890", false, null);  // Do not include safety requirements        
        provider = DemoProviderFactory.getDemoProvider("UDI-1234567890", true, null);  // Include safety requirements
        while (true) {
            provider.startup();
            System.out.println("Provider running...");
            DemoProviderFactory.DemoStreamStateHandler dsh = (DemoProviderFactory.DemoStreamStateHandler) provider.getHandler("handle_stream");
            DemoProviderFactory.DemoNumericStateHandler dnh =  (DemoProviderFactory.DemoNumericStateHandler)  provider.getHandler("handle_metric");
            BLEHeartRate hr = new BLEHeartRate("F5:37:78:A3:F1:D0");
            System.out.println("Recieving Heart Rate Data from Sensor via Bluetooth");
            System.out.println("Start sending ECG Stream Values");
            System.out.println("Start sending Heart Rate values");
            int i = 0;
            while (i++ < 50) {
                dsh.setValueInternal(new double[]{Math.random(), Math.random(), Math.random(), Math.random()});
                //provider.updateState(DemoProviderFactory.DemoNumericStateHandler.createState("handle_metric", ThreadLocalRandom.current().nextDouble()));
                //provider.updateState(DemoProviderFactory.DemoNumericStateHandler.createState("handle_metric", (double)i));
                dnh.setValue(hr.getval());
                //dnh.setValue((double)i);
                sleep(1000);
            }
            hr.disconnect();
            System.out.println("Modifications and streaming stopped.");
            provider.close();
            sleep(Integer.MAX_VALUE);
        }
    }

}
