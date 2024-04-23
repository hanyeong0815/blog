package com.self.blog.application.service;

import com.self.blog.application.aop.ProfileFindAspect;
import com.self.blog.application.aop.ProfileSaveAspect;
import com.self.blog.application.exception.MemberProfileErrorCode;
import com.self.blog.application.repository.MemberProfileRepository;
import com.self.blog.application.usecase.MemberProfileFindDetailViewUseCase;
import com.self.blog.application.usecase.MemberProfileFindNicknameUseCase;
import com.self.blog.application.usecase.MemberProfileSaveUseCase;
import com.self.blog.domain.MemberProfile;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileNickname;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberProfileService
        implements
        MemberProfileSaveUseCase,
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
