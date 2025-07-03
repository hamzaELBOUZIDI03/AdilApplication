package com.adil.app.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AfgBuildDTO {

    private Integer id;

    private LocalDate dateEcrire;

    private Double montant;

    private String commentaire;

}
