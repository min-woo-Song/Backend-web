package com.smw.Backend.domain.member;

import com.smw.Backend.config.auth.PrincipalDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
public class Member {

    private Long id;

    @Email
    private String email;

    private String password;
    private String nickname;

    private String provider;
    private String providerId;
}