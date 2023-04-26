package com.example.restservicecors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Greeting {

    private final long id;
    private final String content;

    public Greeting() {
        this.id = -1;
        this.content = "";
    }


}
