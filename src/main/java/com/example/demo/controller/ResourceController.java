package com.example.demo.controller;

import com.example.demo.entity.Resource;
import com.example.demo.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceService service;

    public ResourceController(ResourceService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Resource> create(@RequestBody Resource resource) {
        return ResponseEntity.ok(service.createResource(resource));
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAll() {
        return ResponseEntity.ok(service.getAllResources());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Resource> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getResource(id));
    }
}