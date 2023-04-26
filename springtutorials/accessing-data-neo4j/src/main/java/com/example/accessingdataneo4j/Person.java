package com.example.accessingdataneo4j;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Node
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name;

    private Person() { }

    public Person(String name) {
        this.name = name;
    }

    @Relationship(type = "TEAMMATE")
    public Set<Person> teammates;

    public void worksWith(Person person) {
        if (teammates == null) {
            teammates = new HashSet<>();
        }
        teammates.add(person);
    }

    @Override
    public String toString() {
        return this.name + "'s teammates => " + Optional.ofNullable(this.teammates)
                .orElse(Collections.emptySet())
                .stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }
}
