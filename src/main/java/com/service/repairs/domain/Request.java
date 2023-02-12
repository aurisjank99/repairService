package com.service.repairs.domain;

import com.service.repairs.dto.PartDto;
import com.service.repairs.utils.StringBuilderPlus;
import com.service.repairs.utils.Utils;
import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CompositeType;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "request_type", discriminatorType = STRING)
@Table(name = "request")
@NoArgsConstructor(access = PROTECTED)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "request_type", insertable = false, updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    @Column(name = "start_date", updatable = false, nullable = false)
    private Instant startDate;

    @Column(name = "end_date", updatable = false, nullable = false)
    private Instant endDate;

    @AttributeOverride(
            name = "amount",
            column = @Column(name = "amount")
    )
    @AttributeOverride(
            name = "currency",
            column = @Column(name = "currency")
    )
    @CompositeType(MonetaryAmountType.class)
    private MonetaryAmount cost;

    @Setter
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "request", cascade = CascadeType.PERSIST)
    private List<RequestParts> requestParts;

    public void addParts(List<PartDto> parts) {
        this.requestParts = parts.stream()
                .map(part -> new RequestParts(part ,this))
                .collect(Collectors.toList());
    }

    public Request(LocalDate startDate,
                   LocalDate endDate,
                   String currency,
                   BigDecimal cost,
                   StringBuilderPlus errorMessages) {
        try {
            this.cost = Money.of(cost, currency);
            validateCost(cost, errorMessages);
        } catch (Exception e) {
            errorMessages.appendLine("Provided currency is not supported");
        }
        this.startDate = Utils.convertLocalDateToInstant(startDate);
        this.endDate = Utils.convertLocalDateToInstant(endDate);
        validateStartDate(startDate, errorMessages);
        validateEndDate(endDate, startDate, errorMessages);
    }

    private void validateStartDate(LocalDate startDate, StringBuilderPlus errorMessages) {
        if (startDate == null) {
            errorMessages.appendLine("Start date cannot be empty");
        } else if (startDate.isAfter(LocalDate.now())) {
            errorMessages.appendLine("Start date cannot be latter day then " + LocalDate.now().minusDays(1));
        }

    }

    private void validateEndDate(LocalDate endDate, LocalDate startDate, StringBuilderPlus errorMessages) {
        if (endDate == null) {
            errorMessages.appendLine("End date cannot be empty");
        } else if (startDate != null && endDate.isBefore(startDate)) {
            errorMessages.appendLine("End date cannot before start date");
        }
    }

    private void validateCost(BigDecimal cost, StringBuilderPlus errorMessages) {
        if (cost == null || cost.compareTo(BigDecimal.ZERO) <= 0) {
            errorMessages.appendLine("Cost must be positive amount");
        }
    }
}
