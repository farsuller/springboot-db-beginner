package com.solodev.database.dao.impl;

import com.solodev.database.TestDataUtil;
import com.solodev.database.dao.AuthorDao;
import com.solodev.database.domain.Author;
import com.solodev.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {


    private AuthorDao authorDao;
    private BookDaoImpl undertest;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl undertest, AuthorDao authorDao){
        this.undertest = undertest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){

        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());
        undertest.create(book);

        Optional<Book> result = undertest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        undertest.create(bookA);

        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(author.getId());
        undertest.create(bookB);

        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(author.getId());
        undertest.create(bookC);

        List<Book> result = undertest.find();
        assertThat(result).hasSize(3).containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        undertest.create(bookA);


        bookA.setTitle("UPDATED");
        undertest.update(bookA.getIsbn(), bookA);

        Optional<Book> result = undertest.findOne(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        undertest.create(bookA);

        undertest.delete(bookA.getIsbn());

        Optional<Book> result = undertest.findOne(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
