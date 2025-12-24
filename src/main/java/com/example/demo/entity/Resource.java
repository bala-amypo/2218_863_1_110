package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "resourceName"))
public class Resource {

    @Id @GeneratedValue
    private Long id;

    private String resourceName;
    private String resourceType;
    private Integer capacity;
    private String location;
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
