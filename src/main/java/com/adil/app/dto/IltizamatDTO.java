package com.adil.app.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IltizamatDTO {

    private Integer id;

    private LocalDate dateEcrire;

    private Double montant;

    private String commentaire;
}
