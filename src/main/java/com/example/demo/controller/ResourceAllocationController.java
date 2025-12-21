package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {

    private final ResourceAllocationService allocationService;

    public ResourceAllocationController(ResourceAllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping("/auto/{requestId}")
    public ResponseEntity<ResourceAllocation> autoAllocate(@PathVariable Long requestId) {
        return ResponseEntity.ok(allocationService.autoAllocate(requestId));
    }

    @GetMapping
    public ResponseEntity<List<ResourceAllocation>> getAllAllocations() {
        return ResponseEntity.ok(allocationService.getAllAllocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceAllocation> getAllocation(@PathVariable Long id) {
        return ResponseEntity.ok(allocationService.getAllocation(id));
    }
}
