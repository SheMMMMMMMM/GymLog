package com.max.gymlog.dto;

import lombok.Getter;

@Getter
public class UserResponse {
    private final Long id;
    private final String username;
    private final String email;

    public UserResponse(Long id, String username, String email) {
        this.id = id;
        this.email = email;
        this.username = username;
    }
}