package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import com.test.birthdays.type.NotificationType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class NotificationStrategyServiceTest {

    @Autowired
    private NotificationStrategyService notificationStrategyService;

    @MockitoBean
    private EmailNotificationServiceImpl emailNotificationService;

    @MockitoBean
    private SmsNotificationServiceImpl smsNotificationService;

    @Test
    void testSendNotificationWithEmail() {
        Friend friend = Friend.builder()
                .preferredNotificationType(NotificationType.EMAIL)
                .build();

        doNothing().when(emailNotificationService).sendNotification(friend);

        notificationStrategyService.sendNotification(friend);

        Mockito.verify(emailNotificationService).sendNotification(friend);
        Mockito.verifyNoInteractions(smsNotificationService);
    }

    @Test
    void testSendNotificationWithSms() {
        Friend friend = Friend.builder()
                .preferredNotificationType(NotificationType.SMS)
                .build();

        doNothing().when(smsNotificationService).sendNotification(friend);

        notificationStrategyService.sendNotification(friend);

        Mockito.verify(smsNotificationService).sendNotification(friend);
        Mockito.verifyNoInteractions(emailNotificationService);
    }

    @Test
    void testSendNotificationPreferredTypeNull() {
        Friend friend = Friend.builder()
                .preferredNotificationType(null)
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                notificationStrategyService.sendNotification(friend));

        Mockito.verifyNoInteractions(emailNotificationService, smsNotificationService);
    }
}