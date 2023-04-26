package com.example.reactiverestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactiveRestServiceApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ReactiveRestServiceApplication.class, args);

        GreetingClient greetingClient = context.getBean(GreetingClient.class);

        // Block for the content here of the JVM will exit before the message is logged
        System.out.println(">> Message = " + greetingClient.getMessage().block());
    }

}
