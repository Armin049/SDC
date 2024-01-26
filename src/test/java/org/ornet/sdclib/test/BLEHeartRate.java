package org.ornet.sdclib.test;

import java.time.Duration;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import example.central.ConnectedNotification;
//import example.central.ValueNotification;
import tinyb.BluetoothDevice;
import tinyb.BluetoothGattCharacteristic;
import tinyb.BluetoothGattService;
import tinyb.BluetoothManager;
import tinyb.BluetoothNotification;

class ValueNotification implements BluetoothNotification<byte[]> {
	float value = 0;
	
    public void run(byte[] heartRateRaw) {
    	/*  
    	 * TODO 5.1: Print the raw bytes of the payload
    	 * TODO 5.2: Extract the heart rate value (hint: find the function,  
    	 *    which encodes the payload in the nRF Connect framework)  
    	 * TODO 5.3: Print out the heart rate including a measurement unit   
    	 */
    	this.value = heartRateRaw[1];
    	//System.out.println("HeartRate: " + heartRateRaw[1] + "bpm");
    	//if(heartRateRaw[1]>100){
    		//System.out.println("ALERT! Heartrate too high");
    	//}
    	
    }
    public float getValNotify(){
    	return this.value;
    }

}

class ConnectedNotification implements BluetoothNotification<Boolean> {

    public void run(Boolean connected) {
            System.out.println(connected);
    }

}


public class BLEHeartRate{
    private static final String HEART_RATE_SERVICE_UUID = "0000180d-0000-1000-8000-00805f9b34fb";
    private static final String HEART_RATE_MEASUREMENT_CHARACTERISTIC_UUID = "00002a37-0000-1000-8000-00805f9b34fb";
    private BluetoothDevice heartRateSensor;
    //static boolean running = true;
	private BluetoothGattCharacteristic heartRateValue;
	private String nrf_mac_addr;
	private ValueNotification notify;
	
	public BLEHeartRate(String nrf_mac_addr){
		
		this.nrf_mac_addr = nrf_mac_addr;
		
		/* Initialize the library using the BluetoothManager. */
        BluetoothManager manager = BluetoothManager.getBluetoothManager();

        /* Start discovery process on default BT interface. */
        boolean discoveryStarted = manager.startDiscovery();
        
        System.out.println("The discovery started: " + (discoveryStarted ? "true" : "false"));

        /* 
         * Find the device based on the MAC address. 
         * TODO 1: Change the MAC address constant according to your device.
         */
        this.heartRateSensor = manager.find(null, this.nrf_mac_addr, null, Duration.ofSeconds(10));
        
        if (this.heartRateSensor == null) {
            System.err.println("No sensor found with the provided address.");
            System.exit(-1);
        }

        this.heartRateSensor.enableConnectedNotifications(new ConnectedNotification());

        /* If we can discover our device, print some device-specific information (see printDevice function). */
        System.out.print("Found device: ");
        printDevice(this.heartRateSensor);

        /* Try to connect to the device. */
        if (this.heartRateSensor.connect())
            System.out.println("Sensor with the provided address connected");
        else {
            System.out.println("Could not connect device.");
            System.exit(-1);
        }

        /* After we find the device, we can stop looking for other devices. */
        manager.stopDiscovery();

        //Lock lock = new ReentrantLock();
        //Condition cv = lock.newCondition();
        
        /* Controlled shutdown of the application, e.g. when "ctrl + c" is signaled */
        
        this.notify = new ValueNotification();
        this.heartRateValue = this.heartRateSensor.find(HEART_RATE_SERVICE_UUID).find(HEART_RATE_MEASUREMENT_CHARACTERISTIC_UUID);
        this.heartRateValue.enableValueNotifications(this.notify);
        //System.out.println("NEwVal" + heartRateValue.getValue());
        
        /* Run the program as long as run condition holds (see shutdownHook) */
        /* Run the program as long as run condition holds (see shutdownHook) */
      
		
	}
	public void disconnect(){
		  System.out.println("Disconnect");
	        /* Disconnect the device */
	       this.heartRateSensor.disconnect();
	       
	}
	
	public float getval(){
		//return this.heartRateValue.getValue()[1];  //not allowed
		return this.notify.getValNotify(); //causes crash
	}
	
	static void printDevice(BluetoothDevice device) {
    	/* 
    	 * TODO 2.1: print device address
    	 * 
    	 * TODO 2.2: print the device name
    	 * TODO 2.3: print the connection status 
	     */
    	System.out.println("Address" + device.getAddress());
    	System.out.println("Name:" + device.getName());
    	System.out.println("Conncetion Status:" + device.getConnected());
    }
	
}
