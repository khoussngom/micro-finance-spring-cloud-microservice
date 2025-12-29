package com.khouss.compte.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompte {
    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    private String numeroTelephone;
    @Min(value = 0, message = "Le solde doit être positif ou nul")
    private Double solde;
    @NotNull(message = "Le numéro de CNI ne peut pas être vide")
    private Long numCni;
}
