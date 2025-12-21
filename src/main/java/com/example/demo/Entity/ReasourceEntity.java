package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "resources",
    uniqueConstraints = @UniqueConstraint(columnNames = "resourceName")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String resourceName;

    @Column(nullable = false)
    private String resourceType;

    @Column(nullable = false)
    private Integer capacity;

    private String location;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "resource")
    private List<ResourceAllocation> allocations;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
