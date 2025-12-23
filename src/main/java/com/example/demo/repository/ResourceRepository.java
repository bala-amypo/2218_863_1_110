package com.example.demo.repository;

import com.example.demo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    boolean existsByResourceName(String resourceName);

    List<Resource> findByResourceType(String resourceType);
}
