package com.service.repairs.domain;

import com.service.repairs.dto.AnalysisRequestDto;
import com.service.repairs.utils.StringBuilderPlus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

@Entity
@DiscriminatorValue("ANALYSIS")
@NoArgsConstructor(access = PROTECTED)
public class AnalysisRequest extends Request {
    public AnalysisRequest(AnalysisRequestDto analysisRequestDto, Optional<Department> department, StringBuilderPlus errorMessages) {
        super(analysisRequestDto.getStartDate(), analysisRequestDto.getEndDate(), analysisRequestDto.getCurrency(), analysisRequestDto.getCost(), errorMessages);
        department.ifPresent(this::setDepartment);
        addParts(analysisRequestDto.getParts());
    }
}