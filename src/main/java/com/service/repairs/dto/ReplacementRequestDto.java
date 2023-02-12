package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ReplacementRequestDto extends RequestDto {
    @JsonProperty(value = "factory_name")
    private String factoryName;

    @JsonProperty(value = "factory_order_number")
    private String factoryOrderNumber;
}