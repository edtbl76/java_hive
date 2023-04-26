package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
    This is a controller, with a request mapping that suggests that the URL's start
    w/ /demo for this app path.
 */
@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository repository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        /*
            ResponseBody means the returned String is a response rather than a view.
         */

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        repository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return repository.findAll();
    }
}
