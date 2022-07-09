package com.gb.reviews.notifier;

import com.gb.reviews.user.User;

public interface UserEmailNotifier {
    NotificationState notifyUser(UserEmailNotification notification);
}
