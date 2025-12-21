package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resource_allocations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    public void onAllocate() {
        this.allocatedAt = LocalDateTime.now();
    }
}
