package com.donkia.library.Borrow;

import com.donkia.library.Book.Book;
import com.donkia.library.Book.BookRepository;
import com.donkia.library.user.User;
import com.donkia.library.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void borrowBook(BorrowDto borrowDto){
        User borrowUser = userRepository.findByEmail(borrowDto.getUserEmail());
        for(Long bookId : borrowDto.getListBookId()) {
            Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
            Borrow borrow = Borrow.builder()
                    .user(borrowUser)
                    .book(book)
                    .borrowDate(LocalDate.now())
                    .returnExpectDate(LocalDate.now().plusDays(14))
                    .build();
            borrowRepository.save(borrow);
        }
    }

    public void returnBook(){

    }


}
