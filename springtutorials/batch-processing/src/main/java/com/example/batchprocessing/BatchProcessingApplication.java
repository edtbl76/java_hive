package com.example.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/*
    SpringBootApplication is a powerful annotation that does the following:

        - Configuration:
            - tags the class as a source of Bean definitions for the Application Context

        - EnableAutoConfiguration
            - tells SpringBoot to start adding beans based on
                - class path settings
                - other beans
                - various property settings


        - ComponentScan
            - tells Spring to look for other
                - components
                - configurations
                - services
            in the package directory, letting it find other CONTROLLERS.
 */
@SpringBootApplication
public class BatchProcessingApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BatchProcessingApplication.class, args);

        // Makes sure that app exists once it is done running.
        System.exit(SpringApplication.exit(context));
    }

}
