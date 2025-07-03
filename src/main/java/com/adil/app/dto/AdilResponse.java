package com.adil.app.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdilResponse {

    private List<AdilDTO> elements;
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
