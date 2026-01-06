package com.khouss.transfert.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@SourceNotEqualDestination
public class TransfertRequest {

    @NotBlank
    @Pattern(regexp = "^(\\+221|221)?[76-8]\\d{8}$", message = "Le numéro de téléphone source doit être un numéro valide au Sénégal")
    @ValidAccount
    private String source;

    @NotBlank
    @Pattern(regexp = "^(\\+221|221)?[76-8]\\d{8}$", message = "Le numéro de téléphone destination doit être un numéro valide au Sénégal")
    @ValidAccount
    private String destination;

    @Positive
    private BigDecimal montant;

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
}
