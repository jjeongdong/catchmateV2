package com.back.catchmate.infrastructure.notification.sender;

import com.back.catchmate.domain.notification.port.NotificationSender;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FcmNotificationSender implements NotificationSender {

    @Override
    public void sendNotification(String fcmToken, String title, String body) {
        // 1. 토큰 유효성 검사
        if (fcmToken == null || fcmToken.isBlank()) {
            log.warn("FCM Token is empty. Notification skipped.");
            return;
        }

        try {
            // 2. 알림 내용 구성 (제목, 본문)
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            // 3. 메시지 객체 생성 (누구에게 + 무엇을)
            Message message = Message.builder()
                    .setToken(fcmToken)
                    .setNotification(notification)
                    .build();

            // 4. 발송 (Firebase SDK 사용)
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("FCM Notification sent successfully. Token: {}, Response: {}", fcmToken, response);
        } catch (Exception e) {
            log.error("Failed to send FCM notification. Token: {}, Error: {}", fcmToken, e.getMessage());
        }
    }
}
