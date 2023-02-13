package com.service.repairs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static com.service.repairs.domain.WorkOrderStatus.NOT_VALID;
import static com.service.repairs.domain.WorkOrderStatus.VALID;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "work_order_request")
@NoArgsConstructor(access = PROTECTED)
public class WorkOrderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "received_date", updatable = false, nullable = false)
    private Instant receivedDate;

    @Column(name = "request_type", updatable = false)
    private String requestType;

    @Column(name = "department", updatable = false)
    private String department;

    @Column(name = "status", updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;

    @Column(name = "error_messages", updatable = false, nullable = false)
    private String errorMessages;

    @Column(name = "request", updatable = false, nullable = false)
    private String request;

    public WorkOrderRequest(String requestType, String department, String errorMessages, String objectString) {
        this.receivedDate = Instant.now();
        this.requestType = requestType;
        this.department = department;
        this.status = errorMessages.isBlank() ? VALID : NOT_VALID;
        this.errorMessages = errorMessages;
        this.request = objectString;
    }
}
