package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceRequestService {
    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;

    // Exact order required
    public ResourceRequestService(ResourceRequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public ResourceRequest createRequest(Long userId, ResourceRequest request) {
        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new RuntimeException("Start time must be before end time");
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        request.setRequestedBy(user);
        request.setStatus("PENDING");
        return requestRepository.save(request);
    }

    public java.util.List<ResourceRequest> getRequestsByUser(Long userId) {
        return requestRepository.findByRequestedBy_Id(userId);
    }
    
    public ResourceRequest getRequest(Long id) {
        return requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
    }

    public ResourceRequest updateRequestStatus(Long requestId, String status) {
        ResourceRequest req = getRequest(requestId);
        req.setStatus(status);
        return requestRepository.save(req);
    }
}