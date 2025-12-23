package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resources")
@Getter
@Setter
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String resourceName;

    @Column(nullable = false)
    private String resourceType;

    @Column(nullable = false)
    private Integer capacity;

    private String location;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Resource() {
    }

    public Resource(String resourceName, String resourceType, Integer capacity, String location) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.capacity = capacity;
        this.location = location;
    }
}
