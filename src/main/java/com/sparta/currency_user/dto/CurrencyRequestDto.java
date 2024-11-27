package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {
    @NotNull(message = "currencyName is required")
    private String currencyName;

    @NotNull(message = "exchangeRate is required")
    @Min(value = 0, message = "exchangeRate has to larger than 0")
    private BigDecimal exchangeRate;

    @NotNull(message = "symbol is required")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
