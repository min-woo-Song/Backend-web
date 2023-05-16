package com.smw.Backend.controller;

import com.smw.Backend.domain.member.Member;
import com.smw.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login") // 로그인 폼
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/signup") // 회원 가입 폼
    public String signUpForm() {
        return "auth/signup";
    }

    @PostMapping("/signup") // 회원 가입
    public String signUp(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "auth/signup";

        memberService.save(member);
        return "redirect:/";
    }
}
