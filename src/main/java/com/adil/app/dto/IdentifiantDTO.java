package com.adil.app.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentifiantDTO {

    private Integer id;
    private String numeroCompte;
    private String typeCompte;
    private String identifiant;
    private String motDePasse;
    private String carteGuichet;

}
