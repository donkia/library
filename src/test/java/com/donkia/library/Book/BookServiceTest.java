package com.donkia.library.Book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("데이터 저장 테스트")
    public void 데이터저장(){
        Book book = Book.builder()
                .name("name1")
                .author("author2")
                .state("state")
                .yearOfPublication(LocalDateTime.now())
                .build();

        bookRepository.save(book);
        Book savedBook = bookRepository.findById(book.getId()).orElseThrow(EntityNotFoundException::new);
        assertEquals(savedBook.getId(), book.getId());
    }

}