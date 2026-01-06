package com.khouss.transfert.services;

import com.khouss.transfert.dtos.CreditEvent;
import com.khouss.transfert.dtos.DebitEvent;
import com.khouss.transfert.dtos.OperationResultEvent;
import com.khouss.transfert.dtos.TransfertRequest;
import com.khouss.transfert.entities.Transfert;
import com.khouss.transfert.repositories.TransfertRepository;
import jakarta.transaction.Transactional;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.function.Consumer;

@Service
public class TransfertService {

    private final TransfertRepository transferRepository;
    private final StreamBridge streamBridge;

    public TransfertService(TransfertRepository transferRepository, StreamBridge streamBridge) {
        this.transferRepository = transferRepository;
        this.streamBridge = streamBridge;
    }

    @Transactional
    public String startTransfer(TransfertRequest request) {

        String transferId = UUID.randomUUID().toString();

        Transfert transfer = new Transfert(
                transferId,
                request.getSource(),
                request.getDestination(),
                request.getMontant(),
                "INITIATED"
        );

        transferRepository.save(transfer);


        streamBridge.send("debit-out-0",
                new DebitEvent(
                        transferId,
                        request.getSource(),
                        request.getMontant()
                ));

        return transferId;
    }

    @Bean
    public Consumer<OperationResultEvent> operationResult() {
        return event -> {
            Transfert transfert = transferRepository.findById(event.getTransferId()).orElse(null);
            if (transfert != null) {
                if ("SUCCESS".equals(event.getStatus())) {
                    if ("DEBIT".equals(event.getOperationType())) {
                        // After debit success, send credit
                        streamBridge.send("credit-out-0",
                                new CreditEvent(
                                        event.getTransferId(),
                                        transfert.getDestinationCompte(),
                                        transfert.getMontant()
                                ));
                        transfert.setStatus("DEBITED");
                    } else if ("CREDIT".equals(event.getOperationType())) {
                        transfert.setStatus("COMPLETED");
                    }
                } else {
                    transfert.setStatus("FAILED");
                }
                transferRepository.save(transfert);
            }
        };
    }
}
