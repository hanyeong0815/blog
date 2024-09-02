package com.self.blog.profile.application.service;

import com.self.blog.profile.application.aop.ProfileFindAspect;
import com.self.blog.profile.application.aop.ProfileSaveAspect;
import com.self.blog.profile.application.exception.MemberProfileErrorCode;
import com.self.blog.profile.application.repository.MemberProfileRepository;
import com.self.blog.profile.application.usecase.MemberProfileFindDetailViewUseCase;
import com.self.blog.profile.application.usecase.MemberProfileFindNicknameUseCase;
import com.self.blog.profile.application.usecase.MemberProfileSaveUseCase;
import com.self.blog.profile.application.usecase.VerifyNicknameUseCase;
import com.self.blog.profile.domain.MemberProfile;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileNickname;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberProfileService
        implements
        MemberProfileSaveUseCase,
        VerifyNicknameUseCase,
        MemberProfileFindNicknameUseCase,
        MemberProfileFindDetailViewUseCase
{
    private final MemberProfileRepository memberProfileRepository;

    @ProfileSaveAspect
    @Override
    public MemberProfile save(MemberProfile memberProfile) {

        return memberProfileRepository.save(memberProfile);
    }

    @Override
    public boolean verifyNickname(String nickname) {
        return !memberProfileRepository.existsByNickname(nickname);
    }

    @Override
    public MemberProfileNickname findNickname(String username) {
        return memberProfileRepository.findNicknameByUsername(username)
                .orElseThrow(
                        MemberProfileErrorCode.NO_SUCH_USER::defaultException
                );
    }

    @ProfileFindAspect
    @Override
    public MemberProfileDetailView findDetailView(String username) {
        return memberProfileRepository.findDetailViewByUsername(username)
                .orElseThrow(
                        MemberProfileErrorCode.NO_SUCH_USER::defaultException
                );
    }
}
