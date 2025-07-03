package com.adil.app.mapper;

import com.adil.app.domain.MissionMensuelle;
import com.adil.app.dto.MissionMensuelleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MissionMensuelleMapper {

    MissionMensuelleDTO toMissionMensuelleDTO(MissionMensuelle missionMensuelle);

    MissionMensuelle toMissionMensuelle(MissionMensuelleDTO missionMensuelleDTO);

    void updatedEntity(@MappingTarget MissionMensuelle missionMensuelle, MissionMensuelleDTO missionMensuelleDTO);
}