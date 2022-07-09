package com.gb.reviews.notifier;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserEmailNotification implements UserEmailNotifier {
    private Long emailNotificationId;
    private String userId;
    private String id;
    private String message;
    private String subject;
    private Instant notifiedOn;
    private NotificationState notificationState;
    @Override
    public NotificationState notifyUser(UserEmailNotification notification) {
        //make an effort to send email asynchronously
        return NotificationState.SUCCESS;
    }
}
