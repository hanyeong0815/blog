package com.self.blog.profile.web.controller;

import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileNickname;
import com.self.blog.profile.web.service.MemberProfileVerifyProxyService;
import com.self.blog.profile.web.service.VerifyNicknameProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class MemberProfileQueryApi {
    private final MemberProfileVerifyProxyService memberProfileVerifyProxyService;
    private final VerifyNicknameProxyService verifyNicknameProxyService;

    @GetMapping("verify/nickname/{nickname}")
    public boolean verifyNickname(@PathVariable String nickname) {
        return verifyNicknameProxyService.verifyNickname(nickname);
    }

    @GetMapping("{username}")
    public MemberProfileNickname verifyNicknameByUsername(@PathVariable String username) {
        return memberProfileVerifyProxyService.verifyNickname(username);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("detail/{username}")
    public MemberProfileDetailView verifyDetailView(@PathVariable String username) {
        return memberProfileVerifyProxyService.verifyDetailView(username);
    }
}
