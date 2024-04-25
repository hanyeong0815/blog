package com.self.blog.profile.internal.server;

import com.self.blog.profile.internal.service.MemberProfileSaveProxyService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberProfileServerRunner implements ApplicationRunner {
    private final MemberProfileSaveProxyService memberProfileSaveProxyService;

    private static final int PORT = 8091;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(memberProfileSaveProxyService)
                .build();

        server.start();
        server.awaitTermination();
    }
}
