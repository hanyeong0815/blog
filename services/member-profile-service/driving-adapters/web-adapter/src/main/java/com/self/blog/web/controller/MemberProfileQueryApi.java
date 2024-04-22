package com.self.blog.web.controller;

import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileNickname;
import com.self.blog.web.service.MemberProfileVerifyProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class MemberProfileQueryApi {
    private final MemberProfileVerifyProxyService memberProfileVerifyProxyService;

    @GetMapping("{username}")
    public MemberProfileNickname verifyNickname(@PathVariable String username) {
        return memberProfileVerifyProxyService.verifyNickname(username);
    }

    @GetMapping("detail/{username}")
    public MemberProfileDetailView verifyDetailView(@PathVariable String username) {
        return memberProfileVerifyProxyService.verifyDetailView(username);
    }
}
