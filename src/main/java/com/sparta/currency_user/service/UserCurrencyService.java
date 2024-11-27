package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.CreateUserCurrencyReqDto;
import com.sparta.currency_user.dto.UserCurrencyResDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enums.CurrencyStatus;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserCurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class UserCurrencyService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final UserCurrencyRepository userCurrencyRepository;

    public UserCurrencyResDto save(CreateUserCurrencyReqDto dto) {
        User findUser = userRepository.findByIdOrElseThrow(dto.getUserId());
        Currency findCurrency = currencyRepository.findByIdOrElseThrow(dto.getCurrencyId());

        int krw = dto.getKrw();
        BigDecimal exchange = new BigDecimal(krw).divide(findCurrency.getExchangeRate(), 2, RoundingMode.HALF_EVEN);

        UserCurrency saveUserCurrency = new UserCurrency(krw, exchange, CurrencyStatus.NORMAL);

        saveUserCurrency.setUser(findUser);
        saveUserCurrency.setCurrency(findCurrency);

        return new UserCurrencyResDto(userCurrencyRepository.save(saveUserCurrency));
    }
}
