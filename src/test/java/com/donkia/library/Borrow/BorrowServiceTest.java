package com.donkia.library.Borrow;

import com.donkia.library.Book.Book;
import com.donkia.library.Book.BookRepository;
import com.donkia.library.user.User;
import com.donkia.library.user.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BorrowServiceTest {

    static long num;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void beforeall(){
        num = 0;
    }

    @Test
    @DisplayName("책 빌리기")
    public void 책빌리기(){
        User user = setUser();

        ArrayList<Borrow> borrowArrayList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Book book = setBook();

            Borrow borrow = Borrow.builder()
                    .bookId(book.getId())
                    .userId(user.getId())
                    //.borrowDate("2022-12-22")
                    //.returnExpectDate("2022-12-31")
                    .build();
            Borrow borrowBook = borrowRepository.save(borrow);
            borrowArrayList.add(borrowBook);
        }
        user.setBorrowList(borrowArrayList);


       // assertEquals(user.getBorrowList().get(0) , borrowArrayList.get(0));
       // assertEquals(user.getBorrowList().get(0).bookId);

    }

    public Book setBook(){

        Book book = Book.builder()
                .name("name" + num)
                .author("author" + num)
                .location("A-1")
                .state("")
                //.yearOfPublication(LocalDate.of("2022", "01", "01"))
                .build();
        num = num + 1;

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




}