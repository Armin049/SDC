# README #

This is a contribution fork of SDCLib/J, a pure java IEEE 11073 SDC Family compatible API.

As former main author of the official project, I will maintain this fork to implement the newest features and bugfixes. For details of the official project 
please refer to the [official repository](https://bitbucket.org/surgitaix/sdclib) where changes will be available via pull requests.

## License ##

SDCLib is licensed under the GNU General Public License version 3. See the [official repository](https://bitbucket.org/surgitaix/sdclib) for further options.

### What is this repository for? ###

The SDCLib/J Java library implements essential parts of a communication protocol for point-of-care (PoC) medical devices. The protocol is specified by 
the IEEE 11073 service-oriented device connectivity (SDC) standard documents. The master branch contains a stable version. Although this library was created 
using thorough architecture and coding techniques, it does not explicitly follow life cycle requirements for the development of medical devices software.
See further notes at the end of this document.

### Getting started ###

The library has a singelton entry point (SDCLib) which must be used for initialization and configuration. It provides a ServiceManager
component which can be used for the discovery of device's endpoints.

Furthermore there are two major components which can be used:

* SDCConsumer: The (consuming) client part. Can be used for obtaining information, receiving events and manipulating values of a provider. Consumer instances are controlled by the library and can be obtained using the ServiceManager's search methods.
* SDCProvider: The device part representing values and functions of the actual medical device (e.g. sensor values or settings).

### Library initialization and network configuration ###

Example for library init with schema validation enabled:

```java
final SDCLib instance = SDCLib.getInstance();
instance.setLogLevel(Level.INFO);
instance.startup(); // You may want to pass 'false' in case you implement a provider. This will turn discovery off.
instance.setSchemaValidationEnabled(true);
```

Example for library shutdown:

```java
instance.shutdown();
```

Example for MDPWS network configuration:

```java
var config = instance.getDefaultTransportLayerConfig(MDPWSTransportLayerConfiguration.class).getConfigurationDetail();
config.setPortStart(30000); // Listening ports will start at 30000
config.setBindInterface("192.168.1.1"); // Binds to interface with that IP address, default is 0.0.0.0 (all interfaces)
config.setCommTimeout(5000); // Socket timeout in milliseconds
config.setTimeToLive(5); // Multicast TTL
```

### SDC Consumer examples ###

An SDC Consumer (client) is the representation of a remote SDC Provider (device). Using the service manager, instances of consumers can be obtained.

Example for discovery of a specific provider by known EPR (endpoint reference), async callback method:

```java
var serviceManager = SDCLib.getInstance().getDefaultServiceManager();
serviceManager.discoverEPRAsync("EPR-1234").feed(this::onDiscovery);

private void onDiscovery(SDCConsumer consumer) {
	System.out.println("Consumer was found. Check for any MDS and UDI follows.");
    var mds = consumer.getMDDescription().getMds().stream().findAny();
    if (!mds.isPresent())
        return;
	var udi = mds.get().getMetaData().getUdi().stream().findAny();
    if (!udi.isPresent())
        return;	
    System.out.println("Device found with an MDS UDI: " + udi);
}
```

Example for discovery of a specific provider by known EPR (endpoint reference), including wait on complete with a 10 seconds timeout:

```java
var serviceManager = SDCLib.getInstance().getDefaultServiceManager();
var consumer = complete(10000, serviceManager.discoverEPRAsync("EPR-1234"))); // May throw an AsyncTimeoutException
```

Example for getting all currently available consumers:

```java
var serviceManager = SDCLib.getInstance().getDefaultServiceManager();
var consumers = serviceManager.getAvailableSDCConsumers(); // Get a snapshot of all consumers
```

Example for adding a listener for added / removed consumers:

```java
serviceManager.getObservableMap().addListener((MapChangeListener.Change<? extends String, ? extends SDCConsumer> change) -> {
    if (change.wasAdded()) {
        var consumer = change.getValueAdded();
        System.out.println("Consumer added: " + consumer.getEndpointReference());
    }
    else if (change.wasRemoved()) {  // Since version 9.6.0
        var consumer = change.getValueRemoved();
        System.out.println("Consumer removed: " + consumer.getEndpointReference());
    }
});
```

The listener described above will be called each time a consumer announces itself or removes itself from the network. Loosing  a connection and reconnecting e.g. after a network failure will also trigger the listeners.

The following listener is called only once after a provider is found for the first time. It can be used to perform operations on the consumer that need to be performed only once (e.g. event handler registration).

```java
serviceManager.getBinding().addConsumerJoinedFirstHandler(consumer -> {
    System.out.println("Consumer first joined: " + consumer.getEndpointReference());
    // Example: register some handlers
    consumer.addLifecycleHandler(...);
    consumer.addEventHandler(...);
});
```

Even if the consumers leaves and joines the network again, the handler will not be called again.

Example for lifecycle handler registration:

```java
consumer.addLifecycleHandler(new SDCLifecycleHandler() {
    @Override
    public void onConnectionLost(SDCConsumer consumer) {
        System.out.println("Connection lost.");
    }

    @Override
    public void onConnectionReestablished(SDCConsumer consumer) {
        System.out.println("Connection reestablished.");
    }

    @Override
    public void onClosed(SDCConsumer consumer) {
        System.out.println("Connection closed.");
    }

    @Override
    public void onOpened(SDCConsumer consumer) {
        System.out.println("Connection opened.");
    }
});
```

Example for event handler registration:

```java
consumer.addEventHandler(new SDCConsumerEventHandler<NumericMetricState>("handle_metric") {

    @Override
    public void onOperationInvoked(OperationInvocationContext oic, InvocationState is) {
        System.out.println("Received operation invoked event, state = " + is.name());
    }

    @Override
    public void onStateChanged(NumericMetricState state) {
		String handle = state.getDescriptorHandle();
        double value = state.getMetricValue().getValue().doubleValue();
        System.out.println("Received metric state changed event, handle = " + handle + ", value = " + value);
    }
});
```

Example for getting, changing and setting values:

```java
// Get remotely
var state = complete(consumer.requestStateAsync("handle_metric", NumericMetricState.class));

// Change
var metricValue = state.getMetricValue();
metricValue.setValue(BigDecimal.valueOf(3.14));

// Set remotely
var invocationState = complete(consumer.commitValueAsync(state.getDescriptorHandle(),
    metricValue,
    new FutureInvocationState().receiveAsync((InvocationState is) -> {
        if (is == InvocationState.FIN || is == InvocationState.FIN_MOD) {
            System.out.println("Finished");
        }
    })));
System.out.println("Direct response is " + invocationState); // Probably WAIT or FAIL
```

### SDC Provider examples ###

Implementing an SDC Provider is a complex task and beyond the scope of this introduction. Please contact the OR.NET association (www.ornet.org) for more information on workshops.

### SDC Fluent API ###

The SDC Fluent API (SDCFluent) provides a flat convenience wrapper hiding consumer, provider and service manager components for a slightly limited
but easy way to access typical SDC functions (e.g. discovery, getting and setting values). Please refer to th API documentation of the SDCFluent class.

### Best practices for handler implementations ###

SDCLib/J is based on vertx.io (https://vertx.io). When using vertx, it's fundamentally important not to block in code (at least not for 
a long time). Being a framework, the SDCLib/J provides several extension points (or handlers) where client code must be implemented. These
implementations should also not contain blocking code. For more information see [vertx-golden-rule](https://vertx.io/docs/vertx-core/java/#golden_rule).

SDCLib/J provides some asynchronous methods for non-blocking cases (e.g. in the SDCConsumer). These methods have suffixes named "Async".
Prefer using these methods over the blocking counterparts.
Also, consider using non-blocking Java code (Futures, CompletableFutures) or libraries such as [async-complete](https://bitbucket.org/besting-it/asynccomplete).
The latter has also been used by SDCLib/J and is therefore available, when you reference the library in your project.

An example for non-blocking best practice using asynccomplete in an SDC Provider handler (e.g. extending SDCProviderMDStateHandler<NumericMetricState>) 
would be one of the follwing cases:

*1. Instant change possible*
```java
@Override
public InvocationState onStateChangeRequest(NumericMetricState state, OperationInvocationContext oic) {
    double value = state.getMetricValue().getValue().doubleValue();
    System.out.println("Provider: received numeric value change request from consumer: " + value);
    // TODO: update medical device data here if this involves no delay!    
    return InvocationState.FIN;  // Let SDClib update the Mdib using FIN or FIN_MOD, also notifies consumer automatically    
}
```

*2. Async change (short duration)*
```java
@Override
public InvocationState onStateChangeRequest(NumericMetricState state, OperationInvocationContext oic) {
    double value = state.getMetricValue().getValue().doubleValue();
    System.out.println("Provider: received numeric value change request from consumer: " + value);
    // Using async statement will execute on a fixed thread pool
    async(() -> {
        // Asynchronous handling of changing device states, short delay
        // TODO: update medical device data here!
        updateState(state);  // Update Mdib
        notifyOperationInvoked(oic, InvocationState.FIN_MOD);  // Notifies consumer that operation is finished
    });
    // Returns instantaneous
    return InvocationState.WAIT;            
}
```

*3. Blocking change (long duration)*
```java
@Override
public InvocationState onStateChangeRequest(NumericMetricState state, OperationInvocationContext oic) {
    double value = state.getMetricValue().getValue().doubleValue();
    System.out.println("Provider: received numeric value change request from consumer: " + value);
    // Using blocking statement will ensure executing in a separate thread
    blocking(() -> {
        // Asynchronous handling of changing device states, long delay
        // TODO: update medical device data here!
        updateState(state);  // Update Mdib
        notifyOperationInvoked(oic, InvocationState.FIN_MOD);  // Notifies consumer that operation is finished
    });
    // Returns instantaneous
    return InvocationState.WAIT;            
}
```

Make sure to use this pattern also on handlers registered in the SDCConsumer!

### Changelog ###

## 10.3.0c ##
2023/09/23

* pom.xml updates
* Bugfixes from contributors

## 10.1.0c ##
2022/05/03

* Fixed CRLF related problems in SOAP messages

## 10.0.3c ##
2022/02/04

* Fixed service address generation for subscribe requests

## 10.0.2c ##
2022/01/22

* Compatibility improvements from last SDC PAT
* Fixed REST API bugs & dependency problems

## 9.6.0c ##
2021/07/28

* Fixed observable map issues
* Renamed addConsumerJoinedHandler to addConsumerJoinedFirstHandler
* Added search for target operations in Vmd

## 9.5.0c ##
2021/05/06

* Vertx dependency upgrade (4.0.3)
* Fixed AppSequence range (pos. integer)
* Changed client eventing SSL config (consumer event sink now uses http server options instead of http client options)

## 9.4.2c ##
2021/03/23

* Changed message formates for subscription (added mustUnderstand=true)
* Small bugfixes

## 9.4.0c ##
2020/12/13

* Implemented TCP streaming, use SDCProvider.setTcpStreaming(true)
* Removed provider init using existing Mdib

## 9.3.0c ##
2020/11/29

* Fixed wrong timer cancel
* Manually merged pull request for finding context descriptor
* Manually merged pull request for handling missing instance id

## 9.2.0c ##
2020/09/06

* Fixed invocation state check on multiple states
* Implements SDC REST API version 1.2.0
- [SdcApiSpec](https://bitbucket.org/besting-it/sdcapispec) - SDC REST API specification

## 9.0.1c ##
2020/08/30

* Fixed possible swallowed exceptions on consumer invoke

## 9.0.0c ##
2020/05/29

* Implements SDC REST API version 1.0.0
- [SdcApiSpec](https://bitbucket.org/besting-it/sdcapispec) - SDC REST API specification

## 8.2.2c ##
2020/02/07

* Fixed OperationInvokedReport subscription

## 8.2.0c ##
2020/01/20

* Added support for arbitrary eventing reference parameters
* Added SDCProvider.startupAsync
* Added SDCServiceManager.discoverEPRAsync

## 8.1.1c ##
2020/01/09

* Added usage of default MDPWS streaming address

## 8.1.0c ##

* Updated project to Java version 11+ (OpenJDK, Oracle JDK)
* Implemented periodic events
* Removed rits cloning

## 7.0.0c ##
Note: last version compatible with Java 8!

* Changed service structure (biceps service)
* Updated extension point XSD to match original schema
* Updated external library references
* Fixed resolving of multiple port types
* Fixed wrong XML element for safety context
* Added better stack trace logging

## 6.1.0c ##

* Fixed provider shutdown error
* Added metadata version increase

## 6.0.2c ##

* Introduced async API
* Device type change
* New provider init methods

## 5.4.0c ##

* Permanent subscription to operation invoked reports
* Eventing on demand (depends on present event handlers in consumer)
* Fixed non-unique handle in operation state

## 5.3.2c ##

* Renamed context changed report

## 5.3.1c ##

* Moved OperationInvoked to SetService
* Fixed UDP msg ID filter bug
* Added IP address output to UDP log messages

## 5.2.0c ##

* SDCProvider now implements java.lang.AutoCloseable (try-with-resources)
* Removed SDCConsumer close() - managed by framework
* ServiceManager discovery methods refactoring (discoverEPRAsync, getAvailableSDCConsumerByEPR, getAvailableSDCConsumers)
* Experimental support for space delimited DPWS eventing filters