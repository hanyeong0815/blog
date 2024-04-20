package com.self.blog.web.controller;

import com.self.blog.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.blog.web.dto.MemberSignupDto.MemberSignupRequestDto;
import com.self.blog.web.service.MemberSignupProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberCommendApi {
    private final MemberSignupProxyService memberSignupProxyService;

    @PostMapping("")
    public MemberLoginResponseDto signup(@RequestBody MemberSignupRequestDto dto) {
        return memberSignupProxyService.signup(dto);
    }
}
