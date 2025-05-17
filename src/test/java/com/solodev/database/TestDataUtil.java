package com.solodev.database;

import com.solodev.database.domain.Author;
import com.solodev.database.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Ellen Grace")
                .age(25)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Goldy Fab")
                .age(24)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Grace Goldy")
                .age(26)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1121")
                .title("Harry Potter")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("1222")
                .title("Goldy Fabregas")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("1333")
                .title("Ellen Goldy")
                .authorId(3L)
                .build();
    }


}
