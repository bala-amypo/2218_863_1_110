package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resource_allocations")
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
        // Ensure allocatedAt is set even for non-persisted instances (unit tests)
        this.allocatedAt = LocalDateTime.now();
    }

    public ResourceAllocation(Resource resource, ResourceRequest request, Boolean conflictFlag, String notes) {
        this.resource = resource;
        this.request = request;
        this.conflictFlag = conflictFlag;
        this.notes = notes;
        this.allocatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public ResourceRequest getRequest() {
        return request;
    }

    public void setRequest(ResourceRequest request) {
        this.request = request;
    }

    public LocalDateTime getAllocatedAt() {
        return allocatedAt;
    }

    public void setAllocatedAt(LocalDateTime allocatedAt) {
        this.allocatedAt = allocatedAt;
    }

    public Boolean getConflictFlag() {
        return conflictFlag;
    }

    public void setConflictFlag(Boolean conflictFlag) {
        this.conflictFlag = conflictFlag;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
