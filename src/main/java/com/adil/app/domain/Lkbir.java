package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "lkbir")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lkbir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomComplet;
    @Column(nullable = false)
    private Double montant;
    private LocalDate dateSortie;
    private LocalDate dateRetour;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffre_fort_id", referencedColumnName = "id")
    private CoffreFort coffreFort;

}
