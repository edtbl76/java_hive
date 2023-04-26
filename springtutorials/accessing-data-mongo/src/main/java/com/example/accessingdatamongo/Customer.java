package com.example.accessingdatamongo;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/*
    Basic Entity.
    - This isn't very complicated.
    - Mongo maps Collections to Classes, so there will be a Collection called "Customer"
    that maps to instances of this Entity class.

    Id
    - this is the annotation to fit the standard name for a Mongo document id.

 */

@NoArgsConstructor
@ToString
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
