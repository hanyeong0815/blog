package com.self.blog.application.service;

import com.self.blog.application.repository.MemberProfileRepository;
import com.self.blog.application.usecase.MemberProfileSaveUseCase;
import com.self.blog.domain.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberProfileService implements MemberProfileSaveUseCase {
    private final MemberProfileRepository memberProfileRepository;

    @Override
    public MemberProfile save(MemberProfile memberProfile) {

        return memberProfileRepository.save(memberProfile);
    }
}
