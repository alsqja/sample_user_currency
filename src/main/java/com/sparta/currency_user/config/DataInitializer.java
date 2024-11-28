package com.sparta.currency_user.config;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enums.CurrencyStatus;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserCurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final UserCurrencyRepository userCurrencyRepository;

    @PostConstruct
    public void init() {
        Currency dollar = new Currency("Dollar", new BigDecimal("1394.59"), "$");

        currencyRepository.save(dollar);

        Currency yen = new Currency("Yen", new BigDecimal("9.20"), "¥");

        currencyRepository.save(yen);

        Currency invalidCurrency = new Currency("invalid", new BigDecimal("0"), "x");

        currencyRepository.save(invalidCurrency);

        for (int i = 0; i < 30; i++) {
            User user = new User("name" + i, "email" + i + "@email.com");
            userRepository.save(user);
        }

        for (int i = 0; i < 30; i++) {
            int krw = 10000 * (int) (Math.random() * 10 + 1);

            Long currencyId = (long) (int) (Math.random() * 2 + 1);

            if (currencyId.equals((long) 1)) {
                extracted(krw, dollar);
            } else if (currencyId.equals((long) 2)) {
                extracted(krw, yen);
            }
        }
        log.info("===== Test Data Initialized =====");
    }

    private void extracted(int krw, Currency dollar) {
        BigDecimal afterExchange = new BigDecimal(krw).divide(dollar.getExchangeRate(), 2, RoundingMode.HALF_EVEN);
        UserCurrency userCurrency = new UserCurrency(krw, afterExchange, CurrencyStatus.NORMAL);

        Long userId = (long) (int) (Math.random() * 29) + 1;
        User findUser = userRepository.findByIdOrElseThrow(userId);
        userCurrency.setUser(findUser);
        userCurrency.setCurrency(dollar);

        userCurrencyRepository.save(userCurrency);
    }

}
