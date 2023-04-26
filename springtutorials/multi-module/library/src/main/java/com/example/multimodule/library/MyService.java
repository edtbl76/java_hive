package com.example.multimodule.library;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class MyService {

    private final ServiceProperties properties;

    public String message() {
        return this.properties.getMessage();
    }
}
