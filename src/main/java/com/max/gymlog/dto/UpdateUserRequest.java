package com.max.gymlog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    @NotBlank(message = "username can't be empty")
    private String username;
    @NotBlank(message = "email can't be empty")
    private String email;

    public UpdateUserRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
