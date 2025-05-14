package com.example.try_gpt4_1.common.service.impl;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTimeService {

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
