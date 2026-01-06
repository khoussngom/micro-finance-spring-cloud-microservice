package com.khouss.compte.services;

import com.khouss.compte.dtos.CreateCompte;
import com.khouss.compte.dtos.GetCompte;
import com.khouss.compte.dtos.CitoyenDto;
import com.khouss.compte.entities.Compte;
import com.khouss.compte.mappers.CompteMapper;
import com.khouss.compte.openFeign.DafClient;
import com.khouss.compte.repositories.CompteRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ComptesServices {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;
    private final DafClient dafClient;

    public List<GetCompte> getAllComptes() {
        return compteRepository.findAll()
                .stream()
                .map(compteMapper::toDto)
                .toList();
    }

    public GetCompte getCompteByNumCni(Long numCni) {
        Compte compte = compteRepository.findByNumCni(numCni)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        return compteMapper.toDto(compte);
    }

    public GetCompte createCompte(CreateCompte createCompte) {

        if (compteRepository.existsByNumCni(createCompte.getNumCni())) {
            throw new RuntimeException("Ce compte existe déjà (CNI déjà utilisé)");
        }

        if (compteRepository.existsByNumeroTelephone(createCompte.getNumeroTelephone())) {
            throw new RuntimeException("Ce compte existe déjà (numéro de téléphone déjà utilisé)");
        }

        CitoyenDto citoyen;

        try {
            citoyen = dafClient.getCitoyenByNumCni(createCompte.getNumCni());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("CNI n'existe pas");
        }

        Compte compte = compteMapper.toEntity(createCompte);
        Compte saved = compteRepository.save(compte);

        return compteMapper.toDto(saved);
    }

    public boolean compteExistsByNumeroTelephone(String numeroTelephone) {
        return compteRepository.existsByNumeroTelephone(numeroTelephone);
    }
}
