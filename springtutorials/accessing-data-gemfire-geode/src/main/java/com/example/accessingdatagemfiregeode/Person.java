package com.example.accessingdatagemfiregeode;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

/*
    Defining a Simple Apache Geode Entity
    - it's an IMDG (In-Memory Data Grid) that maps data to regions
    - it supports configuring distributed regions that partition and replicate data across multiple nodes in a
    cluster.

                (This example uses LOCAL only)

    @Region
        - impls ConcurrentMap interface
        - it is MUCH more robust
            - provides distribution and replication of data within a managed region.


        - "People" is the name of our region.
        - when an instance of Person.class is created, it is stored in the Apache Geode region.

 */
@Region(value = "People")
public class Person implements Serializable {

    @Id
    @Getter
    private final String name;

    @Getter
    private final int age;

    @PersistenceConstructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s is %d years old", getName(), getAge());
    }
}
