package com.service.repairs.dto;

import com.service.repairs.domain.AnalysisRequest;
import com.service.repairs.utils.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@Data
public class AnalysisRequestDto extends RequestDto {

    @Override
    public String toString() {
        return super.toString();
    }

    public static AnalysisRequestDto toDto(AnalysisRequest request) {
        AnalysisRequestDto dto = new AnalysisRequestDto();
        dto.setType(request.getType().name());
        dto.setDepartment(request.getDepartment().getName());
        dto.setStartDate(Utils.convertInstantToLocalDate(request.getStartDate()));
        dto.setEndDate(Utils.convertInstantToLocalDate(request.getEndDate()));
        dto.setCost(request.getCost().getNumber().numberValue(BigDecimal.class));
        dto.setCurrency(request.getCost().getCurrency().getCurrencyCode());
        dto.setParts(request.getRequestParts().stream().map(PartDto::toDto).collect(Collectors.toList()));

        return dto;
    }
}
