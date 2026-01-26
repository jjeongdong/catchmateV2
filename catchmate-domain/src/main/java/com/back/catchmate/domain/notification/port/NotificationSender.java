package com.back.catchmate.domain.notification.port;

public interface NotificationSender {
    void sendNotification(String fcmToken, String title, String body);
}
