package com.adil.app.mapper;

import com.adil.app.domain.Investissement;
import com.adil.app.dto.InvestissementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvestissementMapper {

    InvestissementDTO toInvestissementDTO(Investissement investissement);

    Investissement toInvestissement(InvestissementDTO investissementDTO);

    void updatedEntity(@MappingTarget Investissement investissement, InvestissementDTO investissementDTO);

}