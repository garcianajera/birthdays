package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import com.test.birthdays.repository.FriendRepository;
import com.test.birthdays.service.BirthdayIdentificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BirthdayNotificationServiceImplTest {

    @Mock
    private FriendRepository friendRepository;

    @Mock
    private NotificationStrategyService notificationStrategyService;

    @Mock
    private BirthdayIdentificationService birthdayIdentificationService;

    @InjectMocks
    private BirthdayNotificationServiceImpl birthdayNotificationService;


    @Test
    void shouldSendBirthdayGreetingToFriendWhenItIsTheirBirthday() {
        Friend friend = Friend.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@example.com")
                .birthday(LocalDate.of(2020, 1, 1))
                .build();

        when(friendRepository.getListOfFriends()).thenReturn(Collections.singletonList(friend));
        when(birthdayIdentificationService.isFriendsBirthDay(friend)).thenReturn(true);

        birthdayNotificationService.sendBirthDayGreetings();

        verify(notificationStrategyService, times(1)).sendNotification(friend);
    }

    @Test
    void shouldNotSendBirthdayGreetingToFriendWhenItIsNotTheirBirthday() {
        //given
        Friend friend = Friend.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@example.com")
                .birthday(LocalDate.of(2020, 1, 1))
                .build();

        when(friendRepository.getListOfFriends()).thenReturn(Collections.singletonList(friend));
        when(birthdayIdentificationService.isFriendsBirthDay(friend)).thenReturn(false);
        //when

        birthdayNotificationService.sendBirthDayGreetings();
        //then
        verify(notificationStrategyService, never()).sendNotification(friend);
    }

    @Test
    void shouldNotSendAnyNotificationsWhenNoFriendsExist() {
        //given
        when(friendRepository.getListOfFriends()).thenReturn(Collections.emptyList());
        //when
        birthdayNotificationService.sendBirthDayGreetings();
        //then
        verify(notificationStrategyService, never()).sendNotification(any());
        verify(birthdayIdentificationService, never()).isFriendsBirthDay(any());
    }
}