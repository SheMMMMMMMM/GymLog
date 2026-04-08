package com.max.gymlog.controller;

import com.max.gymlog.dto.RegisterRequest;
import com.max.gymlog.dto.UpdateUserRequest;
import com.max.gymlog.dto.UserResponse;
import com.max.gymlog.entity.User;
import com.max.gymlog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // це точка входу HTTP-запитів у backend.
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users)
            userResponses.add(new UserResponse(user.getId(),
                    user.getUsername(),
                    user.getEmail())
            );
        return userResponses;
    }

    // CRUD

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED) // треба показати статус 201 шо це створення
    public UserResponse createUser(@Valid @RequestBody RegisterRequest req) {
        User user = userService.create(req);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    // get by ID
    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateById(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest req) {
        User user = userService.updateById(id, req);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
    // DELETE BY ID
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }





    //    @GetMapping("/hello") // Якщо прийде HTTP GET запит на URL /hello — виконай цей метод
//    public String hello() {
//        return "Hello World";
//    }

}
