package com.solodev.database.dao.impl;

import com.solodev.database.TestDataUtil;
import com.solodev.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl undertest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        undertest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("1121"),
                eq("Harry Potter"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql(){
        undertest.findOne("1121");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("1121")

        );
    }

    @Test
    public void testThatFindGenerateCorrectSql(){
        undertest.find();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id from books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());

    }


    @Test
    public void testThatUpdateGenerateCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        undertest.update("1121", book);
        verify(jdbcTemplate).update("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "1121", "Harry Potter", 1L, "1121");
    }

    @Test
    public void testThatDeleteGenerateTheCorrectSql(){
        undertest.delete("1121");
        verify(jdbcTemplate).update("DELETE FROM books where isbn = ?",
                "1121");
    }
}
