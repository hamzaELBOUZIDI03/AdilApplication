package com.adil.app.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirementsPermanentsDTO {

    private Integer id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Double montant;

    private String domiciliation;

    private String beneficiaire;

    private String compte;

    private String adresse;
}
