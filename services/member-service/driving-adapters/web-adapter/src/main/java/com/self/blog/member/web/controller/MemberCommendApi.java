package com.self.blog.member.web.controller;

import com.self.blog.member.web.dto.InvitationCodeDto;
import com.self.blog.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.blog.member.web.dto.MemberLogoutDto.MemberLogoutRequestDto;
import com.self.blog.member.web.dto.MemberSignupDto.MemberSignupRequestDto;
import com.self.blog.member.web.dto.PasswordUpdateDto.PasswordUpdateRequestDto;
import com.self.blog.member.web.service.CreateInvitationCodeProxyService;
import com.self.blog.member.web.service.MemberLogoutProxyService;
import com.self.blog.member.web.service.MemberSignupProxyService;
import com.self.blog.member.web.service.PasswordUpdateProxyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberCommendApi {
    private final MemberSignupProxyService memberSignupProxyService;
    private final PasswordUpdateProxyService passwordUpdateProxyService;
    private final CreateInvitationCodeProxyService createInvitationCodeProxyService;
    private final MemberLogoutProxyService memberLogoutProxyService;

    @PostMapping("")
    public MemberLoginResponseDto signup(@RequestBody @Valid MemberSignupRequestDto dto) {
        return memberSignupProxyService.signup(dto);
    }

    @PatchMapping("password-update")
    public boolean updatePassword(@RequestBody PasswordUpdateRequestDto dto) {
        return passwordUpdateProxyService.passwordUpdate(dto);
    }

    @PostMapping("invitation-code/send")
    public boolean createInvitationCode(@RequestBody @Valid InvitationCodeDto.InvitationCodeRequestDto dto) {
        return createInvitationCodeProxyService.createInvitationCode(dto.email());
    }

    @PostMapping("logout")
    public void logout(@RequestBody @Valid MemberLogoutRequestDto dto) {
        memberLogoutProxyService.logout(dto.refreshToken());
    }
}
