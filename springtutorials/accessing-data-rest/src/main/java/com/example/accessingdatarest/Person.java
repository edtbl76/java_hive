package com.example.accessingdatarest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
    http://stateless.co/hal_specification.html

    Spring Data REST uses the HAL format for JSON output.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;


}
