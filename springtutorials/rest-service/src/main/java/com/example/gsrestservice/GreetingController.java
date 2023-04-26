package com.example.gsrestservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


/*
    RestController annotation identifies a Spring Component as one that handles HTTP requests.

    Traditional MVC Controller vs. RESTful Web Service Controller (Spring)

        - MVC controllers use a view technology to perform server-side rendering of the data to HTML

        - the Spring RestController populates and returns a Greeting object (marshalled to JSON) and written directly
        to the HTTP response.

        - every Method in a RestController returns a DOMAIN OBJECT instead of a VIEW.
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
       GetMapping ensures that GET requests to /greeting result in calling this greeting() method.

       @RequestParam binds the value of the query string parameter (provided in the HTTP request) to the
       method parameter.
        - it also provides a default value
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
