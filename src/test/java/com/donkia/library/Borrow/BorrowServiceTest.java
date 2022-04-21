package com.donkia.library.Borrow;

import com.donkia.library.Book.Book;
import com.donkia.library.Book.BookRepository;
import com.donkia.library.user.User;
import com.donkia.library.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BorrowServiceTest {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;



    public Book setBook(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .location("A-1")
                .state("")
                //.yearOfPublication(LocalDate.of("2022", "01", "01"))
                .build();

        return bookRepository.save(book);
    }

    public User setUser(){
        User user = User.builder()
                .email("11@11.com")
                .password("1234")
                .name("name")
                .build();
        return userRepository.save(user);
    }

    @Test
    @DisplayName("책 빌리기")
    public void borrowBook(){
        User user = setUser();
        Book book = setBook();

        Borrow borrow = Borrow.builder()
                .bookId(book.getId())
                .userId(user.getId())
                //.borrowDate("2022-12-22")
                //.returnExpectDate("2022-12-31")
                .build();

        Borrow borrowBook = borrowRepository.save(borrow);

        assertEquals(borrowBook.getBookId(), book.getId());

    }

}