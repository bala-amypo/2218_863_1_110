package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.service.ResourceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

   private final ResourceRepository resourceRepository;


    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource createResource(Resource resource) {
        if (resource.getResourceType() == null || resource.getResourceType().isEmpty()) {
            throw new BadRequestException("Resource type cannot be null or empty");
        }
        if (resource.getCapacity() == null || resource.getCapacity() < 1) {
            throw new BadRequestException("Capacity must be at least 1");
        }
        if (resourceRepository.existsByResourceName(resource.getResourceName())) {
            throw new BadRequestException("Resource with name " + resource.getResourceName() + " already exists");
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
