package com.service.repairs.domain;

import com.service.repairs.dto.PartDto;
import com.service.repairs.dto.ReplacementRequestDto;
import com.service.repairs.utils.StringBuilderPlus;
import com.service.repairs.utils.Utils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("REPLACEMENT")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplacementRequest extends Request {
    @Getter
    @OneToOne(mappedBy = "request", cascade = CascadeType.PERSIST)
    RequestDetailsReplacement requestDetails;

    public ReplacementRequest(ReplacementRequestDto replacementRequestDto,
                              Optional<Department> department,
                              StringBuilderPlus errorMessages) {
        super(replacementRequestDto.getStartDate(),
                replacementRequestDto.getEndDate(),
                replacementRequestDto.getCurrency(),
                replacementRequestDto.getCost(),
                errorMessages);
        department.ifPresent(this::setDepartment);
        addParts(replacementRequestDto.getParts());
        validate(replacementRequestDto, errorMessages);
        this.requestDetails = new RequestDetailsReplacement(replacementRequestDto.getFactoryName(),
                replacementRequestDto.getFactoryOrderNumber(), this);
    }

    private void validate(ReplacementRequestDto replacementRequestDto, StringBuilderPlus errorMessages) {
        validateFactoryName(replacementRequestDto.getFactoryName(), errorMessages);
        validateFactoryOrderNumber(replacementRequestDto.getFactoryOrderNumber(), errorMessages);
        validatePartInventoryNumbers(replacementRequestDto.getParts(), errorMessages);
    }

    private void validateFactoryName(String name, StringBuilderPlus errorMessages) {
        if (name == null || name.isBlank()) {
            errorMessages.appendLine("Factory name cannot be empty");
        }
    }

    private void validateFactoryOrderNumber(String orderNumber, StringBuilderPlus errorMessages) {
        if (orderNumber == null || !Utils.validateUsingRegex(orderNumber, Utils.ORDER_NUMBER_REGEX)) {
            errorMessages.appendLine("Order number should be 10 symbols long, first 2 symbols - letters other numbers");
        }
    }

    private void validatePartInventoryNumbers(List<PartDto> parts, StringBuilderPlus errorMessages) {
        boolean hasPartInventoryNumberBlank = parts.stream()
                .anyMatch(this::isPartInventoryNumberBlank);
        if (hasPartInventoryNumberBlank) {
            errorMessages.appendLine("Part inventory number cannot be empty");
        }
    }

    private boolean isPartInventoryNumberBlank(PartDto part) {
        return part.getInventoryNumber() == null
                || part.getInventoryNumber().isBlank();
    }
}
