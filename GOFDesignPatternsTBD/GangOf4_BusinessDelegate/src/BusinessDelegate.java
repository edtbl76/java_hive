@SuppressWarnings("FieldCanBeLocal")
class BusinessDelegate {
    private final BusinessLookUp lookupService = new BusinessLookUp();
    private BusinessService businessService;
    private String serviceType;

    void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    void doTask(){
        businessService = lookupService.getBusinessService(serviceType);
        businessService.doProcessing();
    }
}
