package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {

    private final ResourceRequestRepository requestRepo;
    private final UserRepository userRepo;

    public ResourceRequestServiceImpl(
            ResourceRequestRepository requestRepo,
            UserRepository userRepo) {
        this.requestRepo = requestRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ResourceRequest createRequest(Long userId, ResourceRequest request) {
        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new RuntimeException("start must be before end");
        }
        request.setRequestedBy(userRepo.findById(userId).orElseThrow());
        return requestRepo.save(request);
    }

    @Override
    public ResourceRequest getRequest(Long id) {
        return requestRepo.findById(id).orElseThrow();
    }

    @Override
    public List<ResourceRequest> getRequestsByUser(Long userId) {
        return requestRepo.findByRequestedBy_Id(userId);
    }

    @Override
    public ResourceRequest updateRequestStatus(Long requestId, String status) {
        ResourceRequest req = requestRepo.findById(requestId).orElseThrow();
        req.setStatus(status);
        return requestRepo.save(req);
    }
}
