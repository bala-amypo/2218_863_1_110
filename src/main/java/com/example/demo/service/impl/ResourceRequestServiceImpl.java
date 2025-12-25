package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {

    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;

    public ResourceRequestServiceImpl(ResourceRequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResourceRequest createRequest(Long userId, ResourceRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        request.setRequestedBy(user);
        // Status defaults to PENDING in Entity, or we can explicit here.
        if (request.getStatus() == null) {
            request.setStatus("PENDING");
        }

        return requestRepository.save(request);
    }

    @Override
    public List<ResourceRequest> getRequestsByUser(Long userId) {
         
        return requestRepository.findByRequestedBy_Id(userId);
    }

    @Override
    public ResourceRequest getRequest(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));
    }

    @Override
    public ResourceRequest updateRequestStatus(Long requestId, String status) {
        ResourceRequest request = getRequest(requestId);
        request.setStatus(status);
        return requestRepository.save(request);
    }
}
