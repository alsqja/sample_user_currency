package com.sparta.currency_user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserCurrencyReqDto {

    @NotNull(message = "krw is required")
    private final int krw;

    @NotNull(message = "currencyId is required")
    private final Long currencyId;

    @NotNull(message = "userId is required")
    private final Long userId;

    public CreateUserCurrencyReqDto(int krw, Long currencyId, Long userId) {
        this.krw = krw;
        this.currencyId = currencyId;
        this.userId = userId;
    }
}
