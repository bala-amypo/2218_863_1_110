package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.service.ResourceService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class ResourceServiceImpl implements ResourceService {

    private final ResourceAllocationRepository resourceRepository;

    public ResourceServiceImpl(ResourceAllocationRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
    @Override
    public Resource createResource(Resource resource) {
        if (resource.getCapacity() == null || resource.getCapacity() < 1) {
            throw new IllegalArgumentException("Capacity must be >= 1");
        }
        if (resource.getResourceType() == null || resource.getResourceType().isEmpty()) {
            throw new IllegalArgumentException("Resource Type is mandatory");
        }
        if (resourceRepository.existsByResourceName(resource.getResourceName())) {
            throw new IllegalArgumentException("Resource with name " + resource.getResourceName() + " already exists");
        }
        return resourceRepository.save(resource);
    }

    @Override
    public Resource getResource(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}
