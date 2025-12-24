package com.example.demo.repository;
import com.example.demo.entity.AllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceAllocationRepository
        extends JpaRepository<ResourceAllocation, Long> { }
