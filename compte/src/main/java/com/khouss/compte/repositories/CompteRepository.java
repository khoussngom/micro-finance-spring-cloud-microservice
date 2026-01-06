package com.khouss.compte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.khouss.compte.entities.Compte;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {
    Optional<Compte> findByNumeroTelephone(String numeroTelephone);
    Optional<Compte> findByNumCni(Long numCni);
    boolean existsByNumeroTelephone(String numeroTelephone);
    boolean existsByNumCni(Long numCni);
}
