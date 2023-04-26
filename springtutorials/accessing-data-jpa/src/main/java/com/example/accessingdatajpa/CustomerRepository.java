package com.example.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
    - Spring Data JPA (like the other SD projects), uses JPA to store data in a database.
        - the biggest value is creating repo implementations automatically at runtime from
        this repository interface.

        MOST IMPORTANT VALUE:
        - we ONLY have to write the interface. The implementations are created at RUNTIME when you run the
        application.

    CrudRepository
    - powerful interface we can extend to inherit a bunch of basic Crud operations.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
