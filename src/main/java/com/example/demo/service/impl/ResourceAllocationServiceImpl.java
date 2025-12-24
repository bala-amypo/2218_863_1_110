package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

    private final ResourceRequestRepository requestRepo;
    private final ResourceRepository resourceRepo;
    private final ResourceAllocationRepository allocationRepo;

    public ResourceAllocationServiceImpl(
        ResourceRequestRepository r,
        ResourceRepository res,
        ResourceAllocationRepository a) {

        this.requestRepo = r;
        this.resourceRepo = res;
        this.allocationRepo = a;
    }

    public ResourceAllocation autoAllocate(Long requestId) {
        ResourceRequest req = requestRepo.findById(requestId).orElseThrow();
        List<Resource> resources = resourceRepo.findByResourceType(req.getResourceType());

        ResourceAllocation alloc = new ResourceAllocation();
        alloc.setRequest(req);

        if (resources.isEmpty()) {
            alloc.setConflictFlag(true);
            alloc.setNotes("No resource available");
        } else {
            alloc.setResource(resources.get(0));
            alloc.setConflictFlag(false);
        }
        return allocationRepo.save(alloc);
    }
}
