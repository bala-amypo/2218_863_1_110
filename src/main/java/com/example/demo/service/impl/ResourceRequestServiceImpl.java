package com.example.demo.service.impl;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {

    private final ResourceRequestRepository requestRepo;
    private final UserRepository userRepo;

    public ResourceRequestServiceImpl(ResourceRequestRepository r, UserRepository u) {
        this.requestRepo = r;
        this.userRepo = u;
    }

    public ResourceRequest createRequest(Long userId, ResourceRequest req) {
        if (req.getStartTime().isAfter(req.getEndTime())) {
            throw new RuntimeException("start must be before end");
        }
        req.setRequestedBy(userRepo.findById(userId).orElseThrow());
        return requestRepo.save(req);
    }
}
