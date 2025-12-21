package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resource_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String resourceType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User requestedBy;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(nullable = false)
    private String purpose;

    private String status;

    @OneToOne(mappedBy = "request")
    private ResourceAllocation allocation;

    @PrePersist
    public void onCreate() {
        if (this.status == null) {
            this.status = "PENDING";
        }
    }
}
