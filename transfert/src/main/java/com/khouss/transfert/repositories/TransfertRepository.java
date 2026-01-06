package com.khouss.transfert.repositories;

import com.khouss.transfert.entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransfertRepository extends JpaRepository<Transfert, String> {
}
