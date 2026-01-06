package com.khouss.transfert.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Transfert {

    @Id
    private String id;

    private String sourceCompte;
    private String destinationCompte;

    private BigDecimal montant;

    private String status;

    public Transfert() {}

    public Transfert(String id, String sourceCompte, String destinationCompte, BigDecimal montant, String status) {
        this.id = id;
        this.sourceCompte = sourceCompte;
        this.destinationCompte = destinationCompte;
        this.montant = montant;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSourceCompte() { return sourceCompte; }
    public void setSourceCompte(String sourceCompte) { this.sourceCompte = sourceCompte; }

    public String getDestinationCompte() { return destinationCompte; }
    public void setDestinationCompte(String destinationCompte) { this.destinationCompte = destinationCompte; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
