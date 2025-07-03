package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "situation_prestataire")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituationPrestataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateEcrire;

    private String local;

    private String eau;

    private String electricite;

    private String escalier;
}