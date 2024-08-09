package com.self.blog.member.web.controller;

import com.self.blog.member.application.usecase.data.JwtTokenPair;
import com.self.blog.member.web.dto.InvitationCodeDto.CertifyInvitationCodeRequestDto;
import com.self.blog.member.web.dto.JwtTokenDto.RefreshJwtTokenRequestDto;
import com.self.blog.member.web.dto.MemberLoginDto.MemberLoginResponseDto;
import com.self.blog.member.web.service.CertifyInvitationCodeProxyService;
import com.self.blog.member.web.service.MemberLoginProxyService;
import com.self.blog.member.web.service.RefreshJwtTokenProxyService;
import com.self.blog.member.web.service.VerifyUsernameProxyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberQueryApi {
    private final VerifyUsernameProxyService verifyUsernameProxyService;
    private final MemberLoginProxyService memberLoginProxyService;
    private final CertifyInvitationCodeProxyService certifyInvitationCodeProxyService;
    private final RefreshJwtTokenProxyService refreshJwtTokenProxyService;

    @GetMapping("verify-username/{username}")
    public boolean verifyUsername(@PathVariable String username) {
        return verifyUsernameProxyService.verifyUsername(username);
    }

    // spring security를 통해 이미 로그인 인증처리가 완료되고 넘어온 authentication token
    @PostMapping("login")
    public MemberLoginResponseDto login(Authentication authentication) {
        return memberLoginProxyService.login(authentication);
    }

    @PostMapping("invitation-code/certify")
    public boolean certifyInvitationCode(@RequestBody @Valid CertifyInvitationCodeRequestDto dto) {
        return certifyInvitationCodeProxyService.certifyInvitationCode(dto);
    }

    @PostMapping("refresh-token")
    public JwtTokenPair refreshJwtToken(@RequestBody RefreshJwtTokenRequestDto dto) {
        return refreshJwtTokenProxyService.refreshJwtToken(dto.refreshToken());
    }
}
