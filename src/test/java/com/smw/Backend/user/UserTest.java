package com.smw.Backend.user;

import com.smw.Backend.domain.User;
import com.smw.Backend.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    void save() {

    }

    @Test
    void findByUsername() {
        User user = userService.findByUsername("test");
        Assertions.assertThat(user.getUsername()).isEqualTo("test");
    }
}
