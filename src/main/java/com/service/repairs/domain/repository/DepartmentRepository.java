package com.service.repairs.domain.repository;

import com.service.repairs.domain.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
