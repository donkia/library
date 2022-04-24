package com.donkia.library.Borrow;

import com.donkia.library.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;
    private final UserService userService;

    @PostMapping("/borrow")
    public ResponseEntity borrowBook(@RequestBody BorrowDto borrowDto){
        borrowService.borrowBook(borrowDto);

        return new ResponseEntity("대출이 완료되었습니다.", HttpStatus.OK);
    }

    @PutMapping("/return")
    public void returnBook(){

    }
}
