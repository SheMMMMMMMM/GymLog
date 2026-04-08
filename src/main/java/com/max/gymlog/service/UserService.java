package com.max.gymlog.service;

import com.max.gymlog.dto.RegisterRequest;
import com.max.gymlog.dto.UpdateUserRequest;
import com.max.gymlog.entity.Role;
import com.max.gymlog.entity.User;
import com.max.gymlog.exception.EmailAlreadyExistsException;
import com.max.gymlog.exception.UserNotFoundException;
import com.max.gymlog.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User create(RegisterRequest req){
        if(userRepository.existsByEmail(req.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        String hash = passwordEncoder.encode(req.getPassword());
        User user = new User(req.getUsername(), req.getEmail(), hash, Role.USER);
        userRepository.save(user);
        return user;
    }
    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateById(Long id, @Valid UpdateUserRequest req){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if(userRepository.existsByEmail(req.getEmail()) && !user.getEmail().equals(req.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());

        return userRepository.save(user);
    }

    public void deleteById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException( "User not found"));

        userRepository.delete(user);
    }


}
