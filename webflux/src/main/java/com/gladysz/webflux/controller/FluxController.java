package com.gladysz.webflux.controller;

import com.gladysz.webflux.domain.Book;
import com.gladysz.webflux.domain.BookDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxController {

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
        Book b1 = new Book("Title1", "Author1", 2000);
        Book b2 = new Book("Title2", "Author2", 2001);
        Book b3 = new Book("Title3", "Author3", 2002);

        Flux<Book> bookFlux =  Flux.just(b1, b2, b3);

        return bookFlux.map(book ->  new BookDto(book.getTitle(), book.getAuthor()));
                /*.delayElements(Duration.ofSeconds(2))
                .log();*/
    }
}
