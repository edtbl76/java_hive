package com.example.servingwebcontentmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
    Controller
    - annotates class as a controller, which is responsible for handling HTTP requests.

 */
@Controller
public class GreetingController {

    /*
        Maps HTTP Requests to /greeting endpoing to the greeting() method.

        return value is a View, which is the presentation component of the MVC pattern, focused on rendering
        the HTML content.
     */
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required = false, defaultValue = "World") String name, Model model) {

        /*
            This adds the value of the name parameter to a Model object, which makes it accessible to
            the View template.
         */
        model.addAttribute("name", name);
        return "greeting";
    }
}
