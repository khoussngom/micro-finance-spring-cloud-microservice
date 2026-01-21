package com.khouss.transfert.dtos;

import java.math.BigDecimal;

public class DebitEvent {
    private String transferId;
    private String compteId;
    private BigDecimal montant;

    public DebitEvent() {}

    public DebitEvent(String transferId, String compteId, BigDecimal montant) {
        this.transferId = transferId;
        this.compteId = compteId;
        this.montant = montant;
    }

    public String getTransferId() { return transferId; }
    public void setTransferId(String transferId) { this.transferId = transferId; }

    public String getCompteId() { return compteId; }
    public void setCompteId(String compteId) { this.compteId = compteId; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
}