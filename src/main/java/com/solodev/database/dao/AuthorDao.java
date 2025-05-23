package com.solodev.database.dao;

import com.solodev.database.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(long id, Author author);

    void delete(long id);
}
