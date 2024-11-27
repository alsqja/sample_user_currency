package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "check your email format")
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
