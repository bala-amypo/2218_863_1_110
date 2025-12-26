package com.example.demo.dto;

public class AuthResponse {

    private String token;
    private Long userId;
    private String username;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, Long userId, String username, String role) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // getters and setters
}
