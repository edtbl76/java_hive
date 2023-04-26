package com.example.contractrestservice;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {

    private final Map<Long, Person> personMap;

    public PersonService() {
        personMap = new HashMap<>();
        personMap.put(1L, new Person(1L, "Steve", "Martin"));
        personMap.put(2L, new Person(2L, "Martin", "Short"));
        personMap.put(3L, new Person(3L, "Chevy", "Chase"));
    }

    Person findPersonById(Long id) {
        return personMap.get(id);
    }
}
