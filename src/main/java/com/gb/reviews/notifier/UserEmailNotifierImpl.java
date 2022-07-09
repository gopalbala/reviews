package com.gb.reviews.notifier;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserEmailNotifierImpl implements UserEmailNotifier {

    @Override
    public NotificationState notifyUser(UserEmailNotification notification) {
        //make an effort to send email asynchronously
        return NotificationState.SUCCESS;
    }
}
