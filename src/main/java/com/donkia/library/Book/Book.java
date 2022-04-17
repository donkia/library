package com.donkia.library.Book;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String author;
    private String state;
    private String location;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime yearOfPublication; //출판년도

    @Builder
    Book(String name, String author, String state, String location, LocalDateTime yearOfPublication){
        this.name = name;
        this.author = author;
        this.state = state;
        this.location = location;
        this.yearOfPublication = yearOfPublication;
    }

    public BookResponseDto setBookResponseDto(){
        BookResponseDto bookResponseDto = BookResponseDto.builder()
                .author(this.author)
                .location(this.location)
                .name(this.name)
                .state(this.state)
                .yearOfPublication(this.yearOfPublication)
                .build();
        return bookResponseDto;
    }
}