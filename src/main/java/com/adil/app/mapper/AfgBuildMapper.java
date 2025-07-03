package com.adil.app.mapper;

import com.adil.app.domain.AfgBuild;
import com.adil.app.dto.AfgBuildDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AfgBuildMapper {

    AfgBuildDTO toAfgBuildDTO(AfgBuild afgBuild);

    AfgBuild toAfgBuild(AfgBuildDTO afgBuildDTO);

    void updatedEntity(@MappingTarget AfgBuild afgBuild, AfgBuildDTO afgBuildDTO);

    List<AfgBuildDTO> toAfgBuildDTOs(List<AfgBuild> afgBuilds);
}