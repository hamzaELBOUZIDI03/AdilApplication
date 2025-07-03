package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mission_mensuelle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionMensuelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "isCompleted")
    private Boolean isCompleted;
}