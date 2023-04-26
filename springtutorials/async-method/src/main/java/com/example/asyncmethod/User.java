package com.example.asyncmethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*
    JsonIgnoreProperties
    - this annotation tells spring to ignore any attributes that aren't listed in this class.
    - simplifies building REST calls and making domain objects.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String blog;
}
