package com.donkia.library.user;


import com.donkia.library.dto.CommonDto;
import com.donkia.library.dto.UserDto;
import com.donkia.library.dto.UserInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;

@Api(tags = "회원관련된 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입 확인", notes = "name, email, password 입력을 통한 회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 완료"),
            @ApiResponse(code = 400, message = "필수값 누락"),
    })
    @PostMapping("user")
    public CommonDto signUp(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(bindingResult.hasErrors()){
            return new CommonDto(HttpStatus.BAD_REQUEST.value(), bindingResult.getFieldError());
        }

        User user = userService.saveUser(userDto);


        return new CommonDto(HttpStatus.OK.value(), user);
    }
    
    // 유저정보 조회
    @GetMapping("user/{id}")
    public CommonDto userInformation(@PathVariable Long id){
        UserInfoDto userInfoDto = userService.userInfo(id);
        return new CommonDto(HttpStatus.OK.value(), userInfoDto);
    }

    // 유저 삭제
    @DeleteMapping("user/{id}")
    public CommonDto delete(@PathVariable Long id){
        userService.delete(id);
        return new CommonDto(HttpStatus.OK.value(), id);
    }

}

