package com.smw.Backend.config.auth;

import com.smw.Backend.config.auth.userInfo.GoogleUserInfo;
import com.smw.Backend.config.auth.userInfo.KakaoUserInfo;
import com.smw.Backend.config.auth.userInfo.NaverUserInfo;
import com.smw.Backend.config.auth.userInfo.OAuth2UserInfo;
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

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        if(provider.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        } else if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String nickname = oAuth2UserInfo.getName();

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

        return new PrincipalDetails(member, oAuth2UserInfo);
    }
}
