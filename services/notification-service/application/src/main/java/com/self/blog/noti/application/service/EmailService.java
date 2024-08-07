package com.self.blog.noti.application.service;

import com.self.blog.email.data.NotificationEmail.DirectNotificationEmail;
import com.self.blog.email.data.NotificationEmail.FullInputtedNotificationEmail;
import com.self.blog.email.data.NotificationEmail.TagNotificationEmail;
import com.self.blog.email.exception.EmailPostErrorCode;
import com.self.blog.email.exception.EmailPostException;
import com.self.blog.noti.application.usecase.NotificationEmailPostUseCase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements NotificationEmailPostUseCase {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(TagNotificationEmail email) {
        throw new Error("TagNotification은 아직 지원하지 않습니다.");
    }

    @Override
    public void sendEmail(DirectNotificationEmail email) throws EmailPostException {
        MimeMessage mailStructure = javaMailSender.createMimeMessage();
        MimeMessageHelper mailHelper = new MimeMessageHelper(mailStructure, "UTF-8");

        try {
            mailHelper.setTo(email.targets());
            mailHelper.setSubject(email.title());
            mailHelper.setText(email.content(), email.useHtml());
        } catch (MessagingException e) {
            throw new EmailPostException(EmailPostErrorCode.MESSAGING_EXCEPTION);
        }

        javaMailSender.send(mailStructure);
    }

    @Override
    public void sendEmail(FullInputtedNotificationEmail email) {
        throw new Error("FullInputtednotification은 아직 지원하지 않습니다.");
    }
}
