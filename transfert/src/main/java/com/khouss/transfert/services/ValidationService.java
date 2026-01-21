package com.khouss.transfert.services;

import com.khouss.transfert.openFeign.CompteClient;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final CompteClient compteClient;

    public ValidationService(CompteClient compteClient) {
        this.compteClient = compteClient;
    }

    public boolean compteExists(String numeroTelephone) {
        try {
            return compteClient.compteExists(numeroTelephone);
        } catch (Exception e) {
            return false;
        }
    }
}