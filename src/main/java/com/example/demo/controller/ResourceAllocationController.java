package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse> autoAllocate(@PathVariable Long requestId) {
        ResourceAllocation allocation = allocationService.autoAllocate(requestId);
        return ResponseEntity.ok(new ApiResponse(true, "Resource allocated successfully", allocation));
    }

    @GetMapping
    public ResponseEntity<List<ResourceAllocation>> getAllAllocations() {
        return ResponseEntity.ok(allocationService.getAllAllocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceAllocation> getAllocationById(@PathVariable Long id) {
        return ResponseEntity.ok(allocationService.getAllocation(id));
    }
}
