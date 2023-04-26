package com.example.contractrestservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Person {

    private Long id;
    private String name;
    private String surname;
}
