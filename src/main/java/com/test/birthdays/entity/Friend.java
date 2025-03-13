package com.test.birthdays.entity;

import com.test.birthdays.type.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Friend {

    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private String email;
    private NotificationType preferredNotificationType;

}
