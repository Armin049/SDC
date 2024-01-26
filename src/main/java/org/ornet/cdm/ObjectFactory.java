//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.12.28 um 06:04:43 PM CET 
//


package org.ornet.cdm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.ornet.cdm package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StreamSource_QNAME = new QName("http://standards.ieee.org/downloads/11073/11073-20702-2016", "StreamSource");
    private final static QName _StreamDescriptions_QNAME = new QName("http://standards.ieee.org/downloads/11073/11073-20702-2016", "StreamDescriptions");
    private final static QName _Extension_QNAME = new QName("http://standards.ieee.org/downloads/11073/11073-10207-2017/extension", "Extension");
    private final static QName _SafetyReq_QNAME = new QName("http://standards.ieee.org/downloads/11073/11073-20702-2016", "SafetyReq");
    private final static QName _SafetyInfo_QNAME = new QName("http://standards.ieee.org/downloads/11073/11073-20702-2016", "SafetyInfo");
    private final static QName _SafetyReqAssertion_QNAME = new QName("http://standards.ieee.org/downloads/11073/11073-20702-2016", "SafetyReqAssertion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.ornet.cdm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObservedValueStream }
     * 
     */
    public ObservedValueStream createObservedValueStream() {
        return new ObservedValueStream();
    }

    /**
     * Create an instance of {@link AbstractOperationalStateReport }
     * 
     */
    public AbstractOperationalStateReport createAbstractOperationalStateReport() {
        return new AbstractOperationalStateReport();
    }

    /**
     * Create an instance of {@link SystemErrorReport }
     * 
     */
    public SystemErrorReport createSystemErrorReport() {
        return new SystemErrorReport();
    }

    /**
     * Create an instance of {@link AbstractComponentReport }
     * 
     */
    public AbstractComponentReport createAbstractComponentReport() {
        return new AbstractComponentReport();
    }

    /**
     * Create an instance of {@link DescriptionModificationReport }
     * 
     */
    public DescriptionModificationReport createDescriptionModificationReport() {
        return new DescriptionModificationReport();
    }

    /**
     * Create an instance of {@link AbstractAlertReport }
     * 
     */
    public AbstractAlertReport createAbstractAlertReport() {
        return new AbstractAlertReport();
    }

    /**
     * Create an instance of {@link Activate }
     * 
     */
    public Activate createActivate() {
        return new Activate();
    }

    /**
     * Create an instance of {@link OperationInvokedReport }
     * 
     */
    public OperationInvokedReport createOperationInvokedReport() {
        return new OperationInvokedReport();
    }

    /**
     * Create an instance of {@link AbstractMetricReport }
     * 
     */
    public AbstractMetricReport createAbstractMetricReport() {
        return new AbstractMetricReport();
    }

    /**
     * Create an instance of {@link AbstractContextReport }
     * 
     */
    public AbstractContextReport createAbstractContextReport() {
        return new AbstractContextReport();
    }

    /**
     * Create an instance of {@link ClinicalInfo }
     * 
     */
    public ClinicalInfo createClinicalInfo() {
        return new ClinicalInfo();
    }

    /**
     * Create an instance of {@link ClinicalInfo.RelatedMeasurement }
     * 
     */
    public ClinicalInfo.RelatedMeasurement createClinicalInfoRelatedMeasurement() {
        return new ClinicalInfo.RelatedMeasurement();
    }

    /**
     * Create an instance of {@link AbstractMetricDescriptor }
     * 
     */
    public AbstractMetricDescriptor createAbstractMetricDescriptor() {
        return new AbstractMetricDescriptor();
    }

    /**
     * Create an instance of {@link ScoState }
     * 
     */
    public ScoState createScoState() {
        return new ScoState();
    }

    /**
     * Create an instance of {@link WorkflowContextState }
     * 
     */
    public WorkflowContextState createWorkflowContextState() {
        return new WorkflowContextState();
    }

    /**
     * Create an instance of {@link WorkflowContextState.WorkflowDetail }
     * 
     */
    public WorkflowContextState.WorkflowDetail createWorkflowContextStateWorkflowDetail() {
        return new WorkflowContextState.WorkflowDetail();
    }

    /**
     * Create an instance of {@link AbstractDeviceComponentDescriptor }
     * 
     */
    public AbstractDeviceComponentDescriptor createAbstractDeviceComponentDescriptor() {
        return new AbstractDeviceComponentDescriptor();
    }

    /**
     * Create an instance of {@link MdsDescriptor }
     * 
     */
    public MdsDescriptor createMdsDescriptor() {
        return new MdsDescriptor();
    }

    /**
     * Create an instance of {@link MdsDescriptor.MetaData }
     * 
     */
    public MdsDescriptor.MetaData createMdsDescriptorMetaData() {
        return new MdsDescriptor.MetaData();
    }

    /**
     * Create an instance of {@link SetStringOperationState }
     * 
     */
    public SetStringOperationState createSetStringOperationState() {
        return new SetStringOperationState();
    }

    /**
     * Create an instance of {@link CodedValue }
     * 
     */
    public CodedValue createCodedValue() {
        return new CodedValue();
    }

    /**
     * Create an instance of {@link EnumStringMetricDescriptor }
     * 
     */
    public EnumStringMetricDescriptor createEnumStringMetricDescriptor() {
        return new EnumStringMetricDescriptor();
    }

    /**
     * Create an instance of {@link AbstractMetricValue }
     * 
     */
    public AbstractMetricValue createAbstractMetricValue() {
        return new AbstractMetricValue();
    }

    /**
     * Create an instance of {@link SampleArrayValue }
     * 
     */
    public SampleArrayValue createSampleArrayValue() {
        return new SampleArrayValue();
    }

    /**
     * Create an instance of {@link CalibrationInfo }
     * 
     */
    public CalibrationInfo createCalibrationInfo() {
        return new CalibrationInfo();
    }

    /**
     * Create an instance of {@link CalibrationInfo.CalibrationDocumentation }
     * 
     */
    public CalibrationInfo.CalibrationDocumentation createCalibrationInfoCalibrationDocumentation() {
        return new CalibrationInfo.CalibrationDocumentation();
    }

    /**
     * Create an instance of {@link ActivateOperationDescriptor }
     * 
     */
    public ActivateOperationDescriptor createActivateOperationDescriptor() {
        return new ActivateOperationDescriptor();
    }

    /**
     * Create an instance of {@link StreamDescriptionsType }
     * 
     */
    public StreamDescriptionsType createStreamDescriptionsType() {
        return new StreamDescriptionsType();
    }

    /**
     * Create an instance of {@link StreamSourceType }
     * 
     */
    public StreamSourceType createStreamSourceType() {
        return new StreamSourceType();
    }

    /**
     * Create an instance of {@link SafetyInfoType }
     * 
     */
    public SafetyInfoType createSafetyInfoType() {
        return new SafetyInfoType();
    }

    /**
     * Create an instance of {@link SafetyReqAssertionType }
     * 
     */
    public SafetyReqAssertionType createSafetyReqAssertionType() {
        return new SafetyReqAssertionType();
    }

    /**
     * Create an instance of {@link SafetyReqType }
     * 
     */
    public SafetyReqType createSafetyReqType() {
        return new SafetyReqType();
    }

    /**
     * Create an instance of {@link StreamTransmissionType }
     * 
     */
    public StreamTransmissionType createStreamTransmissionType() {
        return new StreamTransmissionType();
    }

    /**
     * Create an instance of {@link SafetyContextDefType }
     * 
     */
    public SafetyContextDefType createSafetyContextDefType() {
        return new SafetyContextDefType();
    }

    /**
     * Create an instance of {@link StreamTypeType }
     * 
     */
    public StreamTypeType createStreamTypeType() {
        return new StreamTypeType();
    }

    /**
     * Create an instance of {@link CtxtValueType }
     * 
     */
    public CtxtValueType createCtxtValueType() {
        return new CtxtValueType();
    }

    /**
     * Create an instance of {@link SafetyContextType }
     * 
     */
    public SafetyContextType createSafetyContextType() {
        return new SafetyContextType();
    }

    /**
     * Create an instance of {@link SelectorType }
     * 
     */
    public SelectorType createSelectorType() {
        return new SelectorType();
    }

    /**
     * Create an instance of {@link DcValueType }
     * 
     */
    public DcValueType createDcValueType() {
        return new DcValueType();
    }

    /**
     * Create an instance of {@link DualChannelType }
     * 
     */
    public DualChannelType createDualChannelType() {
        return new DualChannelType();
    }

    /**
     * Create an instance of {@link DualChannelDefType }
     * 
     */
    public DualChannelDefType createDualChannelDefType() {
        return new DualChannelDefType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     * 
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link StringMetricState }
     * 
     */
    public StringMetricState createStringMetricState() {
        return new StringMetricState();
    }

    /**
     * Create an instance of {@link PatientContextDescriptor }
     * 
     */
    public PatientContextDescriptor createPatientContextDescriptor() {
        return new PatientContextDescriptor();
    }

    /**
     * Create an instance of {@link SetMetricStateOperationDescriptor }
     * 
     */
    public SetMetricStateOperationDescriptor createSetMetricStateOperationDescriptor() {
        return new SetMetricStateOperationDescriptor();
    }

    /**
     * Create an instance of {@link LocalizedText }
     * 
     */
    public LocalizedText createLocalizedText() {
        return new LocalizedText();
    }

    /**
     * Create an instance of {@link AlertConditionState }
     * 
     */
    public AlertConditionState createAlertConditionState() {
        return new AlertConditionState();
    }

    /**
     * Create an instance of {@link InstanceIdentifier }
     * 
     */
    public InstanceIdentifier createInstanceIdentifier() {
        return new InstanceIdentifier();
    }

    /**
     * Create an instance of {@link SystemContextDescriptor }
     * 
     */
    public SystemContextDescriptor createSystemContextDescriptor() {
        return new SystemContextDescriptor();
    }

    /**
     * Create an instance of {@link WorkflowContextDescriptor }
     * 
     */
    public WorkflowContextDescriptor createWorkflowContextDescriptor() {
        return new WorkflowContextDescriptor();
    }

    /**
     * Create an instance of {@link AlertConditionDescriptor }
     * 
     */
    public AlertConditionDescriptor createAlertConditionDescriptor() {
        return new AlertConditionDescriptor();
    }

    /**
     * Create an instance of {@link MeansContextDescriptor }
     * 
     */
    public MeansContextDescriptor createMeansContextDescriptor() {
        return new MeansContextDescriptor();
    }

    /**
     * Create an instance of {@link PhysicalConnectorInfo }
     * 
     */
    public PhysicalConnectorInfo createPhysicalConnectorInfo() {
        return new PhysicalConnectorInfo();
    }

    /**
     * Create an instance of {@link ChannelState }
     * 
     */
    public ChannelState createChannelState() {
        return new ChannelState();
    }

    /**
     * Create an instance of {@link SetMetricStateOperationState }
     * 
     */
    public SetMetricStateOperationState createSetMetricStateOperationState() {
        return new SetMetricStateOperationState();
    }

    /**
     * Create an instance of {@link NumericMetricDescriptor }
     * 
     */
    public NumericMetricDescriptor createNumericMetricDescriptor() {
        return new NumericMetricDescriptor();
    }

    /**
     * Create an instance of {@link BatteryDescriptor }
     * 
     */
    public BatteryDescriptor createBatteryDescriptor() {
        return new BatteryDescriptor();
    }

    /**
     * Create an instance of {@link LocationReference }
     * 
     */
    public LocationReference createLocationReference() {
        return new LocationReference();
    }

    /**
     * Create an instance of {@link AbstractContextDescriptor }
     * 
     */
    public AbstractContextDescriptor createAbstractContextDescriptor() {
        return new AbstractContextDescriptor();
    }

    /**
     * Create an instance of {@link AbstractAlertState }
     * 
     */
    public AbstractAlertState createAbstractAlertState() {
        return new AbstractAlertState();
    }

    /**
     * Create an instance of {@link OrderDetail }
     * 
     */
    public OrderDetail createOrderDetail() {
        return new OrderDetail();
    }

    /**
     * Create an instance of {@link SetComponentStateOperationState }
     * 
     */
    public SetComponentStateOperationState createSetComponentStateOperationState() {
        return new SetComponentStateOperationState();
    }

    /**
     * Create an instance of {@link Mdib }
     * 
     */
    public Mdib createMdib() {
        return new Mdib();
    }

    /**
     * Create an instance of {@link OperatorContextDescriptor }
     * 
     */
    public OperatorContextDescriptor createOperatorContextDescriptor() {
        return new OperatorContextDescriptor();
    }

    /**
     * Create an instance of {@link AbstractOperationDescriptor }
     * 
     */
    public AbstractOperationDescriptor createAbstractOperationDescriptor() {
        return new AbstractOperationDescriptor();
    }

    /**
     * Create an instance of {@link VmdState }
     * 
     */
    public VmdState createVmdState() {
        return new VmdState();
    }

    /**
     * Create an instance of {@link AlertSignalState }
     * 
     */
    public AlertSignalState createAlertSignalState() {
        return new AlertSignalState();
    }

    /**
     * Create an instance of {@link LocationDetail }
     * 
     */
    public LocationDetail createLocationDetail() {
        return new LocationDetail();
    }

    /**
     * Create an instance of {@link NumericMetricValue }
     * 
     */
    public NumericMetricValue createNumericMetricValue() {
        return new NumericMetricValue();
    }

    /**
     * Create an instance of {@link DistributionSampleArrayMetricDescriptor }
     * 
     */
    public DistributionSampleArrayMetricDescriptor createDistributionSampleArrayMetricDescriptor() {
        return new DistributionSampleArrayMetricDescriptor();
    }

    /**
     * Create an instance of {@link SetValueOperationState }
     * 
     */
    public SetValueOperationState createSetValueOperationState() {
        return new SetValueOperationState();
    }

    /**
     * Create an instance of {@link ScoDescriptor }
     * 
     */
    public ScoDescriptor createScoDescriptor() {
        return new ScoDescriptor();
    }

    /**
     * Create an instance of {@link StringMetricValue }
     * 
     */
    public StringMetricValue createStringMetricValue() {
        return new StringMetricValue();
    }

    /**
     * Create an instance of {@link AbstractContextState }
     * 
     */
    public AbstractContextState createAbstractContextState() {
        return new AbstractContextState();
    }

    /**
     * Create an instance of {@link ContainmentTreeEntry }
     * 
     */
    public ContainmentTreeEntry createContainmentTreeEntry() {
        return new ContainmentTreeEntry();
    }

    /**
     * Create an instance of {@link LocationContextDescriptor }
     * 
     */
    public LocationContextDescriptor createLocationContextDescriptor() {
        return new LocationContextDescriptor();
    }

    /**
     * Create an instance of {@link LimitAlertConditionState }
     * 
     */
    public LimitAlertConditionState createLimitAlertConditionState() {
        return new LimitAlertConditionState();
    }

    /**
     * Create an instance of {@link AbstractComplexDeviceComponentDescriptor }
     * 
     */
    public AbstractComplexDeviceComponentDescriptor createAbstractComplexDeviceComponentDescriptor() {
        return new AbstractComplexDeviceComponentDescriptor();
    }

    /**
     * Create an instance of {@link ContainmentTree }
     * 
     */
    public ContainmentTree createContainmentTree() {
        return new ContainmentTree();
    }

    /**
     * Create an instance of {@link EnumStringMetricState }
     * 
     */
    public EnumStringMetricState createEnumStringMetricState() {
        return new EnumStringMetricState();
    }

    /**
     * Create an instance of {@link LocationContextState }
     * 
     */
    public LocationContextState createLocationContextState() {
        return new LocationContextState();
    }

    /**
     * Create an instance of {@link SetContextStateOperationState }
     * 
     */
    public SetContextStateOperationState createSetContextStateOperationState() {
        return new SetContextStateOperationState();
    }

    /**
     * Create an instance of {@link RemedyInfo }
     * 
     */
    public RemedyInfo createRemedyInfo() {
        return new RemedyInfo();
    }

    /**
     * Create an instance of {@link ClockState }
     * 
     */
    public ClockState createClockState() {
        return new ClockState();
    }

    /**
     * Create an instance of {@link AbstractSetStateOperationDescriptor }
     * 
     */
    public AbstractSetStateOperationDescriptor createAbstractSetStateOperationDescriptor() {
        return new AbstractSetStateOperationDescriptor();
    }

    /**
     * Create an instance of {@link AbstractDeviceComponentState }
     * 
     */
    public AbstractDeviceComponentState createAbstractDeviceComponentState() {
        return new AbstractDeviceComponentState();
    }

    /**
     * Create an instance of {@link PersonReference }
     * 
     */
    public PersonReference createPersonReference() {
        return new PersonReference();
    }

    /**
     * Create an instance of {@link OperatingJurisdiction }
     * 
     */
    public OperatingJurisdiction createOperatingJurisdiction() {
        return new OperatingJurisdiction();
    }

    /**
     * Create an instance of {@link AbstractMetricState }
     * 
     */
    public AbstractMetricState createAbstractMetricState() {
        return new AbstractMetricState();
    }

    /**
     * Create an instance of {@link VmdDescriptor }
     * 
     */
    public VmdDescriptor createVmdDescriptor() {
        return new VmdDescriptor();
    }

    /**
     * Create an instance of {@link AbstractOperationState }
     * 
     */
    public AbstractOperationState createAbstractOperationState() {
        return new AbstractOperationState();
    }

    /**
     * Create an instance of {@link AlertSignalDescriptor }
     * 
     */
    public AlertSignalDescriptor createAlertSignalDescriptor() {
        return new AlertSignalDescriptor();
    }

    /**
     * Create an instance of {@link ChannelDescriptor }
     * 
     */
    public ChannelDescriptor createChannelDescriptor() {
        return new ChannelDescriptor();
    }

    /**
     * Create an instance of {@link DistributionSampleArrayMetricState }
     * 
     */
    public DistributionSampleArrayMetricState createDistributionSampleArrayMetricState() {
        return new DistributionSampleArrayMetricState();
    }

    /**
     * Create an instance of {@link BaseDemographics }
     * 
     */
    public BaseDemographics createBaseDemographics() {
        return new BaseDemographics();
    }

    /**
     * Create an instance of {@link ApprovedJurisdictions }
     * 
     */
    public ApprovedJurisdictions createApprovedJurisdictions() {
        return new ApprovedJurisdictions();
    }

    /**
     * Create an instance of {@link ActivateOperationState }
     * 
     */
    public ActivateOperationState createActivateOperationState() {
        return new ActivateOperationState();
    }

    /**
     * Create an instance of {@link EnsembleContextState }
     * 
     */
    public EnsembleContextState createEnsembleContextState() {
        return new EnsembleContextState();
    }

    /**
     * Create an instance of {@link SystemContextState }
     * 
     */
    public SystemContextState createSystemContextState() {
        return new SystemContextState();
    }

    /**
     * Create an instance of {@link AbstractComplexDeviceComponentState }
     * 
     */
    public AbstractComplexDeviceComponentState createAbstractComplexDeviceComponentState() {
        return new AbstractComplexDeviceComponentState();
    }

    /**
     * Create an instance of {@link OperatorContextState }
     * 
     */
    public OperatorContextState createOperatorContextState() {
        return new OperatorContextState();
    }

    /**
     * Create an instance of {@link PatientDemographicsCoreData }
     * 
     */
    public PatientDemographicsCoreData createPatientDemographicsCoreData() {
        return new PatientDemographicsCoreData();
    }

    /**
     * Create an instance of {@link AlertSystemDescriptor }
     * 
     */
    public AlertSystemDescriptor createAlertSystemDescriptor() {
        return new AlertSystemDescriptor();
    }

    /**
     * Create an instance of {@link MdDescription }
     * 
     */
    public MdDescription createMdDescription() {
        return new MdDescription();
    }

    /**
     * Create an instance of {@link AbstractDescriptor }
     * 
     */
    public AbstractDescriptor createAbstractDescriptor() {
        return new AbstractDescriptor();
    }

    /**
     * Create an instance of {@link Range }
     * 
     */
    public Range createRange() {
        return new Range();
    }

    /**
     * Create an instance of {@link NumericMetricState }
     * 
     */
    public NumericMetricState createNumericMetricState() {
        return new NumericMetricState();
    }

    /**
     * Create an instance of {@link ClockDescriptor }
     * 
     */
    public ClockDescriptor createClockDescriptor() {
        return new ClockDescriptor();
    }

    /**
     * Create an instance of {@link MdState }
     * 
     */
    public MdState createMdState() {
        return new MdState();
    }

    /**
     * Create an instance of {@link AbstractState }
     * 
     */
    public AbstractState createAbstractState() {
        return new AbstractState();
    }

    /**
     * Create an instance of {@link LimitAlertConditionDescriptor }
     * 
     */
    public LimitAlertConditionDescriptor createLimitAlertConditionDescriptor() {
        return new LimitAlertConditionDescriptor();
    }

    /**
     * Create an instance of {@link Measurement }
     * 
     */
    public Measurement createMeasurement() {
        return new Measurement();
    }

    /**
     * Create an instance of {@link SetAlertStateOperationState }
     * 
     */
    public SetAlertStateOperationState createSetAlertStateOperationState() {
        return new SetAlertStateOperationState();
    }

    /**
     * Create an instance of {@link RealTimeSampleArrayMetricState }
     * 
     */
    public RealTimeSampleArrayMetricState createRealTimeSampleArrayMetricState() {
        return new RealTimeSampleArrayMetricState();
    }

    /**
     * Create an instance of {@link PersonParticipation }
     * 
     */
    public PersonParticipation createPersonParticipation() {
        return new PersonParticipation();
    }

    /**
     * Create an instance of {@link SystemSignalActivation }
     * 
     */
    public SystemSignalActivation createSystemSignalActivation() {
        return new SystemSignalActivation();
    }

    /**
     * Create an instance of {@link SetValueOperationDescriptor }
     * 
     */
    public SetValueOperationDescriptor createSetValueOperationDescriptor() {
        return new SetValueOperationDescriptor();
    }

    /**
     * Create an instance of {@link MeansContextState }
     * 
     */
    public MeansContextState createMeansContextState() {
        return new MeansContextState();
    }

    /**
     * Create an instance of {@link SetAlertStateOperationDescriptor }
     * 
     */
    public SetAlertStateOperationDescriptor createSetAlertStateOperationDescriptor() {
        return new SetAlertStateOperationDescriptor();
    }

    /**
     * Create an instance of {@link SetComponentStateOperationDescriptor }
     * 
     */
    public SetComponentStateOperationDescriptor createSetComponentStateOperationDescriptor() {
        return new SetComponentStateOperationDescriptor();
    }

    /**
     * Create an instance of {@link EnsembleContextDescriptor }
     * 
     */
    public EnsembleContextDescriptor createEnsembleContextDescriptor() {
        return new EnsembleContextDescriptor();
    }

    /**
     * Create an instance of {@link AbstractMultiState }
     * 
     */
    public AbstractMultiState createAbstractMultiState() {
        return new AbstractMultiState();
    }

    /**
     * Create an instance of {@link SetStringOperationDescriptor }
     * 
     */
    public SetStringOperationDescriptor createSetStringOperationDescriptor() {
        return new SetStringOperationDescriptor();
    }

    /**
     * Create an instance of {@link StringMetricDescriptor }
     * 
     */
    public StringMetricDescriptor createStringMetricDescriptor() {
        return new StringMetricDescriptor();
    }

    /**
     * Create an instance of {@link PatientContextState }
     * 
     */
    public PatientContextState createPatientContextState() {
        return new PatientContextState();
    }

    /**
     * Create an instance of {@link ImagingProcedure }
     * 
     */
    public ImagingProcedure createImagingProcedure() {
        return new ImagingProcedure();
    }

    /**
     * Create an instance of {@link AlertSystemState }
     * 
     */
    public AlertSystemState createAlertSystemState() {
        return new AlertSystemState();
    }

    /**
     * Create an instance of {@link CauseInfo }
     * 
     */
    public CauseInfo createCauseInfo() {
        return new CauseInfo();
    }

    /**
     * Create an instance of {@link NeonatalPatientDemographicsCoreData }
     * 
     */
    public NeonatalPatientDemographicsCoreData createNeonatalPatientDemographicsCoreData() {
        return new NeonatalPatientDemographicsCoreData();
    }

    /**
     * Create an instance of {@link RealTimeSampleArrayMetricDescriptor }
     * 
     */
    public RealTimeSampleArrayMetricDescriptor createRealTimeSampleArrayMetricDescriptor() {
        return new RealTimeSampleArrayMetricDescriptor();
    }

    /**
     * Create an instance of {@link AbstractAlertDescriptor }
     * 
     */
    public AbstractAlertDescriptor createAbstractAlertDescriptor() {
        return new AbstractAlertDescriptor();
    }

    /**
     * Create an instance of {@link BatteryState }
     * 
     */
    public BatteryState createBatteryState() {
        return new BatteryState();
    }

    /**
     * Create an instance of {@link MdsState }
     * 
     */
    public MdsState createMdsState() {
        return new MdsState();
    }

    /**
     * Create an instance of {@link SetContextStateOperationDescriptor }
     * 
     */
    public SetContextStateOperationDescriptor createSetContextStateOperationDescriptor() {
        return new SetContextStateOperationDescriptor();
    }

    /**
     * Create an instance of {@link GetDescriptor }
     * 
     */
    public GetDescriptor createGetDescriptor() {
        return new GetDescriptor();
    }

    /**
     * Create an instance of {@link AbstractGet }
     * 
     */
    public AbstractGet createAbstractGet() {
        return new AbstractGet();
    }

    /**
     * Create an instance of {@link SetMetricStateResponse }
     * 
     */
    public SetMetricStateResponse createSetMetricStateResponse() {
        return new SetMetricStateResponse();
    }

    /**
     * Create an instance of {@link AbstractSetResponse }
     * 
     */
    public AbstractSetResponse createAbstractSetResponse() {
        return new AbstractSetResponse();
    }

    /**
     * Create an instance of {@link InvocationInfo }
     * 
     */
    public InvocationInfo createInvocationInfo() {
        return new InvocationInfo();
    }

    /**
     * Create an instance of {@link GetDescriptorsFromArchive }
     * 
     */
    public GetDescriptorsFromArchive createGetDescriptorsFromArchive() {
        return new GetDescriptorsFromArchive();
    }

    /**
     * Create an instance of {@link VersionFrame }
     * 
     */
    public VersionFrame createVersionFrame() {
        return new VersionFrame();
    }

    /**
     * Create an instance of {@link TimeFrame }
     * 
     */
    public TimeFrame createTimeFrame() {
        return new TimeFrame();
    }

    /**
     * Create an instance of {@link AbstractReport }
     * 
     */
    public AbstractReport createAbstractReport() {
        return new AbstractReport();
    }

    /**
     * Create an instance of {@link ObservedValueStream.Value }
     * 
     */
    public ObservedValueStream.Value createObservedValueStreamValue() {
        return new ObservedValueStream.Value();
    }

    /**
     * Create an instance of {@link GetLocalizedTextResponse }
     * 
     */
    public GetLocalizedTextResponse createGetLocalizedTextResponse() {
        return new GetLocalizedTextResponse();
    }

    /**
     * Create an instance of {@link AbstractGetResponse }
     * 
     */
    public AbstractGetResponse createAbstractGetResponse() {
        return new AbstractGetResponse();
    }

    /**
     * Create an instance of {@link EpisodicOperationalStateReport }
     * 
     */
    public EpisodicOperationalStateReport createEpisodicOperationalStateReport() {
        return new EpisodicOperationalStateReport();
    }

    /**
     * Create an instance of {@link AbstractOperationalStateReport.ReportPart }
     * 
     */
    public AbstractOperationalStateReport.ReportPart createAbstractOperationalStateReportReportPart() {
        return new AbstractOperationalStateReport.ReportPart();
    }

    /**
     * Create an instance of {@link GetDescriptorsFromArchiveResponse }
     * 
     */
    public GetDescriptorsFromArchiveResponse createGetDescriptorsFromArchiveResponse() {
        return new GetDescriptorsFromArchiveResponse();
    }

    /**
     * Create an instance of {@link SetContextStateResponse }
     * 
     */
    public SetContextStateResponse createSetContextStateResponse() {
        return new SetContextStateResponse();
    }

    /**
     * Create an instance of {@link GetContextStates }
     * 
     */
    public GetContextStates createGetContextStates() {
        return new GetContextStates();
    }

    /**
     * Create an instance of {@link Retrievability }
     * 
     */
    public Retrievability createRetrievability() {
        return new Retrievability();
    }

    /**
     * Create an instance of {@link RetrievabilityInfo }
     * 
     */
    public RetrievabilityInfo createRetrievabilityInfo() {
        return new RetrievabilityInfo();
    }

    /**
     * Create an instance of {@link SystemErrorReport.ReportPart }
     * 
     */
    public SystemErrorReport.ReportPart createSystemErrorReportReportPart() {
        return new SystemErrorReport.ReportPart();
    }

    /**
     * Create an instance of {@link GetLocalizedText }
     * 
     */
    public GetLocalizedText createGetLocalizedText() {
        return new GetLocalizedText();
    }

    /**
     * Create an instance of {@link PeriodicComponentReport }
     * 
     */
    public PeriodicComponentReport createPeriodicComponentReport() {
        return new PeriodicComponentReport();
    }

    /**
     * Create an instance of {@link AbstractComponentReport.ReportPart }
     * 
     */
    public AbstractComponentReport.ReportPart createAbstractComponentReportReportPart() {
        return new AbstractComponentReport.ReportPart();
    }

    /**
     * Create an instance of {@link SetComponentState }
     * 
     */
    public SetComponentState createSetComponentState() {
        return new SetComponentState();
    }

    /**
     * Create an instance of {@link AbstractSet }
     * 
     */
    public AbstractSet createAbstractSet() {
        return new AbstractSet();
    }

    /**
     * Create an instance of {@link DescriptionModificationReport.ReportPart }
     * 
     */
    public DescriptionModificationReport.ReportPart createDescriptionModificationReportReportPart() {
        return new DescriptionModificationReport.ReportPart();
    }

    /**
     * Create an instance of {@link WaveformStream }
     * 
     */
    public WaveformStream createWaveformStream() {
        return new WaveformStream();
    }

    /**
     * Create an instance of {@link PeriodicAlertReport }
     * 
     */
    public PeriodicAlertReport createPeriodicAlertReport() {
        return new PeriodicAlertReport();
    }

    /**
     * Create an instance of {@link AbstractAlertReport.ReportPart }
     * 
     */
    public AbstractAlertReport.ReportPart createAbstractAlertReportReportPart() {
        return new AbstractAlertReport.ReportPart();
    }

    /**
     * Create an instance of {@link PeriodicOperationalStateReport }
     * 
     */
    public PeriodicOperationalStateReport createPeriodicOperationalStateReport() {
        return new PeriodicOperationalStateReport();
    }

    /**
     * Create an instance of {@link GetMdStateResponse }
     * 
     */
    public GetMdStateResponse createGetMdStateResponse() {
        return new GetMdStateResponse();
    }

    /**
     * Create an instance of {@link GetStatesFromArchive }
     * 
     */
    public GetStatesFromArchive createGetStatesFromArchive() {
        return new GetStatesFromArchive();
    }

    /**
     * Create an instance of {@link SetStringResponse }
     * 
     */
    public SetStringResponse createSetStringResponse() {
        return new SetStringResponse();
    }

    /**
     * Create an instance of {@link GetContextStatesResponse }
     * 
     */
    public GetContextStatesResponse createGetContextStatesResponse() {
        return new GetContextStatesResponse();
    }

    /**
     * Create an instance of {@link GetStatesFromArchiveResponse }
     * 
     */
    public GetStatesFromArchiveResponse createGetStatesFromArchiveResponse() {
        return new GetStatesFromArchiveResponse();
    }

    /**
     * Create an instance of {@link Activate.Argument }
     * 
     */
    public Activate.Argument createActivateArgument() {
        return new Activate.Argument();
    }

    /**
     * Create an instance of {@link GetMdState }
     * 
     */
    public GetMdState createGetMdState() {
        return new GetMdState();
    }

    /**
     * Create an instance of {@link SetAlertState }
     * 
     */
    public SetAlertState createSetAlertState() {
        return new SetAlertState();
    }

    /**
     * Create an instance of {@link SetContextState }
     * 
     */
    public SetContextState createSetContextState() {
        return new SetContextState();
    }

    /**
     * Create an instance of {@link GetMdDescription }
     * 
     */
    public GetMdDescription createGetMdDescription() {
        return new GetMdDescription();
    }

    /**
     * Create an instance of {@link GetContextStatesByFilterResponse }
     * 
     */
    public GetContextStatesByFilterResponse createGetContextStatesByFilterResponse() {
        return new GetContextStatesByFilterResponse();
    }

    /**
     * Create an instance of {@link GetContainmentTree }
     * 
     */
    public GetContainmentTree createGetContainmentTree() {
        return new GetContainmentTree();
    }

    /**
     * Create an instance of {@link GetMdibResponse }
     * 
     */
    public GetMdibResponse createGetMdibResponse() {
        return new GetMdibResponse();
    }

    /**
     * Create an instance of {@link SetComponentStateResponse }
     * 
     */
    public SetComponentStateResponse createSetComponentStateResponse() {
        return new SetComponentStateResponse();
    }

    /**
     * Create an instance of {@link GetMdDescriptionResponse }
     * 
     */
    public GetMdDescriptionResponse createGetMdDescriptionResponse() {
        return new GetMdDescriptionResponse();
    }

    /**
     * Create an instance of {@link OperationInvokedReport.ReportPart }
     * 
     */
    public OperationInvokedReport.ReportPart createOperationInvokedReportReportPart() {
        return new OperationInvokedReport.ReportPart();
    }

    /**
     * Create an instance of {@link SetAlertStateResponse }
     * 
     */
    public SetAlertStateResponse createSetAlertStateResponse() {
        return new SetAlertStateResponse();
    }

    /**
     * Create an instance of {@link GetContainmentTreeResponse }
     * 
     */
    public GetContainmentTreeResponse createGetContainmentTreeResponse() {
        return new GetContainmentTreeResponse();
    }

    /**
     * Create an instance of {@link EpisodicAlertReport }
     * 
     */
    public EpisodicAlertReport createEpisodicAlertReport() {
        return new EpisodicAlertReport();
    }

    /**
     * Create an instance of {@link GetContextStatesByIdentificationResponse }
     * 
     */
    public GetContextStatesByIdentificationResponse createGetContextStatesByIdentificationResponse() {
        return new GetContextStatesByIdentificationResponse();
    }

    /**
     * Create an instance of {@link SetMetricState }
     * 
     */
    public SetMetricState createSetMetricState() {
        return new SetMetricState();
    }

    /**
     * Create an instance of {@link EpisodicComponentReport }
     * 
     */
    public EpisodicComponentReport createEpisodicComponentReport() {
        return new EpisodicComponentReport();
    }

    /**
     * Create an instance of {@link PeriodicMetricReport }
     * 
     */
    public PeriodicMetricReport createPeriodicMetricReport() {
        return new PeriodicMetricReport();
    }

    /**
     * Create an instance of {@link AbstractMetricReport.ReportPart }
     * 
     */
    public AbstractMetricReport.ReportPart createAbstractMetricReportReportPart() {
        return new AbstractMetricReport.ReportPart();
    }

    /**
     * Create an instance of {@link GetSupportedLanguagesResponse }
     * 
     */
    public GetSupportedLanguagesResponse createGetSupportedLanguagesResponse() {
        return new GetSupportedLanguagesResponse();
    }

    /**
     * Create an instance of {@link GetContextStatesByFilter }
     * 
     */
    public GetContextStatesByFilter createGetContextStatesByFilter() {
        return new GetContextStatesByFilter();
    }

    /**
     * Create an instance of {@link SetString }
     * 
     */
    public SetString createSetString() {
        return new SetString();
    }

    /**
     * Create an instance of {@link PeriodicContextReport }
     * 
     */
    public PeriodicContextReport createPeriodicContextReport() {
        return new PeriodicContextReport();
    }

    /**
     * Create an instance of {@link AbstractContextReport.ReportPart }
     * 
     */
    public AbstractContextReport.ReportPart createAbstractContextReportReportPart() {
        return new AbstractContextReport.ReportPart();
    }

    /**
     * Create an instance of {@link EpisodicMetricReport }
     * 
     */
    public EpisodicMetricReport createEpisodicMetricReport() {
        return new EpisodicMetricReport();
    }

    /**
     * Create an instance of {@link GetSupportedLanguages }
     * 
     */
    public GetSupportedLanguages createGetSupportedLanguages() {
        return new GetSupportedLanguages();
    }

    /**
     * Create an instance of {@link GetContextStatesByIdentification }
     * 
     */
    public GetContextStatesByIdentification createGetContextStatesByIdentification() {
        return new GetContextStatesByIdentification();
    }

    /**
     * Create an instance of {@link ActivateResponse }
     * 
     */
    public ActivateResponse createActivateResponse() {
        return new ActivateResponse();
    }

    /**
     * Create an instance of {@link EpisodicContextReport }
     * 
     */
    public EpisodicContextReport createEpisodicContextReport() {
        return new EpisodicContextReport();
    }

    /**
     * Create an instance of {@link GetDescriptorResponse }
     * 
     */
    public GetDescriptorResponse createGetDescriptorResponse() {
        return new GetDescriptorResponse();
    }

    /**
     * Create an instance of {@link SetValueResponse }
     * 
     */
    public SetValueResponse createSetValueResponse() {
        return new SetValueResponse();
    }

    /**
     * Create an instance of {@link GetMdib }
     * 
     */
    public GetMdib createGetMdib() {
        return new GetMdib();
    }

    /**
     * Create an instance of {@link SetValue }
     * 
     */
    public SetValue createSetValue() {
        return new SetValue();
    }

    /**
     * Create an instance of {@link AbstractReportPart }
     * 
     */
    public AbstractReportPart createAbstractReportPart() {
        return new AbstractReportPart();
    }

    /**
     * Create an instance of {@link ClinicalInfo.RelatedMeasurement.ReferenceRange }
     * 
     */
    public ClinicalInfo.RelatedMeasurement.ReferenceRange createClinicalInfoRelatedMeasurementReferenceRange() {
        return new ClinicalInfo.RelatedMeasurement.ReferenceRange();
    }

    /**
     * Create an instance of {@link AbstractMetricDescriptor.Relation }
     * 
     */
    public AbstractMetricDescriptor.Relation createAbstractMetricDescriptorRelation() {
        return new AbstractMetricDescriptor.Relation();
    }

    /**
     * Create an instance of {@link ScoState.OperationGroup }
     * 
     */
    public ScoState.OperationGroup createScoStateOperationGroup() {
        return new ScoState.OperationGroup();
    }

    /**
     * Create an instance of {@link WorkflowContextState.WorkflowDetail.RequestedOrderDetail }
     * 
     */
    public WorkflowContextState.WorkflowDetail.RequestedOrderDetail createWorkflowContextStateWorkflowDetailRequestedOrderDetail() {
        return new WorkflowContextState.WorkflowDetail.RequestedOrderDetail();
    }

    /**
     * Create an instance of {@link WorkflowContextState.WorkflowDetail.PerformedOrderDetail }
     * 
     */
    public WorkflowContextState.WorkflowDetail.PerformedOrderDetail createWorkflowContextStateWorkflowDetailPerformedOrderDetail() {
        return new WorkflowContextState.WorkflowDetail.PerformedOrderDetail();
    }

    /**
     * Create an instance of {@link AbstractDeviceComponentDescriptor.ProductionSpecification }
     * 
     */
    public AbstractDeviceComponentDescriptor.ProductionSpecification createAbstractDeviceComponentDescriptorProductionSpecification() {
        return new AbstractDeviceComponentDescriptor.ProductionSpecification();
    }

    /**
     * Create an instance of {@link MdsDescriptor.MetaData.Udi }
     * 
     */
    public MdsDescriptor.MetaData.Udi createMdsDescriptorMetaDataUdi() {
        return new MdsDescriptor.MetaData.Udi();
    }

    /**
     * Create an instance of {@link SetStringOperationState.AllowedValues }
     * 
     */
    public SetStringOperationState.AllowedValues createSetStringOperationStateAllowedValues() {
        return new SetStringOperationState.AllowedValues();
    }

    /**
     * Create an instance of {@link CodedValue.Translation }
     * 
     */
    public CodedValue.Translation createCodedValueTranslation() {
        return new CodedValue.Translation();
    }

    /**
     * Create an instance of {@link EnumStringMetricDescriptor.AllowedValue }
     * 
     */
    public EnumStringMetricDescriptor.AllowedValue createEnumStringMetricDescriptorAllowedValue() {
        return new EnumStringMetricDescriptor.AllowedValue();
    }

    /**
     * Create an instance of {@link AbstractMetricValue.MetricQuality }
     * 
     */
    public AbstractMetricValue.MetricQuality createAbstractMetricValueMetricQuality() {
        return new AbstractMetricValue.MetricQuality();
    }

    /**
     * Create an instance of {@link AbstractMetricValue.Annotation }
     * 
     */
    public AbstractMetricValue.Annotation createAbstractMetricValueAnnotation() {
        return new AbstractMetricValue.Annotation();
    }

    /**
     * Create an instance of {@link SampleArrayValue.ApplyAnnotation }
     * 
     */
    public SampleArrayValue.ApplyAnnotation createSampleArrayValueApplyAnnotation() {
        return new SampleArrayValue.ApplyAnnotation();
    }

    /**
     * Create an instance of {@link CalibrationInfo.CalibrationDocumentation.CalibrationResult }
     * 
     */
    public CalibrationInfo.CalibrationDocumentation.CalibrationResult createCalibrationInfoCalibrationDocumentationCalibrationResult() {
        return new CalibrationInfo.CalibrationDocumentation.CalibrationResult();
    }

    /**
     * Create an instance of {@link ActivateOperationDescriptor.Argument }
     * 
     */
    public ActivateOperationDescriptor.Argument createActivateOperationDescriptorArgument() {
        return new ActivateOperationDescriptor.Argument();
    }

    /**
     * Create an instance of {@link StreamDescriptionsType.Types }
     * 
     */
    public StreamDescriptionsType.Types createStreamDescriptionsTypeTypes() {
        return new StreamDescriptionsType.Types();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StreamSourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", name = "StreamSource")
    public JAXBElement<StreamSourceType> createStreamSource(StreamSourceType value) {
        return new JAXBElement<StreamSourceType>(_StreamSource_QNAME, StreamSourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StreamDescriptionsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", name = "StreamDescriptions")
    public JAXBElement<StreamDescriptionsType> createStreamDescriptions(StreamDescriptionsType value) {
        return new JAXBElement<StreamDescriptionsType>(_StreamDescriptions_QNAME, StreamDescriptionsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtensionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://standards.ieee.org/downloads/11073/11073-10207-2017/extension", name = "Extension")
    public JAXBElement<ExtensionType> createExtension(ExtensionType value) {
        return new JAXBElement<ExtensionType>(_Extension_QNAME, ExtensionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SafetyReqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", name = "SafetyReq")
    public JAXBElement<SafetyReqType> createSafetyReq(SafetyReqType value) {
        return new JAXBElement<SafetyReqType>(_SafetyReq_QNAME, SafetyReqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SafetyInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", name = "SafetyInfo")
    public JAXBElement<SafetyInfoType> createSafetyInfo(SafetyInfoType value) {
        return new JAXBElement<SafetyInfoType>(_SafetyInfo_QNAME, SafetyInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SafetyReqAssertionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://standards.ieee.org/downloads/11073/11073-20702-2016", name = "SafetyReqAssertion")
    public JAXBElement<SafetyReqAssertionType> createSafetyReqAssertion(SafetyReqAssertionType value) {
        return new JAXBElement<SafetyReqAssertionType>(_SafetyReqAssertion_QNAME, SafetyReqAssertionType.class, null, value);
    }

}
