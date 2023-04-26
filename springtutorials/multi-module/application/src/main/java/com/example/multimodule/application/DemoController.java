package com.example.multimodule.application;

import com.example.multimodule.library.MyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class DemoController {

    private final MyService myService;


    @GetMapping("/")
    public String home() {
        return myService.message();
    }


}
