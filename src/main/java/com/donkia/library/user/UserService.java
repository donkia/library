package com.donkia.library.user;

import com.donkia.library.dto.UserDto;
import com.donkia.library.dto.UserInfoDto;
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
    public User saveUser(UserDto userDto) {

        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword())) // 패스워드는 암호화
                .email(userDto.getEmail())
                .build();

        return userRepository.save(user);
    }

    // 유저 정보
    public UserInfoDto userInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        System.out.println("user : " + user);
        UserInfoDto userInfoDto = new UserInfoDto(user);
        return userInfoDto;

    }

    // 유저 삭제
    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

}
