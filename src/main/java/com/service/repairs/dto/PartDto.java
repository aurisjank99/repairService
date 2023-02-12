package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PartDto {
    @JsonProperty(value = "inventory_number")
    private String inventoryNumber;

    private String name;

    private Long count;
}