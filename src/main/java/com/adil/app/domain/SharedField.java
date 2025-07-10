package com.adil.app.domain;

import jakarta.persistence.*;
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
public abstract class SharedField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomComplet;

    @Column(nullable = false)
    private Double montant;

    private LocalDate dateSortie;

    private LocalDate dateRetour;

    private String commentaire;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffre_fort_id", referencedColumnName = "id")
    private CoffreFort coffreFort;


}
