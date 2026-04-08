package com.max.gymlog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TokenResponse {
    @NotBlank
    private String token;
    public TokenResponse(String token) {
        this.token = token;
    }
}
