package com.gladysz.webflux.controller;

import com.gladysz.webflux.domain.BookDto;
import com.gladysz.webflux.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxController {

    private final BookService bookService;

    public FluxController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(
            value ="/strings",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE /*APPLICATION_NDJSON_VALUE*/
    )
    public Flux<String> getStrings() {
        return Flux
                .just("a", "b", "c", "d", "e")
                .delayElements(Duration.ofSeconds(2))
                .log();
    }


    @GetMapping(
            value = "/books" /*,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE*/
    )
    public Flux<BookDto> getBooks() {

        return bookService.getBooks();
    }
}
