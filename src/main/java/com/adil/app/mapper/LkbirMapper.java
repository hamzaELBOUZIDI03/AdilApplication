package com.adil.app.mapper;

import com.adil.app.domain.Lkbir;
import com.adil.app.dto.LkbirDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LkbirMapper {

    @Mapping(target = "coffreFort", ignore = true)
    LkbirDTO toLkbirDTO(Lkbir lkbir);

    @Mapping(target = "coffreFort", ignore = true)
    Lkbir toLkbir(LkbirDTO lkbir);

    @Mapping(target = "coffreFort", ignore = true)
    void updateEntity(@MappingTarget Lkbir lkbir, LkbirDTO lkbirDTO);

}
