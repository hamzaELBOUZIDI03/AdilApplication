package com.adil.app.mapper;

import com.adil.app.domain.Iltizamat;
import com.adil.app.dto.IltizamatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IltizamatMapper {

    IltizamatDTO toIltizamatDTO(Iltizamat iltizamat);

    Iltizamat toIltizamat(IltizamatDTO iltizamatDTO);

    void updatedEntity(@MappingTarget Iltizamat iltizamat, IltizamatDTO iltizamatDTO);

}