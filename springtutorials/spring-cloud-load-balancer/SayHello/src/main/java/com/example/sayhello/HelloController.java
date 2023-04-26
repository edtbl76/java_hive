package com.example.sayhello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/greeting")
    public String greet() {
        log.info("Access /greeting");

        List<String> greetings = List.of("Hello", "Greetings", "Salutations");

        Random random = new Random();
        int randomNumber = random.nextInt(greetings.size());
        return greetings.get(randomNumber);
    }


    @GetMapping("/")
    public String home() {
        log.info("Access /");
        return "Sup!";
    }

}
