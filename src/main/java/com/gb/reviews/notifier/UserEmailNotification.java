package com.gb.reviews.notifier;

import com.gb.reviews.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserEmailNotification {
    private long emailNotificationId;
    private String userId;
    private String id;
    private String message;
    private String subject;
    private Instant notifiedOn;
    private NotificationState notificationState;

    public UserEmailNotification() {}

    public UserEmailNotification(String userId, String message,
                                 String subject, Instant notifiedOn) {
        this.emailNotificationId = Utils.getRandomLong();
        this.userId = userId;
        this.message = message;
        this.subject = subject;
        this.notifiedOn = notifiedOn;
    }
}
