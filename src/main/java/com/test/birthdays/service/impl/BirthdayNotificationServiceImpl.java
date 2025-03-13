package com.test.birthdays.service.impl;

import com.test.birthdays.repository.FriendRepository;
import com.test.birthdays.service.BirthdayIdentificationService;
import com.test.birthdays.service.BirthdayNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BirthdayNotificationServiceImpl implements BirthdayNotificationService {

    private final FriendRepository friendRepository;
    private final NotificationStrategyService notificationStrategyService;
    private final BirthdayIdentificationService birthdayIdentificationService;

    @Override
    public void sendBirthDayGreetings() {
        friendRepository.getListOfFriends().forEach(friend -> {
            if (birthdayIdentificationService.isFriendsBirthDay(friend)) {
                notificationStrategyService.sendNotification(friend);
            }
        });

    }
}
