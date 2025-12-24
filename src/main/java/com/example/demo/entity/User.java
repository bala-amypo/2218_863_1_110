package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String password; // Added for Auth
    private String role; // ADMIN or USER
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (role == null) role = "USER";
        createdAt = LocalDateTime.now();
    }
public User(String fullname, String email, String password, String role) {
    this.fullName = fullname;
    this.email = email;
    this.password = password;
    this.role = role;
}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}