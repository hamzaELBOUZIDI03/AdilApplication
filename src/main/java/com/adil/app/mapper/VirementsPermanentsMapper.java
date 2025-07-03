package com.adil.app.mapper;

import com.adil.app.domain.VirementsPermanents;
import com.adil.app.dto.VirementsPermanentsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VirementsPermanentsMapper {

    VirementsPermanentsDTO toVirementsPermanentsDTO(VirementsPermanents virementsPermanents);

    VirementsPermanents toVirementsPermanents(VirementsPermanentsDTO virementsPermanentsDTO);

    void updatedEntity(@MappingTarget VirementsPermanents virementsPermanents, VirementsPermanentsDTO virementsPermanentsDTO);
}