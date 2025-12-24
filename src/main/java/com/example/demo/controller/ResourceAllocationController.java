package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceAllocationService {
    private final ResourceRequestRepository requestRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository allocationRepository;

    // Exact order required
    public ResourceAllocationService(ResourceRequestRepository requestRepository, 
                                     ResourceRepository resourceRepository, 
                                     ResourceAllocationRepository allocationRepository) {
        this.requestRepository = requestRepository;
        this.resourceRepository = resourceRepository;
        this.allocationRepository = allocationRepository;
    }

    public ResourceAllocation autoAllocate(Long requestId) {
        ResourceRequest request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found"));
         
        List<Resource> resources = resourceRepository.findByResourceType(request.getResourceType());
        
        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setRequest(request);
        
        if (!resources.isEmpty()) { 
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

    public ResourceAllocation getAllocation(Long id) {
        return allocationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
    
    public List<ResourceAllocation> getAllAllocations() { return allocationRepository.findAll(); }
}