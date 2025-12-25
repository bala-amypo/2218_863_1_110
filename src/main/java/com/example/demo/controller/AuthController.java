package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    } 

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {
        User created = userService.registerUser(user);
        return new ApiResponse(true, "User registered successfully", created);
    }
 
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
 
        User user = userService.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
 
        String encodedInput = encode(request.getPassword());
 
        if (!encodedInput.equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
 
        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token);
    }
 
    private String encode(String raw) {
        if (raw == null) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return raw;
        }
    }
}
