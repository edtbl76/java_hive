package com.example.testingweb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    More realistic implementation
    - introduces an extra component to store the greeting (in a new controller)
 */
@Controller
@AllArgsConstructor
public class GreetingController {

    private final GreetingService service;

    @RequestMapping("/greeting")
    public @ResponseBody String greeting() {
        return service.greet();
    }

}
