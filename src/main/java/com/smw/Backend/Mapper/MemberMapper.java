package com.smw.Backend.Mapper;

import com.smw.Backend.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findByEmail(String email);

    Member findByNickname(String username);
}
