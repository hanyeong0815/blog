package com.self.blog.noti.internal.controller;

import com.self.blog.email.data.NotificationEmail.DirectNotificationEmail;
import com.self.blog.noti.application.usecase.NotificationEmailPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
public class NotificationEmailApi {
    private final NotificationEmailPostUseCase notificationEmailPostUseCase;

    @PostMapping("/email")
    public ResponseEntity<Boolean> mailSend(@RequestBody DirectNotificationEmail email) {
        notificationEmailPostUseCase.sendEmail(email);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/test")
    public ResponseEntity<Void> test() {
        notificationEmailPostUseCase.sendEmail(
                DirectNotificationEmail.builder()
                        .title("테스트 메일")
                        .content("테스트 코드: 123456")
                        .targets(new String[] {
                                "kodong0815@naver.com"
                        })
                        .useHtml(true)
                        .build()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public String healthChecker() {
        return "생존";
    }
}
