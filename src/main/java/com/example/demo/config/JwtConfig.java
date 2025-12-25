package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${security.jwt.secret:test-secret-key-that-is-long-enough-1234}")
    private String secret;

    @Value("${security.jwt.validity-ms:3600000}")
    private long validityMs;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret, validityMs);
    }
}
