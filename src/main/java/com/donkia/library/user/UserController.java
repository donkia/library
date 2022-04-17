package com.donkia.library.user;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
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
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입 확인", notes = "name, email, password 입력을 통한 회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 완료"),
            @ApiResponse(code = 400, message = "필수값 누락"),
    })
    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){

        HttpHeaders headers= new HttpHeaders();
        System.out.println("userDto : " + userDto);
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(bindingResult.hasErrors()){
            return new ResponseEntity("필수값 확인 요청", headers, HttpStatus.BAD_REQUEST);
        }

        User user = userService.saveUser(userDto);


        return new ResponseEntity(user, HttpStatus.OK);
    }
}
