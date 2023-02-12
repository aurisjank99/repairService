package com.service.repairs.service;

import com.service.repairs.domain.Department;
import com.service.repairs.domain.repository.DepartmentRepository;
import com.service.repairs.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.service.repairs.exception.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new BaseException(NOT_FOUND, "Department " + name + " not found "));
    }
}
