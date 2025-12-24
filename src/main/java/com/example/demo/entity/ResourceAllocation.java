package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ResourceAllocation {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Resource resource;

    @OneToOne
    private ResourceRequest request;

    private LocalDateTime allocatedAt;
    private Boolean conflictFlag;
    private String notes;

    @PrePersist
    void onCreate() {
        allocatedAt = LocalDateTime.now();
    }
}
