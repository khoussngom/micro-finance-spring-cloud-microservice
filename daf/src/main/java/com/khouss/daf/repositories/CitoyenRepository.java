package com.khouss.daf.repositories;

import com.khouss.daf.entities.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CitoyenRepository extends JpaRepository<Citoyen, UUID> {

    Optional<Citoyen> findByNumCni(Long numCni);
}
