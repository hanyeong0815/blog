package com.self.blog.application.aop;

import com.self.blog.application.repository.MemberProfileRepository;
import com.self.blog.application.repository.ProfileLogRepository;
import com.self.blog.common.utils.time.ServerTime;
import com.self.blog.domain.ProfileLog;
import com.self.blog.domain.type.ProfileLogType;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class ProfileLogAspect {
    private final ProfileLogRepository profileLogRepository;
    private final MemberProfileRepository memberProfileRepository;

    private final ServerTime serverTime;

    @AfterReturning(value = "@annotation(ProfileFindAspect)", returning = "memberProfileDetailView")
    public void saveProfileLogVerify(MemberProfileDetailView memberProfileDetailView) {
        UUID memberId = memberProfileRepository.findMemberIdByUsername(memberProfileDetailView.username());

        ProfileLog profileLog = ProfileLog.builder()
                .memberId(memberId)
                .username(memberProfileDetailView.username())
                .logType(ProfileLogType.VERIFY)
                .remark(ProfileLogType.VERIFY.remark)
                .createdAt(serverTime.nowInstant())
                .build();

        ProfileLog savedProfileLog = profileLogRepository.save(profileLog);
        System.out.println(savedProfileLog);
    }
}
