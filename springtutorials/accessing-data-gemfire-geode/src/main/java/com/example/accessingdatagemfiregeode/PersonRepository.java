package com.example.accessingdatagemfiregeode;

import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

/*
    PersonRepository
        - extends CrudRepository<V,K> from Spring Data Commons
        - specifies types for the Value and Key (Person, String)
        - comes w/ standard Crud stuff, and whatever else we decided to add.

    The value of CrudRepo + Spring Data Commons is that it derives queries from our code,
    so we don't need to know the OQL query language of Apache Geode. (the Framework translates it for us).


 */
public interface PersonRepository extends CrudRepository<Person, String> {

    @Trace
    Person findByName(Person name);

    @Trace
    Iterable<Person> findByAgeGreaterThan(int age);

    @Trace
    Iterable<Person> findByAgeLessThan(int age);

    @Trace
    Iterable<Person> findByAgeGreaterThanAndAgeLessThan(int highAge, int lowAge);
}
