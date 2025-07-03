package com.adil.app.mapper;

import com.adil.app.domain.Mawjoudat;
import com.adil.app.dto.MawjoudatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MawjoudatMapper {

    MawjoudatDTO toMawjoudatDTO(Mawjoudat mawjoudat);

    Mawjoudat toMawjoudat(MawjoudatDTO mawjoudatDTO);

    void updatedEntity(@MappingTarget Mawjoudat mawjoudat, MawjoudatDTO mawjoudatDTO);
}
