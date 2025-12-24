package com.example.demo.service.impl;

import com.example.demo.entity.Resource;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository repo;

    public ResourceServiceImpl(ResourceRepository repo) {
        this.repo = repo;
    }

    @Override
    public Resource createResource(Resource resource) {
        if (repo.existsByResourceName(resource.getResourceName())) {
            throw new RuntimeException("resource exists");
        }
        if (resource.getCapacity() < 1) {
            throw new RuntimeException("capacity invalid");
        }
        return repo.save(resource);
    }

    @Override
    public Resource getResource(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public List<Resource> getAllResources() {
        return repo.findAll();
    }
}
