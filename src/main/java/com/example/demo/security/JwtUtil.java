package com.example.demo.security;



import io.jsonwebtoken.*;

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

        .setIssuedAt(new Date())

        .setExpiration(new Date(System.currentTimeMillis() + validityInMs))

        .signWith(SignatureAlgorithm.HS256, secret)

        .compact();

    }



    public Claims parseClaims(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }

}
