package com.example.relationaldataaccess;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    private long id;
    private String firstName;
    private String lastName;

}
