package com.service.repairs.domain;

import com.service.repairs.dto.PartDto;
import com.service.repairs.utils.StringBuilderPlus;
import com.service.repairs.domain.Department;
import com.service.repairs.domain.Request;
import com.service.repairs.domain.RequestDetailsRepair;
import com.service.repairs.dto.RepairRequestDto;
import com.service.repairs.dto.RequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@DiscriminatorValue("REPAIR")
public class RepairRequest extends Request {
    @OneToOne(mappedBy = "request", cascade = CascadeType.PERSIST)
    RequestDetailsRepair requestDetails;

    public RepairRequest(RepairRequestDto repairRequestDto, Optional<Department> department, StringBuilderPlus errorMessages) {
        super(repairRequestDto.getStartDate(), repairRequestDto.getEndDate(), repairRequestDto.getCurrency(), repairRequestDto.getCost(), errorMessages);
        department.ifPresent(this::setDepartment);
        validate(repairRequestDto, errorMessages);
        addParts(repairRequestDto.getParts());
        this.requestDetails = new RequestDetailsRepair(repairRequestDto.getAnalysisDate(), repairRequestDto.getTestDate(), repairRequestDto.getResponsiblePerson(), this);
    }

    private void validate(RepairRequestDto repairRequestDto, StringBuilderPlus errorMessages){
        validateParts(repairRequestDto.getParts(), errorMessages);
        validateAnalysisDate(repairRequestDto, errorMessages);
        validateTestDate(repairRequestDto, errorMessages);
        validateResponsiblePerson(repairRequestDto.getResponsiblePerson(), errorMessages);
    }

    private void validateParts(List<PartDto> parts, StringBuilderPlus errorMessages){
        if (parts.isEmpty()) {
            errorMessages.appendLine("Part list cannot be empty");
        }
    }

    private void validateAnalysisDate(RepairRequestDto requestDto, StringBuilderPlus errorMessages){
        Optional<LocalDate> analysisDate = Optional.ofNullable(requestDto.getAnalysisDate());
        Optional<LocalDate> startDate = Optional.ofNullable(requestDto.getStartDate());
        Optional<LocalDate> endDate = Optional.ofNullable(requestDto.getEndDate());
        if(analysisDate.isPresent()){
            if(startDate.isPresent() && analysisDate.get().isBefore(startDate.get())){
                errorMessages.appendLine("Analysis date cannot be before start date");
            }

            if(endDate.isPresent() && analysisDate.get().isAfter(endDate.get())){
                errorMessages.appendLine("Analysis date cannot be after end date");
            }
        }

    }

    private void validateResponsiblePerson(String responsiblePerson, StringBuilderPlus errorMessages){
        if(responsiblePerson == null || responsiblePerson.isBlank()){
            errorMessages.appendLine("Responsible person cannot be empty");
        }
    }

    private void validateTestDate(RepairRequestDto requestDto, StringBuilderPlus errorMessages){
        Optional<LocalDate> analysisDate = Optional.ofNullable(requestDto.getAnalysisDate());
        Optional<LocalDate> endDate = Optional.ofNullable(requestDto.getEndDate());
        Optional<LocalDate> testDate = Optional.ofNullable(requestDto.getTestDate());
        if(testDate.isEmpty()){
            errorMessages.appendLine("Test date cannot be empty");
        } else {
            if(analysisDate.isPresent() && testDate.get().isBefore(analysisDate.get())){
                errorMessages.appendLine("Test date cannot be before analysis date");
            }

            if(endDate.isPresent() && testDate.get().isAfter(endDate.get())){
                errorMessages.appendLine("Test date cannot be after end date");
            }
        }

    }
}
