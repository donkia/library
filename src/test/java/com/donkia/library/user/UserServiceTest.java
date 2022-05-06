package com.donkia.library.user;

import com.donkia.library.dto.LoginDto;
import com.donkia.library.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Test
    void saveUser() {

        UserDto userDto = new UserDto("kim", "11@c.com", "1234");
        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .build();
        User savedUser = userRepository.save(user);


        assertEquals(savedUser.getEmail(), userDto.getEmail());
        assertTrue(passwordEncoder.matches(userDto.getPassword(), savedUser.getPassword()));
    }

    @Test
    @DisplayName("로그인")
    void 로그인(){
        UserDto userDto = new UserDto("kim", "11@test.com", "1234");
        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .build();
        User savedUser = userRepository.save(user);

        LoginDto loginDto = new LoginDto("11@test.com", "1234");

        User loginUser = userRepository.findByEmail(loginDto.getEmail());
        assertTrue(passwordEncoder.matches(loginDto.getPassword(), loginUser.getPassword()));

    }
}