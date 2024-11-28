package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class TotalCurrencyResDto {

    private final Long count;
    private final BigDecimal totalAmountInKrw;

    public TotalCurrencyResDto(Long count, Number totalAmountInKrw) {
        this.count = count;
        this.totalAmountInKrw = totalAmountInKrw instanceof BigDecimal
                ? ((BigDecimal) totalAmountInKrw).setScale(2, RoundingMode.HALF_EVEN)
                : BigDecimal.valueOf(totalAmountInKrw.doubleValue()).setScale(2, RoundingMode.HALF_EVEN);
    }
}
