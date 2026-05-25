package com.gladysz.webflux.service;

import com.gladysz.webflux.domain.Book;
import com.gladysz.webflux.domain.BookDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class BookService {

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
