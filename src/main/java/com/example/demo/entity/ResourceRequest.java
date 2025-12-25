package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resource_requests")
@Getter
@Setter
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User requestedBy;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String purpose;

    private String status; // "PENDING", "APPROVED", "REJECTED"

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    public ResourceRequest() {
        // Default status for unit tests without persistence lifecycle
        this.status = "PENDING";
    }

    public ResourceRequest(String resourceType, User requestedBy, LocalDateTime startTime, LocalDateTime endTime,
            String purpose, String status) {
        this.resourceType = resourceType;
        this.requestedBy = requestedBy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
        this.status = status;
    }
}
