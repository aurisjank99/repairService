package com.service.repairs.domain;

import com.service.repairs.utils.Utils;
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

import java.time.Instant;
import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "request_details_repair")
@NoArgsConstructor(access = PROTECTED)
public class RequestDetailsRepair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "analysis_date")
    private Instant analysisDate;

    @Column(name = "test_date", updatable = false, nullable = false)
    private Instant testDate;

    @Column(name = "responsible_person", nullable = false)
    private String responsiblePerson;

    @OneToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    public RequestDetailsRepair(LocalDate analysisDate,
                                LocalDate testDate,
                                String responsiblePerson,
                                Request request) {
        this.analysisDate = Utils.convertLocalDateToInstant(analysisDate);
        this.testDate = Utils.convertLocalDateToInstant(testDate);
        this.responsiblePerson = responsiblePerson;
        this.request = request;
    }
}
