package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.CreateUserCurrencyReqDto;
import com.sparta.currency_user.dto.UserCurrencyResDto;
import com.sparta.currency_user.service.UserCurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-currency")
@RequiredArgsConstructor
public class UserCurrencyController {

    private final UserCurrencyService userCurrencyService;

    @PostMapping
    public ResponseEntity<UserCurrencyResDto> createUserCurrency(@Valid @RequestBody CreateUserCurrencyReqDto dto) {
        return new ResponseEntity<>(userCurrencyService.save(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserCurrencyResDto> updateUserCurrency(@PathVariable Long id) {
        return new ResponseEntity<>(userCurrencyService.update(id), HttpStatus.OK);
    }
}
