package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    private final long validityInMs;

    // Strict requirement: Specific constructor
    public JwtUtil(String secret, long validityInMs) {
        // If injected via properties, this constructor might need @Value or configuration bean
        // tailored to the test expectation. Assuming usage like:
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMs = validityInMs;
    }

    // Default constructor for Spring if properties are loaded via Config
    public JwtUtil() {
        this("404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970", 86400000);
    }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}