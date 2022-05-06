package com.donkia.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CommonDto<T> {

    private int statusCode;
    private T data;

}
