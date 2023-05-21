package com.smw.Backend.service;

import com.smw.Backend.Mapper.MemberMapper;
import com.smw.Backend.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public void save(Member member){
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        memberMapper.save(member);
    }

    public Member findByEmail(String email) {
        return memberMapper.findByEmail(email);
    }

    public Member findByNickname(String nickname) {
        return memberMapper.findByNickname(nickname);
    }
}
