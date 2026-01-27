package com.back.catchmate.domain.notification.port;

import java.util.List;
import java.util.Map;

public interface NotificationSender {
    void sendNotification(String token, String title, String body, Map<String, String> data);
    void sendMulticastNotification(List<String> tokens, String title, String body, Map<String, String> data);
}
