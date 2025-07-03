package com.adil.app.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffreFortDTO {

    private Integer id;
    @Column(nullable = false)
    private Double coffreTotal;
    @Column(nullable = false)
    private String coffreName;
    private String coffreNameIhm;
    private String coffreColor;
    private LocalDate dateDerniereModification;

}
