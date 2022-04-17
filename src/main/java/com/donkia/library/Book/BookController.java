package com.donkia.library.Book;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/getbooks")
    public void getList(){
       /* Book book = Book.builder()
                .author("1")
                .build();
        bookService.save(book);
        System.out.println("books");*/
    }

    @PostMapping(value="/saveBooks")
    public ResponseEntity setBooks(@RequestBody  BookResponseDto bookResponseDto){
        System.out.println(bookResponseDto.toString());

        long id = bookService.save(bookResponseDto);
        return new ResponseEntity(200, HttpStatus.OK);
    }

}
