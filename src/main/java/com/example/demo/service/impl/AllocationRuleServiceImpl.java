package com.example.demo.service.impl;

import com.example.demo.entity.AllocationRule;
import com.example.demo.repository.AllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AllocationRuleRepository ruleRepository;

    // Strict Constructor Injection
    public AllocationRuleServiceImpl(AllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public AllocationRule createRule(AllocationRule rule) {
        if (ruleRepository.existsByRuleName(rule.getRuleName())) {
            throw new RuntimeException("Rule name exists");
        }
        return ruleRepository.save(rule);
    }

    @Override
    public AllocationRule getRule(Long id) {
        return ruleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @Override
    public List<AllocationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}