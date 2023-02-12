package com.service.repairs.domain;

import com.service.repairs.dto.PartDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "request_parts")
@NoArgsConstructor(access = PROTECTED)
public class RequestParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "inventory_number")
    private String inventoryNumber;

    @Column(name = "name")
    private String name;


    @Column(name = "count")
    private Long count;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    RequestParts(PartDto part, Request request){
        this.inventoryNumber = part.getInventoryNumber();
        this.name = part.getName();
        this.count = part.getCount();
        this.request = request;
    }
}
