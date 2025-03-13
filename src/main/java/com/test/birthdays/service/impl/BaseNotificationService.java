package com.test.birthdays.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.test.birthdays.entity.Friend;
import com.test.birthdays.service.NotificationService;

public abstract class BaseNotificationService implements NotificationService {

    @Override
    public String getGreetingMessage(Friend friend) {
        if (StringUtil.isNullOrEmpty(friend.getFirstName())) {
            return "Happy birthday!";
        }
        return "Happy birthday, dear " + friend.getFirstName() + "!";
    }
}
