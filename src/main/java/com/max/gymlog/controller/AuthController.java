package com.max.gymlog.controller;

import com.max.gymlog.dto.LoginRequest;
import com.max.gymlog.dto.RegisterRequest;
import com.max.gymlog.dto.TokenResponse;
import com.max.gymlog.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register (@Valid @RequestBody RegisterRequest register) {
        authService.register(register);
    }
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest login) {
        return authService.login(login);
    }

}
