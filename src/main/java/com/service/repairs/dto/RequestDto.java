package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestDto {
    private String type;

    private String department;

    @JsonProperty(value = "start_date")
    private LocalDate startDate;

    @JsonProperty(value = "end_date")
    private LocalDate endDate;

    private String currency;

    private BigDecimal cost;

    private List<PartDto> parts;
}
