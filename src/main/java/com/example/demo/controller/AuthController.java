package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService) {
        this.userService = userService;
        this.jwtUtil = new JwtUtil("secretkeysecretkeysecretkey", 3600000);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User user = userService.getAllUsers().stream()
            .filter(u -> u.getEmail().equals(req.getEmail()))
            .findFirst()
            .orElseThrow();

        String token =
            jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

        return new AuthResponse(token);
    }
}
