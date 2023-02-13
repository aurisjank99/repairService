package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.repairs.domain.RequestParts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PartDto {
    @JsonProperty(value = "inventory_number")
    private String inventoryNumber;

    private String name;

    private Long count;

    public static PartDto toDto(RequestParts parts){
        return new PartDto(parts.getInventoryNumber(), parts.getName(), parts.getCount());
    }
}