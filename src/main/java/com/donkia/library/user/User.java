package com.donkia.library.user;

import com.donkia.library.Book.Book;
import com.donkia.library.Borrow.Borrow;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    @Builder
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrowList;

    public User(){

    }
    //private PasswordEncoder password;



}
