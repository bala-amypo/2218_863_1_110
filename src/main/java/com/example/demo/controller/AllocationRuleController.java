package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.AllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleService ruleService;

    public AllocationRuleController(AllocationRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createRule(@RequestBody AllocationRule rule) {
        AllocationRule created = ruleService.createRule(rule);
        return ResponseEntity.ok(new ApiResponse(true, "Rule created successfully", created));
    }

    @GetMapping
    public ResponseEntity<List<AllocationRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationRule> getRuleById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRule(id));
    }
}
