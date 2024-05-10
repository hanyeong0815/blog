package com.self.blog.member.web.controller;

import com.self.blog.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.blog.member.web.service.MemberLoginProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberQueryApi {
    private final MemberLoginProxyService memberLoginProxyService;

    // spring security를 통해 이미 로그인 인증처리가 완료되고 넘어온 authentication token
    @GetMapping("login")
    public MemberLoginResponseDto login(Authentication authentication) {
        return memberLoginProxyService.login(authentication);
    }

    @GetMapping("test")
    public String test(String test) {
        return test;
    }
}
