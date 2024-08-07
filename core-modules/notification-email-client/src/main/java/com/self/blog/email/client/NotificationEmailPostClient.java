package com.self.blog.email.client;

import com.self.blog.email.data.NotificationEmail.DirectNotificationEmail;
import com.self.blog.email.data.NotificationEmail.FullInputtedNotificationEmail;
import com.self.blog.email.data.NotificationEmail.TagNotificationEmail;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public sealed interface NotificationEmailPostClient permits NotificationEmailPostClientImpl {
    void sendEmail(TagNotificationEmail email);
    Mono<ResponseEntity<Boolean>> sendEmail(DirectNotificationEmail email);
    void sendEmail(FullInputtedNotificationEmail email);
}
