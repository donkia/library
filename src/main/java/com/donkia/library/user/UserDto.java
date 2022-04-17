package com.donkia.library.user;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Builder
@Getter
@ToString
public class UserDto {

    @NotNull
    @ApiModelProperty(value="이름", dataType = "string", required = true)
    private String name;

    @Email
    @ApiModelProperty(value="이메일", dataType = "string", required = true)
    private String email;

    @ApiModelProperty(value="패스워드", dataType = "string", required = true)
    private String password;

    UserDto(){

    }

    UserDto(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;

    }

}
