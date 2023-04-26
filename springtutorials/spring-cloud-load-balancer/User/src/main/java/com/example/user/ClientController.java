package com.example.user;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class ClientController {

    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction loadBalancerFunction;

    @RequestMapping("/hi")
    public Mono<String> hi(@RequestParam(value = "name", defaultValue = "Homer") String name) {
        return loadBalancedWebClientBuilder.build()
                .get().uri("http://say-hello/greeting")
                .retrieve().bodyToMono(String.class)
                .map(greeting -> String.format("%s, %s!", greeting, name));
    }

    @RequestMapping("/hello")
    public Mono<String> hello(@RequestParam(value = "name", defaultValue = "Marge") String name) {
        return WebClient.builder()
                .filter(loadBalancerFunction)
                .build()
                .get().uri("http://say-hello/greeting")
                .retrieve().bodyToMono(String.class)
                .map(greeting -> String.format("%s, %s!", greeting, name));
    }


}
