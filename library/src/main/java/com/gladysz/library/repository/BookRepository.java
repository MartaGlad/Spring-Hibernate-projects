package com.gladysz.library.repository;

import com.gladysz.library.domain.Book;

import java.util.List;


public interface BookRepository {

    List<Book> findAll();

    void save(Book book);

    void deleteByIndex(int index);
}
