package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "coffre_fort")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffreFort {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Double coffreTotal;
    @Column(nullable = false, updatable = false)
    private String coffreName;
    private String coffreNameIhm;
    private String coffreColor;
    private LocalDate dateDerniereModification;

}
