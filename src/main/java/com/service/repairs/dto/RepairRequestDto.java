package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.repairs.domain.RepairRequest;
import com.service.repairs.utils.Utils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@Data
public class RepairRequestDto extends RequestDto {
    @Schema(title = "Analysis date of request", example = "2000-07-01")
    @JsonProperty(value = "analysis_date")
    private LocalDate analysisDate;

    @Schema(title = "Test date of request", example = "2000-07-01")
    @JsonProperty(value = "test_date")
    private LocalDate testDate;

    @Schema(title = "Responsible person", example = "GOoD repairmaster")
    @JsonProperty(value = "responsible_person")
    private String responsiblePerson;

    @Override
    public String toString() {
        return super.toString() + ", analysisDate=" + analysisDate + ", testDate=" + testDate + ", responsiblePerson=" + responsiblePerson;
    }

    public static RepairRequestDto toDto(RepairRequest request) {
        RepairRequestDto dto = new RepairRequestDto();
        dto.setType(request.getType().name());
        dto.setDepartment(request.getDepartment().getName());
        dto.setStartDate(Utils.convertInstantToLocalDate(request.getStartDate()));
        dto.setEndDate(Utils.convertInstantToLocalDate(request.getEndDate()));
        dto.setCost(request.getCost().getNumber().numberValue(BigDecimal.class));
        dto.setCurrency(request.getCost().getCurrency().getCurrencyCode());
        dto.setParts(request.getRequestParts().stream().map(PartDto::toDto).collect(Collectors.toList()));
        dto.setAnalysisDate(Utils.convertInstantToLocalDate(request.getRequestDetails().getAnalysisDate()));
        dto.setTestDate(Utils.convertInstantToLocalDate(request.getRequestDetails().getTestDate()));
        dto.setResponsiblePerson(request.getRequestDetails().getResponsiblePerson());

        return dto;
    }
}
