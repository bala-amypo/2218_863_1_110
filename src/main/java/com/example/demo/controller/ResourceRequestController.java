// ResourceRequestController.java
package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequestController {

    private final ResourceRequestService requestService;
    public ResourceRequestController(ResourceRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/{userId}")
    public ResourceRequest create(@PathVariable Long userId, @RequestBody ResourceRequest req) {
        return requestService.createRequest(userId, req);
    }

    @GetMapping("/user/{userId}")
    public List<ResourceRequest> byUser(@PathVariable Long userId) {
        return requestService.getRequestsByUser(userId);
    }

    @GetMapping("/{id}")
    public ResourceRequest get(@PathVariable Long id) {
        return requestService.getRequest(id);
    }

    @PutMapping("/status/{requestId}")
    public ResourceRequest updateStatus(@PathVariable Long requestId, @RequestParam String status) {
        return requestService.updateRequestStatus(requestId, status);
    }
}
