package com.adil.app.mapper;

import com.adil.app.domain.CoffreFort;
import com.adil.app.dto.CoffreFortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CoffreFortMapper {

    CoffreFortDTO toCoffreFortDTO(CoffreFort coffreFort);

    @Mapping(target = "coffreName", ignore = true)
    void updateEntity(@MappingTarget CoffreFort coffreFort, CoffreFortDTO coffreFortDTO);

}
