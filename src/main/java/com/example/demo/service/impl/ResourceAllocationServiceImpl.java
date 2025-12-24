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

    private final ResourceRequestRepository requestRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository allocationRepository;

    // Strict Constructor Order: (ResourceRequestRepository, ResourceRepository, ResourceAllocationRepository)
    public ResourceAllocationServiceImpl(ResourceRequestRepository requestRepository,
                                         ResourceRepository resourceRepository,
                                         ResourceAllocationRepository allocationRepository) {
        this.requestRepository = requestRepository;
        this.resourceRepository = resourceRepository;
        this.allocationRepository = allocationRepository;
    }

    @Override
    public ResourceAllocation autoAllocate(Long requestId) {
        ResourceRequest request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found"));

        // Logic: Find first available resource of the requested type
        List<Resource> resources = resourceRepository.findByResourceType(request.getResourceType());
        
        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setRequest(request);
        
        if (!resources.isEmpty()) {
            // Simplified logic: Assign first found resource
            allocation.setResource(resources.get(0));
            allocation.setConflictFlag(false);
            allocation.setNotes("Auto allocated");
            request.setStatus("APPROVED");
        } else {
            allocation.setConflictFlag(true);
            allocation.setNotes("No resource available");
            request.setStatus("REJECTED");
        }
        
        requestRepository.save(request);
        return allocationRepository.save(allocation);
    }

    @Override
    public ResourceAllocation getAllocation(Long id) {
        return allocationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Allocation not found"));
    }

    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }
}