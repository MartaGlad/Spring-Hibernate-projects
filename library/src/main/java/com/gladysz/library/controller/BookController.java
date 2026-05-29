package com.gladysz.library.controller;

import com.gladysz.library.domain.Book;
import com.gladysz.library.domain.BookDto;
import com.gladysz.library.mapper.BookMapper;
import com.gladysz.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    @GetMapping
    public List<BookDto> getBooks() {
        return BookMapper.toBookDtoList(service.getBooks());
    }


    @PostMapping
    public void createBook(@RequestBody BookDto bookDto) {

        service.createBook(BookMapper.toBook(bookDto));
    }


    @DeleteMapping(path = "/{index}")
    public void deleteBook(@PathVariable int index) {

        service.deleteBook(index);
    }
}
