package com.khouss.daf.services;

import com.khouss.daf.dto.CitoyenDto;
import com.khouss.daf.entities.Citoyen;
import com.khouss.daf.repositories.CitoyenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CitoyenService implements CitoyenServiceInterface {

    @Autowired
    private  CitoyenRepository citoyenRepository;
    @Autowired
    private  CitoyenMapper citoyenMapper;

    @Override
    public List<CitoyenDto> findAllCitoyens() {
        return citoyenRepository.findAll()
                .stream()
                .map(citoyenMapper::toDto)
                .toList();
    }

    @Override
    public CitoyenDto findByNumCni(Long numCni) {
        Citoyen citoyen = citoyenRepository.findByNumCni(numCni)
                .orElseThrow(() -> new RuntimeException("Citoyen not found"));

        return citoyenMapper.toDto(citoyen);
    }

    @Override
    public CitoyenDto createCitoyen(CitoyenDto citoyenDto) {
        Citoyen citoyen = citoyenMapper.toEntity(citoyenDto);
        Citoyen saved = citoyenRepository.save(citoyen);
        return citoyenMapper.toDto(saved);
    }

    @Override
    public CitoyenDto updateCitoyen(UUID id, CitoyenDto citoyenDto) {
        Citoyen existing = citoyenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citoyen not found"));

        existing.setNom(citoyenDto.getNom());
        existing.setPrenom(citoyenDto.getPrenom());
        existing.setAdresse(citoyenDto.getAdresse());

        Citoyen updated = citoyenRepository.save(existing);
        return citoyenMapper.toDto(updated);
    }

    @Override
    public void deleteCitoyen(UUID id) {
        citoyenRepository.deleteById(id);
    }
}
