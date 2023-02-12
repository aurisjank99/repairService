package com.service.repairs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
public class RepairRequestDto extends RequestDto {
    @JsonProperty(value = "analysis_date")
    private LocalDate analysisDate;

    @JsonProperty(value = "test_date")
    private LocalDate testDate;

    @JsonProperty(value = "responsible_person")
    private String responsiblePerson;

}
