import java.util.ArrayList;
import java.util.List;

class Cache {

    private final List<Service> services;

    Cache() {
        services = new ArrayList<>();
    }

    Service getService(String serviceName){

        for(Service service : services) {
            if(service.getName().equalsIgnoreCase(serviceName)){
                System.out.println("Returning cached " + serviceName + " object");
                return service;
            }
        }
        return null;
    }

    void addService(Service newService) {
        boolean exists = false;

        for (Service service : services) {
            if(service.getName().equalsIgnoreCase(newService.getName())) {
                exists = true;
            }
        }
        if(!exists){
            services.add(newService);
        }
    }
}
