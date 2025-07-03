package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "iltizamat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Iltizamat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateEcrire;

    private Double montant;

    private String commentaire;
}
