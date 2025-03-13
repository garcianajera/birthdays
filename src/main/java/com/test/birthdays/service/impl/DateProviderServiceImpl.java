package com.test.birthdays.service.impl;

import com.test.birthdays.service.DateProviderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateProviderServiceImpl implements DateProviderService {

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
