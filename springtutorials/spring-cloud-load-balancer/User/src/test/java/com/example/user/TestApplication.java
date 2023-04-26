package com.example.user;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableAutoConfiguration
@RestController
public class TestApplication {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping(value = "/greeting")
    public Integer greet()  {
        return atomicInteger.incrementAndGet();
    }

    @RequestMapping(value = "/")
    public String health() {
        return "ok";
    }
}
