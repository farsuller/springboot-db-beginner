package com.solodev.database.dao;

import com.solodev.database.domain.Book;

import java.util.Optional;
import java.util.List;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();

    void update(String isbn, Book book);

    void delete(String isbn);
}
