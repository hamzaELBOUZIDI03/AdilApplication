package com.adil.app.mapper;

import com.adil.app.domain.Adil2;
import com.adil.app.dto.Adil2DTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface Adil2Mapper {

    @Mapping(target = "coffreFort", ignore = true)
    Adil2DTO toAdil2DTO(Adil2 adil2);

    @Mapping(target = "coffreFort", ignore = true)
    Adil2 toAdil2(Adil2DTO Adil2DTO);

    @Mapping(target = "coffreFort", ignore = true)
    void updateEntity(@MappingTarget Adil2 adil2, Adil2DTO Adil2DTO);

}
