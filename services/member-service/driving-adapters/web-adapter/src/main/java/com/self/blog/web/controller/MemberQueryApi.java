package com.self.blog.web.controller;

import com.self.blog.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.blog.web.service.MemberLoginProxyService;
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

    @GetMapping("login")
    public MemberLoginResponseDto login(Authentication authentication) {
        System.out.println("로그인 진입");
        return memberLoginProxyService.login(authentication);
    }
}
