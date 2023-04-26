package com.example.resthateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/*
    This is a "resource representation" class (just a form of a POJO), that models the representation (REST)
    of a Greeting.

    RepresentationModel adds instances of Link to ensure that they are rendered properly
    - _links property is the fundamental property of the representation model.

    {"content":"Hello, World!","_links":{"self":{"href":"http://localhost:8080/greeting?name=World"}}}

 */
public class Greeting extends RepresentationModel<Greeting> {

    @Getter
    private final String content;

    /*
        JsonCreator -> tells Jackson how to create an instance of this POJO
        JsonProperty -> marks the field into which Jackson should put this constructor argument.
     */
    @JsonCreator
    public Greeting(@JsonProperty("content") String content) {
        this.content = content;
    }
}
