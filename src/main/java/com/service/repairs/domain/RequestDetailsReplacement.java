package com.service.repairs.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "request_details_replacement")
@NoArgsConstructor(access = PROTECTED)
public class RequestDetailsReplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "factory_name", updatable = false, nullable = false)
    private String factoryName;

    @Column(name = "factory_order_number", updatable = false, nullable = false)
    private String factoryOrderNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    public RequestDetailsReplacement(String factoryName, String factoryOrderNumber, Request request) {
        this.factoryName = factoryName;
        this.factoryOrderNumber = factoryOrderNumber;
        this.request = request;
    }
}
