package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import com.test.birthdays.service.NotificationService;
import com.test.birthdays.type.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationStrategyService {
    private final Map<NotificationType, NotificationService> notificationStrategies;

    @Autowired
    public NotificationStrategyService(EmailNotificationServiceImpl emailNotificationService,
                                       SmsNotificationServiceImpl smsNotificationService) {
        this.notificationStrategies = Map.of(
                NotificationType.EMAIL, emailNotificationService,
                NotificationType.SMS, smsNotificationService
        );
    }

    public void sendNotification(Friend friend) {
        NotificationType preferredType = friend.getPreferredNotificationType();
        if (preferredType == null) {
            throw new IllegalArgumentException("preferredType should not be null " );
        }

        NotificationService notificationService = notificationStrategies.get(preferredType);

        if (notificationService == null) {
            throw new IllegalArgumentException("Unsupported notification type: " + preferredType);
        }

        notificationService.sendNotification(friend);
    }
}