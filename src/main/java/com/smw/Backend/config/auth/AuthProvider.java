package com.smw.Backend.config.auth;

import com.smw.Backend.domain.member.Member;
import com.smw.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails member = userDetailsService.loadUserByUsername(email);

        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
            return new UsernamePasswordAuthenticationToken(member, password, null);
        }

        throw new BadCredentialsException("id 혹은 password 불일치");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
