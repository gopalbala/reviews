package com.gb.reviews.notifier;

public interface UserEmailNotifier {
    NotificationState notifyUser(UserEmailNotification notification);
}
