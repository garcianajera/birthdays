package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import com.test.birthdays.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationServiceImpl extends BaseNotificationService implements NotificationService {

    @Override
    public void sendNotification(Friend friend) {
        sendSms(getGreetingMessage(friend));
    }

    void sendSms(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
