package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.TotalCurrencyResDto;
import com.sparta.currency_user.dto.UserCurrencyResDto;
import com.sparta.currency_user.dto.UserRequestDto;
import com.sparta.currency_user.dto.UserResponseDto;
import com.sparta.currency_user.service.UserCurrencyService;
import com.sparta.currency_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserCurrencyService userCurrencyService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUsers() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.save(userRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/user-currency")
    public ResponseEntity<List<UserCurrencyResDto>> findAllUserCurrency(@PathVariable Long id) {
        return new ResponseEntity<>(userCurrencyService.findAll(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/total-currencies")
    public ResponseEntity<TotalCurrencyResDto> findGroupUserCurrency(@PathVariable Long id) {
        return new ResponseEntity<>(userCurrencyService.findGroupUserCurrency(id), HttpStatus.OK);
    }
}
