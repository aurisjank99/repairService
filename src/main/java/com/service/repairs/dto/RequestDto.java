package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    @Schema(title = "Request type", example = "ANALYSIS")
    private String type;

    @Schema(title = "Department of request", example = "GOoD analysis department")
    private String department;

    @Schema(title = "Start date of request", example = "2000-07-01")
    @JsonProperty(value = "start_date")
    private LocalDate startDate;

    @Schema(title = "End date of request", example = "2000-07-01")
    @JsonProperty(value = "end_date")
    private LocalDate endDate;

    @Schema(title = "Currency", example = "USD")
    private String currency;

    @Schema(title = "Cost", example = "123.12")
    private BigDecimal cost;

    private List<PartDto> parts;
}
