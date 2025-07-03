package com.adil.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionMensuelleDTO {

    private Integer id;

    private String description;

    private Boolean isCompleted;
}
