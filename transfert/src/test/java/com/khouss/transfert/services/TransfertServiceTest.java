package com.khouss.transfert.services;

import com.khouss.transfert.dtos.DebitEvent;
import com.khouss.transfert.dtos.TransfertRequest;
import com.khouss.transfert.entities.Transfert;
import com.khouss.transfert.repositories.TransfertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransfertServiceTest {

    @Mock
    private TransfertRepository transferRepository;

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private TransfertService transferService;

    @Test
    void startTransfer_shouldSaveTransferAndSendEvent() {
        // Given
        TransfertRequest request = new TransfertRequest();
        request.setSource("771234567");
        request.setDestination("781234567");
        request.setMontant(BigDecimal.valueOf(1000));

        Transfert transfer = new Transfert("id", "771234567", "781234567", BigDecimal.valueOf(1000), "INITIATED");
        when(transferRepository.save(any(Transfert.class))).thenReturn(transfer);

        // When
        String result = transferService.startTransfer(request);

        // Then
        verify(transferRepository).save(any(Transfert.class));
        verify(streamBridge).send(eq("debit-out-0"), any(DebitEvent.class));
        // Assert that result is not null or something
    }
}