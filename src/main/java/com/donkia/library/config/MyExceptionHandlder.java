package com.donkia.library.config;

import com.donkia.library.dto.CommonDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// exception을 낚아챔
@RestController
@ControllerAdvice // 모든 exception을 관리
public class MyExceptionHandlder {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonDto illegalArgumentException(IllegalArgumentException e){
        return new CommonDto(HttpStatus.BAD_REQUEST.value(), "조회가 되지않습니다");
    }

    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e){
        return e.getMessage();

    }
}
