package com.example.accessingdatarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/*
    Leverage Spring Data Commons 'PagingAndSortingRepository'

    @RepositoryRestResource directs Spring MVC to create RESTful endpoints
    at the provided endpoint.

        NOTE: It isn't required to EXPORT repositories, it's only required for changing
        export details.
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByLastName(@Param("name") String name);
}
