package com.example.demo.repository;

import com.example.demo.entity.AllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRuleRepository extends JpaRepository<AllocationRule, Long> {
    boolean existsByRuleName(String ruleName);
}
