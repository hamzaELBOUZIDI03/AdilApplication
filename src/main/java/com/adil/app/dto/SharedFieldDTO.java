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
public abstract class SharedFieldDTO {

    private Integer id;

    private String nomComplet;

    private Double montant;

    private LocalDate dateSortie;

    private LocalDate dateRetour;

    private String commentaire;

    private CoffreFortDTO coffreFort;

}
