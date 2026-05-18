package com.gladysz.webflux;

import com.gladysz.webflux.domain.Book;
import com.gladysz.webflux.domain.BookDto;
import reactor.core.publisher.Flux;

public class FirstFluxCheck {

    public static void main(String[] args) {

        Book b1 = new Book("Title1", "Author1", 2000);
        Book b2 = new Book("Title2", "Author2", 2001);

        Flux<Book> bookFlux = Flux.just(b1, b2);

        bookFlux = bookFlux.concatWith(Flux.error(new Exception("Test Exception")));

        bookFlux.subscribe(System.out::println, FirstFluxCheck::handleException);


        Flux<BookDto> bookDtoFlux =  bookFlux.map(
                book -> new BookDto(book.getTitle(), book.getAuthor())
        );

        bookDtoFlux.subscribe(System.out::println, FirstFluxCheck::handleException);
    }


    private static void handleException(Throwable e) {
        System.out.println("There was an error: " + e);
    }
}
