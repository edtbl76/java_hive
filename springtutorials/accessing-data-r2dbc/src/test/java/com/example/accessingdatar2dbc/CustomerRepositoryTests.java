package com.example.accessingdatar2dbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.r2dbc.dialect.H2Dialect.INSTANCE;

@ExtendWith(SpringExtension.class)
@DataR2dbcTest
public class CustomerRepositoryTests {

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    CustomerRepository repository;

    @Test
    public void testFindByLastName() {
        Customer customer = new Customer("first", "last");
        R2dbcEntityTemplate template = new R2dbcEntityTemplate(databaseClient, INSTANCE);

        template.insert(Customer.class)
                .using(customer)
                .then()
                .as(StepVerifier::create)
                .verifyComplete();

        Flux<Customer> findByLastName = repository.findByLastName(customer.getLastName());

        findByLastName.as(StepVerifier::create)
                .assertNext(actual -> {
                    assertThat(actual.getFirstName()).isEqualTo("first");
                    assertThat(actual.getLastName()).isEqualTo("last");
                })
                .verifyComplete();
    }
}
