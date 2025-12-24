package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class AllocationRule {

    @Id @GeneratedValue
    private Long id;

    private String ruleName;
    private String ruleType;
    private Integer priorityWeight;
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
