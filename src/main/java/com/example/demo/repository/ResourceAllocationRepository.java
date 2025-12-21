package com.example.demo.repository;

import com.example.demo.entity.ResourceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  extends JpaRepository<ResourceAllocation, Long> {
}
