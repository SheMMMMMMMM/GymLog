package com.max.gymlog.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    Map<String, Long> tokens = new ConcurrentHashMap<String, Long>();
    public void save(String token, Long userId) {
        tokens.put(token, userId);
    }
    public Optional<Long> getUserId(String token) {
        if (token == null) return Optional.empty();
        return Optional.ofNullable(tokens.get(token));
    }
}
