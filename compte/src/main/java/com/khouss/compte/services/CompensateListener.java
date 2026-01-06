package com.khouss.compte.services;

import com.khouss.compte.dtos.CreditEvent;
import com.khouss.compte.entities.Compte;
import com.khouss.compte.entities.ProcessedOperation;
import com.khouss.compte.repositories.CompteRepository;
import com.khouss.compte.repositories.ProcessedOperationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CompensateListener {

    private final CompteRepository compteRepository;
    private final ProcessedOperationRepository processedRepo;

    public CompensateListener(CompteRepository compteRepository, ProcessedOperationRepository processedRepo) {
        this.compteRepository = compteRepository;
        this.processedRepo = processedRepo;
    }

    @Bean
    public Consumer<CreditEvent> compensate() {
        return event -> {
            String key = event.getTransferId() + "_COMPENSATE";

            if (processedRepo.existsById(key)) return;

            Compte compte = compteRepository.findById(event.getCompteId())
                    .orElseThrow(() -> new RuntimeException("Compte inexistant"));


            compte.setSolde(compte.getSolde().add(event.getMontant()));
            processedRepo.save(new ProcessedOperation(key));
        };
    }
}
