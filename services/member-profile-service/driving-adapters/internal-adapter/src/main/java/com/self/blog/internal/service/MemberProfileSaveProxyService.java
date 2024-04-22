package com.self.blog.internal.service;

import com.self.blog.application.usecase.MemberProfileSaveUseCase;
import com.self.blog.domain.MemberProfile;
import com.self.blog.internal.mapper.MemberProfileDtoMapper;
import com.self.blog.profile.lib.MemberProfileDetailResponse;
import com.self.blog.profile.lib.MemberProfileInterfaceGrpc.MemberProfileInterfaceImplBase;
import com.self.blog.profile.lib.MemberProfileSaveRequest;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class MemberProfileSaveProxyService extends MemberProfileInterfaceImplBase {
    private final MemberProfileSaveUseCase memberProfileSaveUseCase;
    private final MemberProfileDtoMapper mapper;

    @Override
    public void save(MemberProfileSaveRequest request, StreamObserver<MemberProfileDetailResponse> responseObserver) {
        MemberProfile memberProfile = mapper.from(request);

        MemberProfile savedMemberProfile = memberProfileSaveUseCase.save(memberProfile);

        MemberProfileDetailResponse response = mapper.from(savedMemberProfile);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
