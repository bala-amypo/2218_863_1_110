package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "allocation_rules")
@Getter
@Setter
public class AllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    private String ruleType; // "FIRSTAVAILABLE", "PRIORITYBASED", "ROUNDROBIN"

    @Column(nullable = false)
    private Integer priorityWeight;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public AllocationRule() {
    }

    public AllocationRule(String ruleName, String ruleType, Integer priorityWeight) {
        this.ruleName = ruleName;
        this.ruleType = ruleType;
        this.priorityWeight = priorityWeight;
    }
}
