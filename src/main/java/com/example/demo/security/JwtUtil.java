package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
public class JwtUtil {

    private final String secret;
    private final long validityInMs;

    public JwtUtil(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
            .claim("id", userId)
            .claim("email", email)
            .claim("role", role)
            .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
            .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secret.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
