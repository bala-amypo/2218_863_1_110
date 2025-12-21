package com.example.demo.repository;

import com.example.demo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    boolean existsByResourceName(String resourceName);

    List<Resource> findByResourceType(String resourceType);
}
