package com.example.accessingdatajpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

/*
    Entity
        - JPA entity annotation. This "maps" this class to a table in the database named "Customer"
            - the Id annotation is used to represent that this is the object's Id/Primary Key in the DB
            - GeneratedValue annotates that the Id should be automatically generated.

        - default (no args) constructor is a requirement for JPA. (We handle it w/ Lombok and adjust the
            access level to ensure it isn't used.)
        - (The is a read only entity, hence only getters)

 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    /*
        This is the constructor we'll actually use to persist the instances of Customer to the DB.
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
