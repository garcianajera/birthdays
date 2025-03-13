package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SmsNotificationServiceImplTest {
    @Spy
    private SmsNotificationServiceImpl smsNotificationService;

    @Test
    void sendNotification() {
        var friend = Friend.builder()
                .lastName("Doe")
                .firstName("John")
                .birthday(LocalDate.of(1990, 1, 1))
                .email("juan@email.com")
                .build();

        smsNotificationService.sendNotification(friend);
        verify(smsNotificationService, Mockito.times(1))
                .sendSms("Happy birthday, dear John!");
    }
}