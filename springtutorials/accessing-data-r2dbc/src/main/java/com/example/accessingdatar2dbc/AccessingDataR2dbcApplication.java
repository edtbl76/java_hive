package com.example.accessingdatar2dbc;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.util.List;

import static java.time.Duration.ofSeconds;

@Slf4j
@SpringBootApplication
public class AccessingDataR2dbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataR2dbcApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CustomerRepository repository) {

        return (args) -> {
            repository.saveAll(List.of(
                        new Customer("Bruce","Wayne"),
                        new Customer("Dick", "Grayson"),
                        new Customer("Jason", "Todd"),
                        new Customer("Tim", "Drake"),
                        new Customer("Damian", "Wayne")))
                    .blockLast(ofSeconds(10));

            // fetch all customers
            log.info("Customers found with findAll()");
            log.info("------------------------------");
            repository.findAll()
                    .doOnNext(customer -> log.info(customer.toString()))
                    .blockLast(ofSeconds(10));

            log.info("");

            // fetch individual customer by id
            repository.findById(2L)
                    .doOnNext(customer -> {
                        log.info("Customer found with findById(2L)");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    }).block(ofSeconds(10));

            // fetch by last name
            log.info("Customer found with findByLastName('Wayne')");
            log.info("-------------------------------------------");
            repository.findByLastName("Wayne")
                    .doOnNext(wayne -> log.info(wayne.toString()))
                    .blockLast(ofSeconds(10));
            log.info("");
        };
    }
}
