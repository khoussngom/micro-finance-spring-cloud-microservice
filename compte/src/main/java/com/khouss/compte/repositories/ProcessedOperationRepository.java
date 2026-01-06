package com.khouss.compte.repositories;

import com.khouss.compte.entities.ProcessedOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedOperationRepository extends JpaRepository<ProcessedOperation,String> {
}
