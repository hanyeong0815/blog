package com.self.blog.web.service;

import com.self.blog.application.usecase.MemberProfileFindDetailViewUseCase;
import com.self.blog.application.usecase.MemberProfileFindNicknameUseCase;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileNickname;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberProfileVerifyProxyService {
    private final MemberProfileFindNicknameUseCase memberProfileFindNicknameUseCase;
    private final MemberProfileFindDetailViewUseCase memberProfileFindDetailViewUseCase;

    public MemberProfileNickname verifyNickname(String username) {
        return memberProfileFindNicknameUseCase.findNickname(username);
    }

    public MemberProfileDetailView verifyDetailView(String username) {
        return memberProfileFindDetailViewUseCase.findDetailView(username);
    }
}
