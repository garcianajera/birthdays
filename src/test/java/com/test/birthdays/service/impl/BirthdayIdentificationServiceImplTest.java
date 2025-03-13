package com.test.birthdays.service.impl;

import com.test.birthdays.entity.Friend;
import com.test.birthdays.service.BirthdayIdentificationService;
import com.test.birthdays.service.DateProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BirthdayIdentificationServiceImplTest {

    @MockitoBean
    private DateProviderService dateProviderService;

    private BirthdayIdentificationService birthdayIdentificationService;

    @BeforeEach
    void setUp() {
        birthdayIdentificationService = new BirthdayIdentificationServiceImpl(dateProviderService);
    }

    @ParameterizedTest
    @DisplayName("Should correctly identify birthday scenarios")
    @CsvSource({
            // friendYear, friendMonth, friendDay, currentYear, currentMonth, currentDay, expectedResult
            "1982, 10, 8, 1982, 10, 8, true",     // same month year, and day
            "1982, 10, 8, 2000, 10, 8, true",     // same month and day
            "1982, 10, 8, 2000, 10, 9, false",    // same month different day
            "2025, 10, 8, 2025, 10, 9, false",     // same year, month and different day
            "2020, 2, 29, 2021, 2, 28, true",     // non-leap year February case
            "2020, 2, 29, 2024, 2, 29, true",     // leap year February case
            "2020, 2, 29, 2024, 2, 28, false"     // current year is leap should not send notification on Feb 28th
    })
    void testBirthdayIdentification(
            int friendYear, int friendMonth, int friendDay,
            int currentYear, int currentMonth, int currentDay,
            boolean expected) {

        // given
        Friend friend = createTestFriend(friendYear, friendMonth, friendDay);
        mockCurrentDate(currentYear, currentMonth, currentDay);

        // when
        boolean result = birthdayIdentificationService.isFriendsBirthDay(friend);

        // then
        assertEquals(expected, result);
    }

    private Friend createTestFriend(int year, int month, int day) {
        return Friend.builder()
                .firstName("John")
                .lastName("Dow")
                .birthday(LocalDate.of(year, month, day))
                .email("juan@email.com")
                .build();
    }

    private void mockCurrentDate(int year, int month, int day) {
        when(dateProviderService.getCurrentDate())
                .thenReturn(LocalDate.of(year, month, day));
    }
}