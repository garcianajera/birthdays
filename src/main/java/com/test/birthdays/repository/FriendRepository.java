package com.test.birthdays.repository;

import com.test.birthdays.entity.Friend;
import com.test.birthdays.type.NotificationType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

@Component
public class FriendRepository {
    private final List<Friend> friends = List.of(
            Friend.builder().lastName("Doe").firstName("John").birthday(LocalDate.of(1990, 1, 1)).email("john@email.com").preferredNotificationType(NotificationType.SMS).build(),
            Friend.builder().lastName("Doe").firstName("Jane").birthday(LocalDate.of(1990, 2, 1)).email("jane@email.com").preferredNotificationType(NotificationType.EMAIL).build()
    );

    public List<Friend> getListOfFriends() {
        return friends;
    }

    private List<Friend> getFriendsByBirthdayMonth(Month month) {
        return friends.stream().filter(friend -> friend.getBirthday().getMonth().equals(month)).toList();
    }

    public List<Friend> getFriendsByBirthdayMonthSortedByDayAsc(Month month) {
        var listOfFriendsByMonth = getFriendsByBirthdayMonth(month);
        listOfFriendsByMonth.sort(Comparator.comparingInt(f -> f.getBirthday().getDayOfMonth()));
        return listOfFriendsByMonth;
    }
}
