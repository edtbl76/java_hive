package com.example.testingrestdocs;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class CrudController {

    @GetMapping
    public List<CrudInput> read(@RequestBody CrudInput input) {
        return List.of(input);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public HttpHeaders save(@RequestBody CrudInput input) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(CrudController.class).slash(input.getTitle()).toUri());
        return headers;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    HttpHeaders delete(@PathVariable("id") long id) {
        return new HttpHeaders();
    }

    @PutMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    void put(@PathVariable("id") long id, @RequestBody CrudInput input) { }

    @PatchMapping("/{id}")
    public List<CrudInput> patch(@PathVariable("id") long id, @RequestBody CrudInput input) {
        input.setId(id);
        return List.of(input);
    }
}
