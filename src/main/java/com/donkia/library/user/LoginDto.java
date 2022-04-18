package com.donkia.library.user;

import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Getter
public class LoginDto {

    @NonNull
    private String email;

    @NonNull
    @Size(min = 8)
    private String password;

    public LoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public LoginDto(){

    }
}
