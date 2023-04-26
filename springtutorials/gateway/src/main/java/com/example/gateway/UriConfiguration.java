package com.example.gateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("example")
public class UriConfiguration {

    @Getter @Setter
    private String httpbin = "http://httpbin.org:80";
}
