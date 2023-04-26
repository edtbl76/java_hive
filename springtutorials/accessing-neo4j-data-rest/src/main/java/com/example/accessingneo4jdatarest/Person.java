package com.example.accessingneo4jdatarest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;


}
