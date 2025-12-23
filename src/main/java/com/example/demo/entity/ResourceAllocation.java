package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resource_allocations")
@Getter
@Setter
public class ResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @OneToOne
    @JoinColumn(name = "request_id", unique = true, nullable = false)
    private ResourceRequest request;

    private LocalDateTime allocatedAt;

    private Boolean conflictFlag;

    private String notes;

    @PrePersist
    protected void onCreate() {
        this.allocatedAt = LocalDateTime.now();
    }

    public ResourceAllocation() {
    }

    public ResourceAllocation(Resource resource, ResourceRequest request, Boolean conflictFlag, String notes) {
        this.resource = resource;
        this.request = request;
        this.conflictFlag = conflictFlag;
        this.notes = notes;
    }
}
