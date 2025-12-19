package com.khouss.daf.services;

import com.khouss.daf.dto.CitoyenDto;
import com.khouss.daf.entities.Citoyen;

import java.util.List;
import java.util.UUID;

public interface CitoyenServiceInterface {

    CitoyenDto findByNumCni(Long numCni);

    List<CitoyenDto> findAllCitoyens();

    CitoyenDto createCitoyen(CitoyenDto citoyen);

    CitoyenDto updateCitoyen(UUID id, CitoyenDto citoyen);

    void deleteCitoyen(UUID id);
}
