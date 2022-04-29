package com.donkia.library.user;

import com.donkia.library.Book.Book;
import com.donkia.library.Borrow.Borrow;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto {

    private Long id;

    private String name;

    private String email;

    private String role;

    List<Borrow> borrowList;

    public UserInfoDto(){

    }

    public UserInfoDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.borrowList = user.getBorrowList();
    }
}
