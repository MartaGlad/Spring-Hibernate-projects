package com.gladysz.library.service;

import com.gladysz.library.domain.Book;
import com.gladysz.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository repository;


    @Override
    public List<Book> getBooks() {
        return repository.findAll();
    }


    @Override
    public void createBook(Book book) {
        repository.save(book);
    }


    @Override
    public void deleteBook(int index) {
        repository.deleteByIndex(index);
    }
}
