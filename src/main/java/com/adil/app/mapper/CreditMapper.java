package com.adil.app.mapper;

import com.adil.app.domain.Credit;
import com.adil.app.dto.CreditDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    @Mapping(target = "coffreFort", ignore = true)
    CreditDTO toCreditDTO(Credit credit);

    @Mapping(target = "coffreFort", ignore = true)
    Credit toCredit(CreditDTO creditDTO);

    @Mapping(target = "coffreFort", ignore = true)
    void updateEntity(@MappingTarget Credit credit, CreditDTO creditDTO);

}
