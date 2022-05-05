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
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입 확인", notes = "name, email, password 입력을 통한 회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 완료"),
            @ApiResponse(code = 400, message = "필수값 누락"),
    })
    @PostMapping("signup")
    public ResponseEntity signUp(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){

        HttpHeaders headers= new HttpHeaders();
        System.out.println("userDto : " + userDto);
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(bindingResult.hasErrors()){
            //return new ResponseEntity("필수값 확인 요청", headers, HttpStatus.BAD_REQUEST);
            return new ResponseEntity(bindingResult.getFieldError(), headers, HttpStatus.BAD_REQUEST);
        }

        User user = userService.saveUser(userDto);


        return new ResponseEntity(user, HttpStatus.OK);
    }

    /*@PostMapping("login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult){

        HttpHeaders headers= new HttpHeaders();
        System.out.println("loginDto : " + loginDto);
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if(bindingResult.hasErrors()){
            return new ResponseEntity("필수값 확인 요청", headers, HttpStatus.BAD_REQUEST);
        }

        User loginUser = userService.login(loginDto);

        if(loginUser == null){
            return new ResponseEntity("회원이 아닙니다", headers, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity("로그인 성공", headers, HttpStatus.OK);
    }
*/
    @PostMapping("userInfo")
    public UserInfoDto userInformation(String email){
        System.out.println("email : " + email);
        UserInfoDto userInfoDto = userService.userInfo(email);
        System.out.println("userInfoDto : " + userInfoDto);

        if(userInfoDto == null){
            //return new ResponseEntity("회원정보가 없습니다.", HttpStatus.BAD_REQUEST);
            return null;

        }
        //return new ResponseEntity(userInfoDto,HttpStatus.OK);
        return userInfoDto;
    }
}
