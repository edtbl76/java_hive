package com.example.accessingdatamongo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccessingDataMongoApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    Customer bruce;
    Customer damian;
    Customer selina;

    @BeforeEach
    public void setup() {

        customerRepository.deleteAll();

        bruce = customerRepository.save(new Customer("Bruce", "Wayne"));
        damian = customerRepository.save(new Customer("Damian", "Wayne"));
        selina = customerRepository.save(new Customer("Selina", "Kyle"));

    }

    @Test
    public void setsIdOnSave() {
        Customer bruce = customerRepository.save(new Customer("Bruce", "Wayne"));

        assertThat(bruce.id).isNotNull();
    }

    @Test
    public void findsByLastName() {
        List<Customer> result = customerRepository.findByLastName("Kyle");

        assertThat(result).hasSize(1).extracting("firstName").contains("Selina");
    }

    @Test
    public void findsByExample() {
        Customer probe = new Customer(null, "Wayne");
        List<Customer> result = customerRepository.findAll(Example.of(probe));

        assertThat(result).hasSize(2).extracting("firstName").contains("Bruce", "Damian");

    }

}
