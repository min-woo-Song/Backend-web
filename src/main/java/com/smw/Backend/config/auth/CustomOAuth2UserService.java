package com.smw.Backend.config.auth;

import com.smw.Backend.domain.member.Member;
import com.smw.Backend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String nickname = oAuth2User.getName();

        Member member = memberService.findByEmail(email);

        if (member == null) {
            member = Member.builder()
                    .email(email)
                    .nickname(nickname)
                    .password(bCryptPasswordEncoder.encode(provider + "_" + providerId))
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberService.save(member);
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }
}
