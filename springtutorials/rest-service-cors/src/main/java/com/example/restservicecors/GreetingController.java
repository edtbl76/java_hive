package com.example.restservicecors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    /*
        GetMapping is ACTUALLY a short cut for RequestMapping(method = RequestMethod.GET)

        This is a Restful controller, rather than an MVC controller.
        - instead of using a view to perform server-side rendering, we return a greeting object which is
        marshaled into Json (under the hood) and written directly to the HTTP response.

        - it accomplishes this by assuming that every method inherits @RequestBody
            (This is how the returned object data is inserted directly into the response body)


        Cross Origin
            - this is enabled at Controller Method level (meaning that other methods won't inherit
            the side effects of the cross-origin resource sharing annotation)
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
        System.out.println("==== get greeting ====");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greeting-javaconfig")
    public Greeting greetingWithJavaConfig(@RequestParam(required = false, defaultValue = "World")String name) {
        System.out.println("==== get greeting ====");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
