package com.self.blog.member.application.config;

import com.self.blog.profile.lib.MemberProfileInterfaceGrpc;
import com.self.blog.profile.lib.MemberProfileInterfaceGrpc.MemberProfileInterfaceBlockingStub;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcStubConfig {
    @Bean
    public MemberProfileInterfaceBlockingStub memberProfileInterfaceBlockingStub() {
        return MemberProfileInterfaceGrpc
                .newBlockingStub(
                        ManagedChannelBuilder
                                .forAddress("localhost", 8091)
                                .usePlaintext().build()
                );
    }
}
