package com.example.demo.controller;

import com.example.demo.entity.AllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleService service;

    public AllocationRuleController(AllocationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public AllocationRule create(@RequestBody AllocationRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<AllocationRule> all() {
        return service.getAllRules();
    }

    @GetMapping("/{id}")
    public AllocationRule get(@PathVariable Long id) {
        return service.getRule(id);
    }
}
