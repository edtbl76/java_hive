package com.example.accessingdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class AccessingDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository) {
        return (args) -> {
            // Save some customers
            customerRepository.save(new Customer("Wyatt", "Earp"));
            customerRepository.save(new Customer("Morgan", "Earp"));
            customerRepository.save(new Customer("Doc", "Holliday"));
            customerRepository.save(new Customer("Virgil", "Earp"));
            customerRepository.save(new Customer("Bat", "Masterson"));

            // Get 'em
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // Fetch one by Id
            Customer customer = customerRepository.findById(5L);
            log.info("Customers found with findById(5L):");
            log.info("----------------------------------");
            log.info(customer.toString());
            log.info("");

            // Fetch Customers By Last Name
            log.info("Customers found with findByLastName('Earp'):");
            log.info("--------------------------------------------");
            customerRepository.findByLastName("Earp").forEach(cust -> log.info(cust.toString()));
            log.info("");


        };
    }
}
