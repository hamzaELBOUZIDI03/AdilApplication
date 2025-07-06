package com.adil.app.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditDTO {

    private Integer id;
    private String nomComplet;
    @Column(nullable = false)
    private Double montant;
    private LocalDate dateSortie;
    private LocalDate dateRetour;
    private CoffreFortDTO coffreFort;
    private String commentaire;

}
