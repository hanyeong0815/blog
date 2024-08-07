package com.self.blog.noti.application.usecase;

import com.self.blog.email.data.NotificationEmail.DirectNotificationEmail;
import com.self.blog.email.data.NotificationEmail.FullInputtedNotificationEmail;
import com.self.blog.email.data.NotificationEmail.TagNotificationEmail;
import com.self.blog.email.exception.EmailPostException;

public interface NotificationEmailPostUseCase {
    void sendEmail(TagNotificationEmail email);
    void sendEmail(DirectNotificationEmail email) throws EmailPostException;
    void sendEmail(FullInputtedNotificationEmail email);
}
