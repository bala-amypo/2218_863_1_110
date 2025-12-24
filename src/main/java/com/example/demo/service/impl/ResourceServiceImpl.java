package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
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

    public Resource createResource(Resource resource) {
        if (resourceRepository.existsByResourceName(resource.getResourceName())) {
            throw new RuntimeException("resource exists");
        }
        if (resource.getCapacity() < 1) {
            throw new RuntimeException("capacity invalid");
        }
        return resourceRepository.save(resource);
    }
}
