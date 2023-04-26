package com.example.user;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.List;

@AllArgsConstructor
public class DemoServiceInstanceListSupplier implements ServiceInstanceListSupplier {

    private final String serviceId;

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(List.of(
                new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8090, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 9092, false),
                new DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 9999, false)
        ));
    }
}
