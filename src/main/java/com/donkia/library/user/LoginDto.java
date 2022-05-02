package com.donkia.library.user;

import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Getter
public class LoginDto {

    @NonNull
    private String email;

    @NonNull
    @Size(min = 8, message = "최소 8자리 이상 입력해주세요.")
    private String password;

    public LoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public LoginDto(){

    }
}
