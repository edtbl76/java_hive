package com.example.resthateoas;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;


/*
    RestController combines 2 annotations
        - Controller
        - ResponseBody

    This is Spring's secret sauce for building RESTful web services.
 */
@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";

    /*
        RequestMapping

            Ensures that HTTP Requests which have been made to the /greeting endpoint are mapped to the
            greeting() method.

            NOTE: This doesn't "qualify" the HTTP request as GET, PUT or POST, because RequestMapping maps
            ALL HTTP operations by default.
                - Use GetMapping, PutMapping, or PostMapping to narrow the mapping.

        RequestParam
            - binds the value of the query string parameter (i.e. the one provided in the HTTP Request) to the
            specified method parameter.

            - the 'defaultValue' attribute means that the parameter is optional. If not presented in the HTTP Request
            this defaultValue is used.

        NOTE:
            - Since RestController is present at the class level, the @ResponseBody annotation is implicitly provided
            to the greeting() method.

            - This causes Spring MVC to return an HttpEntity and its payload (Greeting) directly to the response.
     */
    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));

        /*
            linkTo() and methodOn() are static methods of ControllerLinkBuilder()
            - this emulates method invocation of the controller.
            - the returned LinkBuilder will have inspected the controller method's mapping
            annotation to build up exactly the URI to which the method has been mapped.

            withSelfRel()
            - creates a Link instance that is added to the representational model.
         */
        greeting.add(
                linkTo(methodOn(GreetingController.class).greeting(name))
                        .withSelfRel());

        return new ResponseEntity<>(greeting, OK);
    }
}
