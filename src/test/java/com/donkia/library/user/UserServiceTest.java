package com.donkia.library.user;

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
}