package com.example.try_3_7_sonnet.common.service.impl;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTimeService {

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
