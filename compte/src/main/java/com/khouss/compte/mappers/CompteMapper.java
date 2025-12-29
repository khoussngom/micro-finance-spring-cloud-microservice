package com.khouss.compte.mappers;


import com.khouss.compte.dtos.CreateCompte;
import com.khouss.compte.dtos.GetCompte;
import com.khouss.compte.entities.Compte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteMapper {
    Compte toEntity(CreateCompte createCompte);
    GetCompte toDto(Compte compte);
}
