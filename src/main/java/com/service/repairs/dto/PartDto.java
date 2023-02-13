package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.repairs.domain.RequestParts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartDto {
    @Schema(title = "Inventory number", example = "InventoryNumber5")
    @JsonProperty(value = "inventory_number")
    private String inventoryNumber;

    @Schema(title = "Part name number", example = "PartNumber5")
    private String name;

    @Schema(title = "Number of parts", example = "2")
    private Long count;

    public static PartDto toDto(RequestParts parts) {
        return new PartDto(parts.getInventoryNumber(), parts.getName(), parts.getCount());
    }
}