package com.example.handlingformsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/*
    Handles HTTP requests for the /greeting endpoint.
    - returns the name of a View which is responsible for rendering the HTML content.
 */
@Controller
public class GreetingController {

    /*
        Specifically mapped to GET verb
        - Model parameter exposes the Greeting object to the View object that is returned.
     */
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    /*
        Specifically mapped to POST verb
        - Model parameter exposes the Greeting object to the View object that is returned.
     */
    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }


}
