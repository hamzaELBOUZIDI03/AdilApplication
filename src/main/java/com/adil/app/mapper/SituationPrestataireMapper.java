package com.adil.app.mapper;

import com.adil.app.domain.SituationPrestataire;
import com.adil.app.dto.SituationPrestataireDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SituationPrestataireMapper {

    SituationPrestataireDTO toSituationPrestataireDTO(SituationPrestataire situationPrestataire);

    SituationPrestataire toSituationPrestataire(SituationPrestataireDTO situationPrestataireDTO);

    void updatedEntity(@MappingTarget SituationPrestataire situationPrestataire, SituationPrestataireDTO situationPrestataireDTO);
}