package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = userRepo.findAll().stream()
            .filter(u -> u.getEmail().equals(email))
            .findFirst()
            .orElseThrow();

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            "",
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
