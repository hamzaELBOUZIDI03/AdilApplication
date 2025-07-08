package com.adil.app.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adil2DTO {

    private Integer id;
    private String nomComplet;
    @Column(nullable = false)
    private Double montant;
    private LocalDate dateSortie;
    private LocalDate dateRetour;
    private CoffreFortDTO coffreFort;
    private String commentaire;

}
