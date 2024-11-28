package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.TotalCurrencyResDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {
    List<UserCurrency> findByUser(User findUser);

    default UserCurrency findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no currency request having id = " + id));
    }

    @Query("""
        select
            new com.sparta.currency_user.dto.TotalCurrencyResDto(
                            COUNT(uc.id),
                            COALESCE(SUM(uc.amountInKrw), 0)
                        )
        FROM UserCurrency uc
        WHERE uc.user.id = :id
        AND uc.status = 'NORMAL'
   """)
    public TotalCurrencyResDto findTotalCurrency(@Param("id") Long id);
}
