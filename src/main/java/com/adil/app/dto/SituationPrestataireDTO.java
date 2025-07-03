package com.adil.app.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituationPrestataireDTO {

    private Integer id;

    private LocalDate dateEcrire;

    private String local;

    private String eau;

    private String electricite;

    private String escalier;
}
