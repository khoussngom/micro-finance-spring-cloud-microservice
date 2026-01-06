package com.khouss.compte.services;


import com.khouss.compte.dtos.DebitEvent;
import com.khouss.compte.dtos.DebitResultEvent;
import com.khouss.compte.dtos.OperationResultEvent;
import com.khouss.compte.entities.Compte;
import com.khouss.compte.entities.ProcessedOperation;
import com.khouss.compte.repositories.CompteRepository;
import com.khouss.compte.repositories.ProcessedOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DebitListener {

    private static final Logger logger = LoggerFactory.getLogger(DebitListener.class);

    private final CompteRepository comptesRepository;
    private final ProcessedOperationRepository processedRepo;
    private final StreamBridge streamBridge;

    public DebitListener(CompteRepository comptesRepository, ProcessedOperationRepository processedRepo, StreamBridge streamBridge) {
        this.comptesRepository = comptesRepository;
        this.processedRepo = processedRepo;
        this.streamBridge = streamBridge;
    }

    @Bean
    public Consumer<DebitEvent> debit(){
        return event -> {
            logger.info("Received DebitEvent: {}", event);
            String key = event.getTransferId() + "_DEBIT";

            if(processedRepo.existsById(key)) {
                logger.info("Operation already processed: {}", key);
                return;
            }

            Compte compte = comptesRepository.findByNumeroTelephone(event.getCompteId())
                    .orElseThrow(()-> new RuntimeException("Compte not found"));

            if (compte.getSolde().compareTo(event.getMontant())<0){
                logger.warn("Insufficient balance for debit: compte={}, solde={}, montant={}", compte.getId(), compte.getSolde(), event.getMontant());
                streamBridge.send("result-out-0",
                        new OperationResultEvent(event.getTransferId(), "DEBIT", "FAILED", "Solde Insuffisant"));
                return;
            }

            compte.setSolde(compte.getSolde().subtract(event.getMontant()));
            comptesRepository.save(compte);
            processedRepo.save(new ProcessedOperation(key));
            logger.info("Debit processed successfully for transfer: {}", event.getTransferId());

            streamBridge.send("result-out-0",
                    new OperationResultEvent(event.getTransferId(),
                            "DEBIT", "SUCCESS", null));
    };

}
}
