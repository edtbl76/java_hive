package com.example.batchprocessing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
    Class that represents a row of data (potentially in a database or CSV)
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Person {

    private String firstName;
    private String lastName;
}
