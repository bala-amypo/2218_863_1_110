package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceAllocationServiceImpl implements ResourceAllocationService {

  // 1. Update these types to match your imports/files exactly
    private final ResourceRequestRepository requestRepository; 
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository allocationRepository;

    // 2. Update the constructor parameters to match
    public ResourceAllocationServiceImpl(
            ResourceRequestRepository requestRepository,
            ResourceRepository resourceRepository,
            ResourceAllocationRepository allocationRepository) {
        this.requestRepository = requestRepository;
        this.resourceRepository = resourceRepository;
        this.allocationRepository = allocationRepository;
    }

    @Override
    public ResourceAllocation autoAllocate(Long requestId) {
        ResourceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + requestId));

        List<Resource> resources = resourceRepository.findByResourceType(request.getResourceType());

        if (resources.isEmpty()) {
            throw new ResourceNotFoundException("No resources found for type: " + request.getResourceType());
        }

        Resource selectedResource = resources.get(0);

        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setResource(selectedResource);
        allocation.setRequest(request);
        allocation.setConflictFlag(false);
        allocation.setNotes("Auto-allocated");

        request.setStatus("APPROVED");
        requestRepository.save(request);

        return allocationRepository.save(allocation);
    }

    @Override
    public ResourceAllocation getAllocation(Long id) {
        return allocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Allocation not found with id: " + id));
    }

    @Override
    public List<ResourceAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }
}
