package com.self.blog.email.client;

import com.self.blog.email.data.NotificationEmail.DirectNotificationEmail;
import com.self.blog.email.data.NotificationEmail.FullInputtedNotificationEmail;
import com.self.blog.email.data.NotificationEmail.TagNotificationEmail;
import com.self.blog.email.properties.NotificationUrlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public final class NotificationEmailPostClientImpl implements NotificationEmailPostClient {
    private final WebClient webClient;

    public NotificationEmailPostClientImpl(NotificationUrlProperties notificationUrlProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(notificationUrlProperties.notiUrl())
                .build();
    }

    private static final String URI = "/notification/email";

    @Override
    public void sendEmail(TagNotificationEmail email) {
        throw new Error("미지원");
    }

    @Override
    public Mono<ResponseEntity<Boolean>> sendEmail(DirectNotificationEmail email) {
        return webClient.post()
                .uri(URI)
                .body(Mono.just(email), DirectNotificationEmail.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body(false));
    }

    @Override
    public void sendEmail(FullInputtedNotificationEmail email) {
        throw new Error("미지원");
    }
}
