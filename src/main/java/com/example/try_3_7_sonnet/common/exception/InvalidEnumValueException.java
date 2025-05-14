package com.example.try_3_7_sonnet.common.exception;

import lombok.Getter;

@Getter
public class InvalidEnumValueException extends RuntimeException {

    private final String enumType;
    private final String invalidValue;

    public InvalidEnumValueException(String enumType, String invalidValue) {
        super(String.format("無効な%s値です: '%s'", enumType, invalidValue));
        this.enumType = enumType;
        this.invalidValue = invalidValue;
    }
}
