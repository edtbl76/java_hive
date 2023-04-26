package com.example.gsrestservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    The Spring application will uses https://github.com/FasterXML/jackson to automatically marshal instances
    of this class type into JSON

    Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter is automatically chosen to convert the
    Greeting instance to JSON.

 */
@AllArgsConstructor
@Getter
public class Greeting {

    private final long id;
    private final String content;

}
