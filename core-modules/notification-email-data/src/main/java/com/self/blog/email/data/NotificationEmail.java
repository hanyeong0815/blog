package com.self.blog.email.data;

import com.self.blog.email.exception.EmailPostErrorCode;
import lombok.AccessLevel;
import lombok.Builder;

public record NotificationEmail() {
    @Builder
    public record TagNotificationEmail(
            String title,
            String content,
            Boolean useHtml,
            String[] tag
    ) {
        public TagNotificationEmail {
            if (tag == null || tag.length == 0) {
                throw EmailPostErrorCode.TAG_REQUIRED.defaultException();
            }
        }

    }
    @Builder(access = AccessLevel.PUBLIC)
    public record DirectNotificationEmail(
            String title,
            String content,
            Boolean useHtml,
            String[] targets
    ) {
        public DirectNotificationEmail {
            if (targets == null || targets.length == 0) {
                throw EmailPostErrorCode.TAG_REQUIRED.defaultException();
            }

            if (useHtml == null) {
                useHtml = false;
            }
        }
    }

    @Builder
    public record FullInputtedNotificationEmail(
            String title,
            String content,
            Boolean useHtml,
            String[] targetList,
            String[] ccAddressList,
            String[] bccAddressList,
            EmailAttachedFile[] attachedFiles
    ) {}
}
