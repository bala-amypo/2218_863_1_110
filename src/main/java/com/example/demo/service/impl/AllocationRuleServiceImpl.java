package com.example.demo.service.impl;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AllocationRuleRepository ruleRepository;

    public AllocationRuleServiceImpl(AllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public AllocationRule createRule(AllocationRule rule) {
        if (ruleRepository.existsByRuleName(rule.getRuleName())) {
            throw new IllegalArgumentException("AllocationRule exists with name: " + rule.getRuleName());
        }
        return ruleRepository.save(rule);
    }

    @Override
    public AllocationRule getRule(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AllocationRule not found with id: " + id));
    }

    @Override
    public List<AllocationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
