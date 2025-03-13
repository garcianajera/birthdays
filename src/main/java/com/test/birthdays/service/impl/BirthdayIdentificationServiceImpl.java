package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import com.test.birthdays.service.BirthdayIdentificationService;
import com.test.birthdays.service.DateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class BirthdayIdentificationServiceImpl implements BirthdayIdentificationService {

    private final DateProviderService dateProviderService;

    @Override
    public boolean isFriendsBirthDay(Friend friend) {
        LocalDate currentDate = dateProviderService.getCurrentDate();
        var currentMoth = currentDate.getMonth();
        var currentDay = currentDate.getDayOfMonth();
        if (shouldSendNotificationForLeapYear(currentDate.isLeapYear(), currentMoth, currentDay) && isFriendsBirthdayOnLeapYear(friend)) {
            return true;
        }
        return friend.getBirthday().getMonth().equals(currentMoth) && friend.getBirthday().getDayOfMonth() == currentDay;
    }

    private boolean shouldSendNotificationForLeapYear(boolean isLeapYear, Month currentMoth, int currentDay) {
        return !isLeapYear && currentMoth.equals(Month.FEBRUARY) && currentDay == 28;
    }

    private boolean isFriendsBirthdayOnLeapYear(Friend friend) {
        return friend.getBirthday().getMonth().equals(Month.FEBRUARY) && friend.getBirthday().getDayOfMonth() == 29;
    }

}
