package com.donkia.library.Borrow;

import com.donkia.library.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor
public class BorrowDto {

    private String userEmail; //빌리는사람
    List<Long> listBookId;      //빌리는 책

    BorrowDto(){

    }
}
