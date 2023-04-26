package com.example.configclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConfigClientApplicationTests {

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private MessageRestController controller;

    @Qualifier("configDataContextRefresher")
    @Autowired
    private ContextRefresher refresher;

    @Test
    public void contextLoads() {
        assertThat(controller.getMessage()).isNotEqualTo("Hello test");
        TestPropertyValues
                .of("message: Hello test")
                .applyTo(environment);

        assertThat(controller.getMessage()).isNotEqualTo("Hello test");
        refresher.refresh();

        assertThat(controller.getMessage()).isEqualTo("Hello test");
    }

}
