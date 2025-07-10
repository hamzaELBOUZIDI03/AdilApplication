package com.adil.app.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SharedFieldResponse {

    private Double rest;
    private Double coffreTotal;
    private Double montantConsommee;
    private LocalDate lastDateModification;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private boolean first;
    private boolean last;
    private int numberOfElements;



}
