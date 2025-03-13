package com.test.birthdays.service;

import com.test.birthdays.entity.Friend;

public interface NotificationService {
    void sendNotification(Friend friend);

    String getGreetingMessage(Friend friend);
}
