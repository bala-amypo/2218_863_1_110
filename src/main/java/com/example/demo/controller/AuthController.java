package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User user) {
        // Prompt calls for user registration data to map to userService.registerUser
        User created = userService.registerUser(user);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", created));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) { 
        User user = userService.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(request.getEmail())) /
                .findFirst()
                .orElse(null);  
        .orElseThrow(() -> new IllegalArgumentException("User not found")); // Or 
        
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");similar
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
    }
}
