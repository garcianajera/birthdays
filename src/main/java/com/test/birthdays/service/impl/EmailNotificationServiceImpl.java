package com.test.birthdays.service.impl;

import com.test.birthdays.constants.Constants;
import com.test.birthdays.entity.Friend;
import com.test.birthdays.service.NotificationService;
import org.springframework.stereotype.Service;

@Service(Constants.NOTIFICATION_BY_EMAIL)
public class EmailNotificationServiceImpl extends BaseNotificationService implements NotificationService {

    @Override
    public void sendNotification(Friend friend) {
        String message = getGreetingMessage(friend);
        String subject = "Happy Birthday!";
        sendEmail(friend.getEmail(), subject, message);
    }

    void sendEmail(String email, String subject, String message) {
        // send email
        System.out.println("Email sent to " + email + " with subject: " + subject + " and message: " + message);
    }

}
