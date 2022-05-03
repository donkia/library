package com.donkia.library.Book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public Book getList() {
        return new Book();
    }

    public long save(BookResponseDto bookResponseDto) {
        Book saveBook = Book.builder()
                .author(bookResponseDto.getAuthor())
                .location(bookResponseDto.getLocation())
                .name(bookResponseDto.getName())
                .state(bookResponseDto.getState())
                .yearOfPublication(bookResponseDto.getYearOfPublication())
                .build();
        bookRepository.save(saveBook);
        return saveBook.getId();
    }

}
