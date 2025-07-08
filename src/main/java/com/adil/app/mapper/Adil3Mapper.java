package com.adil.app.mapper;

import com.adil.app.domain.Adil3;
import com.adil.app.dto.Adil3DTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface Adil3Mapper {

    @Mapping(target = "coffreFort", ignore = true)
    Adil3DTO toAdil3DTO(Adil3 adil3);

    @Mapping(target = "coffreFort", ignore = true)
    Adil3 toAdil3(Adil3DTO Adil3DTO);

    @Mapping(target = "coffreFort", ignore = true)
    void updateEntity(@MappingTarget Adil3 adil3, Adil3DTO Adil3DTO);

}
