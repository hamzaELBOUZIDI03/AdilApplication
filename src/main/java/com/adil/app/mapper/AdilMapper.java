package com.adil.app.mapper;

import com.adil.app.domain.Adil;
import com.adil.app.dto.AdilDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdilMapper {

    @Mapping(target = "coffreFort", ignore = true)
    AdilDTO toAdilDTO(Adil adil);

    @Mapping(target = "coffreFort", ignore = true)
    Adil toAdil(AdilDTO adilDTO);

    @Mapping(target = "coffreFort", ignore = true)
    void updateEntity(@MappingTarget Adil adil, AdilDTO adilDTO);

}
