package com.example.accessingdatamongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AccessingDataMongoApplication implements CommandLineRunner {

    // *** Created constructor to inject Customer Repository rather than autowiring by field
    final CustomerRepository customerRepository;

    public AccessingDataMongoApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMongoApplication.class, args);
    }

    @Override
    public void run(String... args) {

        // Start Fresh!
        customerRepository.deleteAll();

        // Save some customers
        customerRepository.saveAll(List.of(new Customer("Joel", "Coen"), new Customer("Ethan", "Coen")));

        // Fetch 'em all
        System.out.println("Customers found with findAll()");
        System.out.println("------------------------------");
        customerRepository.findAll().forEach(System.out::println);
        System.out.println();

        // Fetch Just One
        System.out.println("Customer found with findByFirstName('Joel')");
        System.out.println("-------------------------------------------");
        System.out.println(customerRepository.findByFirstName("Joel"));
        System.out.println();

        // Fetch By Last Name
        System.out.println("Customers found with findByLastName('Coen')");
        System.out.println("-------------------------------------------");
        customerRepository.findByLastName("Coen").forEach(System.out::println);


    }
}
