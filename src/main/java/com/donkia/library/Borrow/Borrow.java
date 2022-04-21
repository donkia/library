package com.donkia.library.Borrow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

//책을 대여하는 엔티티

@Entity
@Getter
@Builder
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long userId;

    Long bookId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDateTime borrowDate; //대출일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDateTime returnExpectDate; //반납 예정일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDateTime returnDate; //반납일

    public Borrow(){

    }



}
