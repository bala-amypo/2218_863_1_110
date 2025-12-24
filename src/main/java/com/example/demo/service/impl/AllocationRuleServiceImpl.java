package com.example.demo.service.impl;

import com.example.demo.entity.AllocationRule;
import com.example.demo.repository.AllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AllocationRuleRepository repo;

    public AllocationRuleServiceImpl(AllocationRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public AllocationRule createRule(AllocationRule rule) {
        if (repo.existsByRuleName(rule.getRuleName())) {
            throw new RuntimeException("rule exists");
        }
        return repo.save(rule);
    }

    @Override
    public AllocationRule getRule(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public List<AllocationRule> getAllRules() {
        return repo.findAll();
    }
}
