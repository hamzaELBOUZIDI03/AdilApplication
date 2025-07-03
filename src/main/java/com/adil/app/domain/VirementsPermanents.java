package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "virements_permanents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirementsPermanents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "domiciliation")
    private String domiciliation;

    @Column(name = "beneficiaire")
    private String beneficiaire;

    @Column(name = "compte")
    private String compte;

    @Column(name = "adresse")
    private String adresse;
}