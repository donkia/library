package com.donkia.library.Borrow;

import com.donkia.library.BaseTimeEntity;
import com.donkia.library.Book.Book;
import com.donkia.library.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

//책을 대여하는 엔티티

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Borrow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="book_id")
    private Book book;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate borrowDate; //대출일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnExpectDate; //반납 예정일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate; //반납일

}
