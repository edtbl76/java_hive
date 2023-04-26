package com.example.springbootstarter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            System.out.println("Inspecting beans provided by Sprint Boot...");

            String[] beansNames = context.getBeanDefinitionNames();
            Arrays.sort(beansNames);
            for (String beansName : beansNames) {
               System.out.println(beansName);
            }
        };
    }

}
