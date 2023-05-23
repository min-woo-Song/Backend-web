package com.smw.Backend.user;

import com.smw.Backend.domain.member.Member;
import com.smw.Backend.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    MemberService memberService;

    @Test
    @Commit
    void save() {
        String id = "test";
        Member member;
        for (int i = 2; i <= 1000; i++){
            member = Member.builder().
                    email(id+i+"@test.com")
                    .nickname(id+i)
                    .password(id+i)
                    .build();
            memberService.save(member);
        }
        Member name = memberService.findByNickname("test");
        Assertions.assertThat(name.getNickname()).isEqualTo("test");
    }

    @Test
    void findByUsername() {
        Member member = memberService.findByNickname("test");
        Assertions.assertThat(member.getNickname()).isEqualTo("test");
    }
}
