package com.max.gymlog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class LoginRequest {
    @NotBlank(message = "email can't be empty")
    private String email;

    @NotBlank(message = "password can't be empty & length > 6")
    @Size(min = 6)
    private String password;


}
