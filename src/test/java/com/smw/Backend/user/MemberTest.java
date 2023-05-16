package com.smw.Backend.user;

import com.smw.Backend.domain.member.Member;
import com.smw.Backend.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    MemberService memberService;

    @Test
    void save() {

    }

    @Test
    void findByUsername() {
        Member member = memberService.findByNickname("test");
        Assertions.assertThat(member.getUsername()).isEqualTo("test");
    }
}
