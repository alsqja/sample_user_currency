package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.UserCurrency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class UserCurrencyResDto {

    private final Long id;
    private final Long userId;
    private final Long toCurrencyId;
    private final int amountInKrw;
    private final BigDecimal amountAfterExchange;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UserCurrencyResDto(Long id, Long userId, Long toCurrencyId, int amountInKrw, BigDecimal amountAfterExchange, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.toCurrencyId = toCurrencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserCurrencyResDto(UserCurrency userCurrency) {
        this.id = userCurrency.getId();
        this.userId = userCurrency.getUser().getId();
        this.toCurrencyId = userCurrency.getCurrency().getId();
        this.amountInKrw = userCurrency.getAmountInKrw();
        this.amountAfterExchange = userCurrency.getAmountAfterExchange();
        this.status = userCurrency.getStatus().getValue();
        this.createdAt = userCurrency.getCreatedAt();
        this.updatedAt = userCurrency.getUpdatedAt();
    }
}
