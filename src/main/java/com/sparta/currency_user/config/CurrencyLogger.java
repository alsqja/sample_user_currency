package com.sparta.currency_user.config;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@DependsOn("dataInitializer")
public class CurrencyLogger {

    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        List<Currency> currencies = currencyRepository.findAll();

        for (Currency c : currencies) {
            if (c.getExchangeRate().compareTo(new BigDecimal("0")) <= 0) {
                log.info("invalid exchangeRate is recorded where id = {}, name = {}, exchangeRate = {}", c.getId(), c.getCurrencyName(), c.getExchangeRate());
            }
        }

        log.info("Complete Currency Logging");
    }
}
