package com.service.repairs.domain.repository;

import com.service.repairs.domain.WorkOrderRequest;
import org.springframework.data.repository.CrudRepository;

public interface WorkOrderRequestRepository extends CrudRepository<WorkOrderRequest, Long> {
}