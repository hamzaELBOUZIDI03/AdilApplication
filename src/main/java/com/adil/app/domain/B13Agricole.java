package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "b13_agricole")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class B13Agricole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateEcrire;

    private Double montant;

    private String commentaire;
}
