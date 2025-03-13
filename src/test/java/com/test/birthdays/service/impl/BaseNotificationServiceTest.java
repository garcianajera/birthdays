package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseNotificationServiceTest {

    private BaseNotificationService baseNotificationService ;

    @BeforeEach
    void setUp() {
        baseNotificationService = new BaseNotificationService() {
            @Override
            public void sendNotification(Friend friend) {
                // do nothing
            }
        };
    }

    @Test
    void getGreetingMessage_ShouldReturnExpectedGreeting() {
        // given
        Friend friend = Friend.builder()
                .firstName("John").build();

        // when
        String result = baseNotificationService.getGreetingMessage(friend);

        // Assert
        assertEquals("Happy birthday, dear John!", result);
    }
    @Test
    void getGreetingMessage_firstNameNull_ShouldReturnGenericGreeting() {
        // given
        Friend friend = Friend.builder().build();

        // when
        String result = baseNotificationService.getGreetingMessage(friend);

        // Assert
        assertEquals("Happy birthday!", result);
    }
}