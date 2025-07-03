package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "identifiant")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Identifiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroCompte;
    private String typeCompte;
    private String identifiant;
    private String motDePasse;
    private String carteGuichet;

}
