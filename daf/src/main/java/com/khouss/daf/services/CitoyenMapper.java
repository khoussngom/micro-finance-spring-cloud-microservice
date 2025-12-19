package com.khouss.daf.services;

import com.khouss.daf.dto.CitoyenDto;
import com.khouss.daf.entities.Citoyen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitoyenMapper {
    CitoyenDto toDto(Citoyen citoyen);
    Citoyen toEntity(CitoyenDto citoyenDto);

}

