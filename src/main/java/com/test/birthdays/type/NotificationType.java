package com.test.birthdays.type;

import lombok.Getter;

@Getter
public enum NotificationType {
    EMAIL("email"),
    SMS("sms");
    private final String value;

    NotificationType(String value) {
        this.value = value;
    }
}
