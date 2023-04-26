package com.example.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.server.environment.EnvironmentController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.profiles.active=native" )
class ConfigServerApplicationTests {

    @Qualifier("environmentController")
    @Autowired
    private EnvironmentController controller;

    @Test
    public void controllerShouldExist() {
        assertThat(controller).isNotNull();
    }

}
