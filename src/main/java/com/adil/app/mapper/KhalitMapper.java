package com.adil.app.mapper;

import com.adil.app.domain.Khalit;
import com.adil.app.dto.KhalitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KhalitMapper {

    @Mapping(target = "coffreFort", ignore = true)
    KhalitDTO toKhalitDTO(Khalit khalit);

    @Mapping(target = "coffreFort", ignore = true)
    Khalit toKhalit(KhalitDTO khalitDTO);

    @Mapping(target = "coffreFort", ignore = true)
    void updateEntity(@MappingTarget Khalit khalit, KhalitDTO khalitDTO);

}
