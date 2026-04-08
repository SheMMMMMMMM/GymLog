package com.max.gymlog.service;

import com.max.gymlog.dto.LoginRequest;
import com.max.gymlog.dto.RegisterRequest;
import com.max.gymlog.dto.TokenResponse;
import com.max.gymlog.entity.Role;
import com.max.gymlog.entity.User;
import com.max.gymlog.exception.EmailAlreadyExistsException;
import com.max.gymlog.exception.UserNotFoundException;
import com.max.gymlog.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public void register(@Valid RegisterRequest req) {
        if(userRepository.existsByEmail(req.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        String hash = passwordEncoder.encode(req.getPassword());
        User user = new User(req.getUsername(), req.getEmail(), hash, Role.USER);
        userRepository.save(user);
    }
    public TokenResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid credentials"));
        boolean passwordMatches = passwordEncoder.matches(req.getPassword(), user.getPasswordHash());
        if(!passwordMatches){
            throw new UserNotFoundException("Invalid credentials");
        }
        String token = UUID.randomUUID().toString();
        tokenService.save(token, user.getId());
        return new TokenResponse(token);
    }
}
