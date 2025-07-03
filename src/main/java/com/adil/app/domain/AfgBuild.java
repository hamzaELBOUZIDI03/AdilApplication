package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "afg_build")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AfgBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_ecrire")
    private LocalDate dateEcrire;

    private Double montant;

    private String commentaire;

}
