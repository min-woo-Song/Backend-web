package com.smw.Backend.service;

import com.smw.Backend.Mapper.BoardMapper;
import com.smw.Backend.Mapper.UserMapper;
import com.smw.Backend.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void save(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userMapper.save(user);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
