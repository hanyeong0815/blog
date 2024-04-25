package com.self.blog.member.web.controller;

import com.self.blog.member.web.dto.MemberSignupDto;
import com.self.blog.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.blog.member.web.service.MemberSignupProxyService;
import jakarta.validation.Valid;
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
    public MemberLoginResponseDto signup(@RequestBody @Valid MemberSignupDto.MemberSignupRequestDto dto) {
        return memberSignupProxyService.signup(dto);
    }
}
