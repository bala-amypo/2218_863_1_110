package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "allocation_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private String ruleType;

    private Integer priorityWeight;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.priorityWeight == null) {
            this.priorityWeight = 0;
        }
    }
}
