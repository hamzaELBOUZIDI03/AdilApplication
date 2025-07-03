package com.adil.app.mapper;

import com.adil.app.domain.B13Agricole;
import com.adil.app.dto.B13AgricoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface B13AgricoleMapper {

    B13AgricoleDTO toB13AgricoleDTO(B13Agricole b13Agricole);

    B13Agricole toB13Agricole(B13AgricoleDTO b13AgricoleDTO);

    void updatedEntity(@MappingTarget B13Agricole b13Agricole, B13AgricoleDTO b13AgricoleDTO);

}