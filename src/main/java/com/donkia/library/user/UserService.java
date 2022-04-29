package com.donkia.library.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //회원가입
    public User saveUser(UserDto userDto){

        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword())) // 패스워드는 암호화
                .email(userDto.getEmail())
                .build();

        return userRepository.save(user);
    }

    // 로그인
    /*public User login(LoginDto loginDto){
        User loginUser = userRepository.findByEmail(loginDto.getEmail());

        if(!passwordEncoder.matches(loginDto.getPassword(), loginUser.getPassword())){
            return null;
        }
        return loginUser;
    }*/

    // 유저 정보
    public UserInfoDto userInfo(String email){
        User user = userRepository.findByEmail(email);

        if(user != null){
            UserInfoDto userInfoDto = new UserInfoDto(user);
            return userInfoDto;
        }
        return null;

    }

}
