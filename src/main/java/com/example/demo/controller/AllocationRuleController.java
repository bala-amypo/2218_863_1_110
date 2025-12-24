package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import com.example.demo.repository.AllocationRuleRepository;
import org.springframework.stereotype.Service;

@Service
public class AllocationRuleService {
    private final AllocationRuleRepository ruleRepository;

    public AllocationRuleService(AllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public AllocationRule createRule(AllocationRule rule) {
        if (ruleRepository.existsByRuleName(rule.getRuleName())) {
            throw new RuntimeException("Rule name exists");
        }
        return ruleRepository.save(rule);
    }
    
    public AllocationRule getRule(Long id) {
        return ruleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rule not found"));
    }
    public java.util.List<AllocationRule> getAllRules() { return ruleRepository.findAll(); }
}