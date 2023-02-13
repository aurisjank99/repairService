package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.repairs.domain.ReplacementRequest;
import com.service.repairs.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplacementRequestDto extends RequestDto {
    @JsonProperty(value = "factory_name")
    private String factoryName;

    @JsonProperty(value = "factory_order_number")
    private String factoryOrderNumber;

    @Override
    public String toString() {
        return super.toString() + ", factoryName=" + factoryName + ", factoryOrderNumber=" + factoryOrderNumber;
    }

    public static ReplacementRequestDto toDto(ReplacementRequest request) {
        ReplacementRequestDto dto = new ReplacementRequestDto();
        dto.setType(request.getType().name());
        dto.setDepartment(request.getDepartment().getName());
        dto.setStartDate(Utils.convertInstantToLocalDate(request.getStartDate()));
        dto.setEndDate(Utils.convertInstantToLocalDate(request.getEndDate()));
        dto.setCost(request.getCost().getNumber().numberValue(BigDecimal.class));
        dto.setCurrency(request.getCost().getCurrency().getCurrencyCode());
        dto.setParts(request.getRequestParts().stream().map(PartDto::toDto).collect(Collectors.toList()));
        dto.setFactoryName(request.getRequestDetails().getFactoryName());
        dto.setFactoryOrderNumber(request.getRequestDetails().getFactoryName());
        return dto;
    }
}