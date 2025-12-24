package com.example.demo.controller;

import com.example.demo.entity.Resource;
import com.example.demo.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public Resource create(@RequestBody Resource resource) {
        return service.createResource(resource);
    }

    @GetMapping
    public List<Resource> all() {
        return service.getAllResources();
    }

    @GetMapping("/{id}")
    public Resource get(@PathVariable Long id) {
        return service.getResource(id);
    }
}
