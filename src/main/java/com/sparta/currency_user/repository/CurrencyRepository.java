package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    default public Currency findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no currency having id = " + id));
    }
}
