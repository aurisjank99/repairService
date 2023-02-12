package com.service.repairs.service;

import com.service.repairs.domain.AnalysisRequest;
import com.service.repairs.domain.Department;
import com.service.repairs.domain.DepartmentType;
import com.service.repairs.domain.RepairRequest;
import com.service.repairs.domain.ReplacementRequest;
import com.service.repairs.domain.Request;
import com.service.repairs.domain.repository.RequestRepository;
import com.service.repairs.dto.AnalysisRequestDto;
import com.service.repairs.dto.RepairRequestDto;
import com.service.repairs.dto.ReplacementRequestDto;
import com.service.repairs.exception.BaseException;
import com.service.repairs.utils.StringBuilderPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkOrderResolveService {
    private final DepartmentService departmentService;
    private final RequestService requestService;

    public String createAnalysisRequest(AnalysisRequestDto request) {
        StringBuilderPlus errorMessages = new StringBuilderPlus();
        Optional<Department> department = getDepartment(request.getDepartment(), errorMessages);
        validateRequestedDepartment(department, request.getType(), DepartmentType.ANALYSIS, errorMessages);
        requestService.createRequest(new AnalysisRequest(request, department, errorMessages), errorMessages);
        return getResponse(errorMessages);
    }

    public String createRepairRequest(RepairRequestDto request) {
        StringBuilderPlus errorMessages = new StringBuilderPlus();
        Optional<Department> department = getDepartment(request.getDepartment(), errorMessages);
        validateRequestedDepartment(department, request.getType(), DepartmentType.REPAIR, errorMessages);
        requestService.createRequest(new RepairRequest(request, department, errorMessages), errorMessages);
        return getResponse(errorMessages);
    }

    public String createReplacementRequest(ReplacementRequestDto request) {
        StringBuilderPlus errorMessages = new StringBuilderPlus();
        Optional<Department> department = getDepartment(request.getDepartment(), errorMessages);
        validateRequestedDepartment(department, request.getType(), DepartmentType.REPLACEMENT, errorMessages);
        requestService.createRequest(new ReplacementRequest(request, department, errorMessages), errorMessages);
        return getResponse(errorMessages);
    }

    private String getResponse(StringBuilderPlus errorMessages) {
        return errorMessages.toString().isBlank() ? "Success" : errorMessages.toString();
    }

    private void validateRequestedDepartment(Optional<Department> department,
                                             String type,
                                             DepartmentType requiredType,
                                             StringBuilderPlus errorMessages) {
        try {
            DepartmentType departmentType = DepartmentType.valueOf(type);
            if (!requiredType.equals(departmentType)) {
                errorMessages.appendLine("Selected department type is not supported by this operation");
            }

            validateDepartmentType(department, departmentType, errorMessages);
        } catch (Exception e) {
            errorMessages.appendLine("Selected department type does not exist");
        }
    }

    private Optional<Department> getDepartment(String name, StringBuilderPlus errorMessages) {
        try {
            return Optional.ofNullable(departmentService.getDepartmentByName(name));
        } catch (BaseException e) {
            errorMessages.appendLine(e.getMessage());
            return Optional.empty();
        }
    }

    private void validateDepartmentType(Optional<Department> department, DepartmentType departmentType, StringBuilderPlus errorMessages) {
        if (department.isPresent()) {
            if (!department.get().getType().equals(departmentType)) {
                errorMessages.appendLine("Selected department is not correct type facility");
            }
        }
    }
}
