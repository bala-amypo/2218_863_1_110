package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequestController {

    private final ResourceRequestService service;

    public ResourceRequestController(ResourceRequestService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public ResourceRequest create(
            @PathVariable Long userId,
            @RequestBody ResourceRequest request) {
        return service.createRequest(userId, request);
    }

    @GetMapping("/user/{userId}")
    public List<ResourceRequest> byUser(@PathVariable Long userId) {
        return service.getRequestsByUser(userId);
    }

    @GetMapping("/{id}")
    public ResourceRequest get(@PathVariable Long id) {
        return service.getRequest(id);
    }

    @PutMapping("/status/{requestId}")
    public ResourceRequest updateStatus(
            @PathVariable Long requestId,
            @RequestParam String status) {
        return service.updateRequestStatus(requestId, status);
    }
}
