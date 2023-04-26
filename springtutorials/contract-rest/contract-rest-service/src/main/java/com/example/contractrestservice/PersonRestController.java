package com.example.contractrestservice;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PersonRestController {

    private final PersonService personService;

    @GetMapping("/person/{id}")
    public Person findPersonById(@PathVariable("id") Long id) {
        return personService.findPersonById(id);
    }
}
