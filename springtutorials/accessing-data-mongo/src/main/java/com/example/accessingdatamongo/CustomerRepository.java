package com.example.accessingdatamongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/*
    MongoRepository
    - This is part of Spring Data MongoDB, and like CrudRepository, it inherites functionality from
    Spring Data Commons
        - ability to derive queries, so you don't have to learn the query language of Mongo to make this work.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
