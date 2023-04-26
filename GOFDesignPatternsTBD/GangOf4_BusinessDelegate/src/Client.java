class Client {

    private final BusinessDelegate businessService;

    Client(BusinessDelegate businessService) {
        this.businessService = businessService;
    }

    void doTask(){
        businessService.doTask();
    }
}
