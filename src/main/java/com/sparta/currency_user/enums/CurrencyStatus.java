package com.sparta.currency_user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum CurrencyStatus {
    NORMAL("normal"),
    CANCELLED("cancelled");

    private final String value;

    CurrencyStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CurrencyStatus fromValue(String value) {
        for (CurrencyStatus status : CurrencyStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 타입 입니다.");
    }
}
