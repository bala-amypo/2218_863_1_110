package com.example.demo.service;

import com.example.demo.entity.ResourceAllocation;
import java.util.List;

public interface ResourceAllocation{
    ResourceAllocation autoAllocate(Long requestId);

    ResourceAllocation getAllocation(Long id);

    List<ResourceAllocation> getAllAllocations();
}
