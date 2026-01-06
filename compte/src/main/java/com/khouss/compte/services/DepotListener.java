package com.khouss.compte.services;


import com.khouss.compte.dtos.CreditEvent;
import com.khouss.compte.dtos.DepotEvent;
import com.khouss.compte.dtos.OperationResultEvent;
import com.khouss.compte.entities.Compte;
import com.khouss.compte.entities.ProcessedOperation;
import com.khouss.compte.repositories.CompteRepository;
import com.khouss.compte.repositories.ProcessedOperationRepository;
import jakarta.transaction.Transactional;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DepotListener  {
    private final ProcessedOperationRepository processedRepo;
    private final CompteRepository comptesRepository;
    private final StreamBridge streamBridge;

    public DepotListener(ProcessedOperationRepository processedRepo, CompteRepository comptesRepository, StreamBridge streamBridge) {
        this.processedRepo = processedRepo;
        this.comptesRepository = comptesRepository;
        this.streamBridge = streamBridge;
    }


    @Bean
    public Consumer<DepotEvent> depot(){
        return event ->
        {
            String key  = event.getTransferId() + "_DEPOT";
            if (processedRepo.existsById(key)) return;

            Compte compte = comptesRepository.findByNumeroTelephone(event.getCompteId()).orElseThrow(
                    ()-> new RuntimeException("Compte not found"));

            compte.setSolde(compte.getSolde().add(event.getMontant()));
            comptesRepository.save(compte);
            processedRepo.save(new ProcessedOperation(key));
            streamBridge.send("result-out-0",
                    new OperationResultEvent(event.getTransferId(),
                            "DEPOT", "SUCCESS", null));





        };
   }

    @Bean
    public Consumer<CreditEvent> credit(){
        return event ->
        {
            String key  = event.getTransferId() + "_CREDIT";
            if (processedRepo.existsById(key)) return;

            Compte compte = comptesRepository.findByNumeroTelephone(event.getCompteId()).orElseThrow(
                    ()-> new RuntimeException("Compte not found"));

            compte.setSolde(compte.getSolde().add(event.getMontant()));
            comptesRepository.save(compte);
            processedRepo.save(new ProcessedOperation(key));
            streamBridge.send("result-out-0",
                    new OperationResultEvent(event.getTransferId(),
                            "CREDIT", "SUCCESS", null));





        };
   }
}
