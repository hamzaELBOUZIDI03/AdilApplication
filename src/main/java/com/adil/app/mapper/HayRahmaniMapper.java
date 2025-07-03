package com.adil.app.mapper;

import com.adil.app.domain.HayRahmani;
import com.adil.app.dto.HayRahmaniDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HayRahmaniMapper {

    HayRahmaniDTO toHayRahmaniDTO(HayRahmani hayRahmani);

    HayRahmani toHayRahmani(HayRahmaniDTO hayRahmaniDTO);

    void updatedEntity(@MappingTarget HayRahmani hayRahmani, HayRahmaniDTO hayRahmaniDTO);

}