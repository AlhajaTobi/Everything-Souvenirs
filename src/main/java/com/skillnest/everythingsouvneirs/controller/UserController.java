package com.skillnest.everythingsouvneirs.controller;

import com.skillnest.everythingsouvneirs.data.model.User;
import com.skillnest.everythingsouvneirs.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User registered successfully");
            response.put("userId", createdUser.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}