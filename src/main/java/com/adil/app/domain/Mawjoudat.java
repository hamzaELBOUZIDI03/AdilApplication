package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mawjoudat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mawjoudat{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateEcrire;

    private Double montant;

    private String commentaire;
}
