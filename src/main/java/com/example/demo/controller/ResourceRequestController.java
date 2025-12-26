package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.service.ResourceRequestService;
import org.springframework.http.ResponseEntity;
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
public ResponseEntity<ApiResponse> createRequest(
        @PathVariable Long userId,
        @RequestBody com.example.demo.dto.ResourceRequestInputDto dto) {

    ResourceRequest request = new ResourceRequest();
    request.setResourceType(dto.getResourceType());
    request.setStartTime(dto.getStartTime());
    request.setEndTime(dto.getEndTime());
    request.setPurpose(dto.getPurpose());

    ResourceRequest created = requestService.createRequest(userId, request);
    return ResponseEntity.ok(new ApiResponse(true, "Request created successfully", created));
}


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResourceRequest>> getUserRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getRequestsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceRequest> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getRequest(id));
    }

    @PutMapping("/status/{requestId}")
    public ResponseEntity<ResourceRequest> updateStatus(@PathVariable Long requestId, @RequestParam String status) {
        // Prompt asks for PUT /api/requests/status/{requestId} - matching strictly
        return ResponseEntity.ok(requestService.updateRequestStatus(requestId, status));
    }
}
