package com.adil.app.mapper;

import com.adil.app.domain.Identifiant;
import com.adil.app.dto.IdentifiantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IdentifiantMapper {

    Identifiant toIdentifiant(IdentifiantDTO identifiant);

    IdentifiantDTO toIdentifiantDTO(Identifiant identifiant);

    void updatedEntity(@MappingTarget Identifiant identifiant, IdentifiantDTO identifiantDTO);

}
