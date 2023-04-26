package com.example.reading;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @RequestMapping("/to-read")
    public Mono<String> toRead() {
        return bookService.readingList();
    }

}
