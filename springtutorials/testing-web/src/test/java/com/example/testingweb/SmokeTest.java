package com.example.testingweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/*
    Ensures that the Application Context is creating the Controller.
    - "infrastructure smoke test"

    The controller is injected before the test methods are run.
    - Assertions are tested w/ AssertJ


    NOTE:
        Spring Test
 */
@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController controller;

    @Test
    public void controllerShouldExist() {
        assertThat(controller).isNotNull();
    }
}
