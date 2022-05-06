package com.donkia.library.dto;

import com.donkia.library.Book.Book;
import com.donkia.library.Borrow.Borrow;
import com.donkia.library.user.User;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
public class UserInfoDto {

    private Long id;

    private String name;

    @Email
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
