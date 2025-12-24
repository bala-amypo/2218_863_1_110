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
            ResourceRequestRepository requestRepo,
            ResourceRepository resourceRepo,
            ResourceAllocationRepository allocationRepo) {
        this.requestRepo = requestRepo;
        this.resourceRepo = resourceRepo;
        this.allocationRepo = allocationRepo;
    }

    @Override
    public ResourceAllocation autoAllocate(Long requestId) {

        ResourceRequest request =
                requestRepo.findById(requestId).orElseThrow();

        List<Resource> resources =
                resourceRepo.findByResourceType(request.getResourceType());

        ResourceAllocation alloc = new ResourceAllocation();
        alloc.setRequest(request);

        if (resources.isEmpty()) {
            alloc.setConflictFlag(true);
            alloc.setNotes("No resource available");
        } else {
            alloc.setResource(resources.get(0));
            alloc.setConflictFlag(false);
        }

        return allocationRepo.save(alloc);
    }

    @Override
    public ResourceAllocation getAllocation(Long id) {
        return allocationRepo.findById(id).orElseThrow();
    }
@Override
public List<ResourceAllocation> getAllAllocations() {
    return allocationRepo.findAll();
}

}
